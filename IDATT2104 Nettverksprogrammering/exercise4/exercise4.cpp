#include <iostream>
#include <condition_variable>
#include <thread>
#include <functional>
#include <list>

using namespace std;
using namespace chrono;

class Workers{

    list<function<void()>> tasks_list;
    list<thread> thread_list;
    mutex tasks_mutex;
    mutex stopped_mutex;
    mutex wait_mutex;
    bool stopped = false;
    bool wait = true;
    condition_variable con_var;
    int number_of_threads = 1;

  public:
    Workers(int nr_threads) {
        number_of_threads = nr_threads;
        
    }

    void post(function<void()> task) {
        {
        lock_guard<mutex> lock(tasks_mutex);
        tasks_list.emplace_back(task);
        }

        {
            lock_guard<mutex> lock(wait_mutex);
            wait = false;
        }
        con_var.notify_all();
    }


    void start(){
        thread_list.clear();
        for (int i = 0; i < number_of_threads; i++){
            thread_list.emplace_back([this, i]{
                bool done = false;
                bool tasks_left = false;
                int nr = i;
                cout << "thread " << nr+1 << endl;
                while(!done){
                    function<void()> task;  
                    if(!tasks_left){
                        unique_lock<mutex> lock(wait_mutex);
                        while(wait){
                            con_var.wait(lock);
                        }
                    }
                    work(task, tasks_left, done);
                    if(task) {
                        task();
                        con_var.notify_one();
                    }
                }
            });
        }
    }

    void work(function<void()> &task, bool &tasks_left, bool &done){
        {
            lock_guard<mutex> lock(tasks_mutex);
            if(!tasks_list.empty()){
                task = *tasks_list.begin();
                tasks_list.pop_front();
                tasks_left = true;

            }else{
                {
                    unique_lock<mutex> lock(stopped_mutex);
                    if(stopped){
                        done = true;
                    }else{
                        unique_lock<mutex> lock(wait_mutex);
                        wait = true;
                        tasks_left = false;
                    }
                }
            }
        }
    }

    void stop(){
        {
            unique_lock<mutex> lock(stopped_mutex);
            stopped = true;
        }
        {
            lock_guard<mutex> lock(wait_mutex);
            wait = false;
        }
        con_var.notify_all();


        //should be like this
        // for (auto &thread: thread_list){
        //     thread.join();
        // }
    }
    //which makes this useless
    void join(){
        for (auto &thread: thread_list){
            thread.join();
        }
    }

    void post_timeout(function<void()> task, int ms){
         {
            unique_lock<mutex> lock(tasks_mutex);
            tasks_list.emplace_back([ms, task] {
                this_thread::sleep_for(chrono::milliseconds(ms));
                task();
            });
        }
        con_var.notify_one();
    }
};

int main() {
    Workers worker_threads(4);
    Workers event_loop(1);
    worker_threads.start();
    event_loop.start(); 
    worker_threads.post([] {
        cout << "a"<<endl;
    });

    worker_threads.post_timeout([] {
        cout << "d"<<endl;
    },5000);
    event_loop.post([] {
        cout << "b"<<endl;
    });
    event_loop.post([] {
        cout << "c"<<endl;
    });

    worker_threads.stop();
    event_loop.stop();
    worker_threads.join(); 
    event_loop.join(); 
    
}