# MOL3022_bioinformatics_project
 
### Project created by:
Hermann Owrenn Elton: hermanoe@stud.ntnu.no
<br>
Simon Jensen: simon.jensen@ntnu.no
<br>
Stian Fjæran Mogen: stianfmo@stud.ntnu.no

### Deployed application

The application is deployed for your convenience at the following url: https://mol-3022-bioinformatics-project.vercel.app/predict
<br>
If you are the only current user of the web page, you will have to wait a couple of seconds for the server to start up. On the prediction page, wait 10-20 seconds on the "Wating for the prediction api to wake up" page, and then refresh. The prompt will dissapear and you can start predicting. 

### Setup and running locally

If the deployment is not running, or if you want to test for yourself locally, follow these instructions: 

Cloning the repository can be done with the following commands: 

#### HTTP
```angular2html
git clone https://github.com/stianmogen/MOL3022_bioinformatics_project.git
```
#### SSH
```angular2html
git clone git@github.com:stianmogen/MOL3022_bioinformatics_project.git
```

#### Dataset

Download [2018-06-06-ss.cleaned.csv](https://www.kaggle.com/datasets/alfrandom/protein-secondary-structure?select=2018-06-06-ss.cleaned.csv) and add it to a folder named 'data' in the project root directory.


To run this project you must have [python](https://www.python.org/downloads/) correctly installed on your computer.

#### Conda

We recommend using and [anaconda](https://conda.io/projects/conda/en/latest/user-guide/install/index.html) for this project.
To create environment with necessary dependencies open a terminal in project root and write the following:
```angular2html
conda env create -f environment.yml
conda activate conda-env
```

Now you can run commands in terminal. To train the model yourself run the following:
```angular2html
python3 train.py
```


To use the newly created environment in Pycharm IDE go to:

Pycharm -> Settings -> Project MOL3022_bioinformatics_project -> Python interpreter -> Add interpreter -> Add local interpreter -> Conda environment -> Use existing environment -> Select 'conda-env' from the dropdown

#### Pip
If you want to use pip instead of conda is important that you have python3.8 installed. This is necessary to use tensorflow.
```angular2html
python -m pip install --upgrade pip

pip install virtualenv

virtualenv -p python3.8 venv

source venv/bin/activate

pip install -r requirements.txt
```
#### Backend 

To setup the backend server, run the following command inside project root folder: 
```angular2html
uvicorn server:app --reload
```

#### Fronted

To start the frontend application, open a new terminal and run the following:
```angular2html
cd web-app
yarn install
yarn run dev
```
Go to http://localhost:3000/ and test out the application.


### Training Results

If you want to see output from the training without running it yourself, please visit the [Kaggle notebook](https://www.kaggle.com/code/simojens/notebook212b492bbb).
