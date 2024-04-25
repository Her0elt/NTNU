#include <boost/asio.hpp>
#include <iostream>
#include <stdio.h>
#include <string.h>


const int PORT = 8000;

using namespace std;
using namespace boost::asio::ip;

class EchoServer {
private:
  class Connection {
  public:
    tcp::socket socket;
    Connection(boost::asio::io_service &io_service) : socket(io_service) {}
  };

  boost::asio::io_service io_service;

  tcp::endpoint endpoint;
  tcp::acceptor acceptor;

  string main_page() {
    return "HTTP/1.0 200 OK\n"
       "Content-Type: text/html; charset=utf-8\n"
       "Connection: Closed\r\n\r\n"
       "<!DOCTYPE html><html><body>"
       "<h1>Dette er hovedsiden </h1>"
       "</body></html>";
  }
  
  string en_side_page() {
      return  "HTTP/1.0 200 OK\n"
       "Content-Type: text/html; charset=utf-8\n"
       "Connection: Closed\r\n\r\n "
       "<!DOCTYPE html><html><body>"
       "<h1>Dette er en side </h1>"
       "</body></html>";
  }
  string error() {
       return "HTTP/1.0 200 OK\n"
       "Content-Type: text/html; charset=utf-8\n"
       "Connection: Closed\r\n\r\n"
       "<!DOCTYPE html><html><body>"
       "<p> 404 Not Found</p>"
       "</body></html>";
  }

  void handle_request(shared_ptr<Connection> connection) {
    auto read_buffer = make_shared<boost::asio::streambuf>();
    // Read from client until newline ("\r\n")
    async_read_until(connection->socket, *read_buffer, "\r\n",
                     [this, connection, read_buffer](const boost::system::error_code &ec, size_t) {
      if (!ec) {
        istream read_stream(read_buffer.get());
        std::string message;
        getline(read_stream, message);
        message.pop_back();

        if (message == "exit") return;

        cout << "Message from a connected client: " << message << endl;

        auto write_buffer = make_shared<boost::asio::streambuf>();
        ostream write_stream(write_buffer.get());

        if(message == "GET /en_side HTTP/1.1"){

         write_stream << en_side_page();
        }
        else if( message == "GET / HTTP/1.1") {
          write_stream << main_page();
        }
        else{
          write_stream << error();
        } 

        async_write(connection->socket, *write_buffer,
                    [this, connection, write_buffer](const boost::system::error_code &ec, size_t) {
          if (!ec) handle_request(connection);
          connection->socket.close();
        });
      }
    });


  }
  

  void accept() {
    auto connection = make_shared<Connection>(io_service);
    
    acceptor.async_accept(connection->socket, [this, connection](const boost::system::error_code &ec) {
      accept();
      if (!ec) {
        handle_request(connection);
      }
    });
  }

public:
  EchoServer() : endpoint(tcp::v4(), PORT), acceptor(io_service, endpoint) {}

  void start() {
    accept();

    io_service.run();
  }
};

int main() {
  EchoServer echo_server;

  cout << "Starting echo server" << endl
       << "Connect in a terminal with: telnet localhost "<< PORT <<". Type 'exit' to end connection." << endl;

  echo_server.start();
}
