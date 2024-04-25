
const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const fs = require('fs');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
const cors = require('cors');

app.use(cors({origin: 'http://localhost:3000'}));


app.post('/compile', (req, res) => {
  writeToFile(req.body["code"]);
  const { exec } = require("child_process");
  exec("docker build \"./cpp/\" -t gcc",() =>{
      exec("docker run --rm gcc", (error, stdout, stderr) => {
          res.status(200).send(JSON.stringify({ans:stdout}))  
      });
  });
});

const server = app.listen(9090, () => {
  console.log('listening on port %s...', server.address().port);
});

function writeToFile(code){
  fs.writeFile('./cpp/main.cpp', code, function (err) {
      if (err) throw err;
    });
}