import axios from 'axios';
import {useState} from 'react'
import { Button, Paper ,TextField, Typography } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  root:{
    display: "grid",
    gridTemplateColumns: "1fr 1fr",
    gridTemplateRows: "1fr",
    gridTemplateAreas:"'text1 text2'",
    alignContent: 'center',
    backgroundColor: '#1b1b2d',
    gridGap: 5,
    padding: 28,
    margin: '55px 25px 0px 25px',
  },
  inputField: {
    gridArea: "text1",
    backgroundColor: "#131924",
  },
  input: {
    color: "white"
  },
  outputField: {
    backgroundColor: "#131924",
    padding: 25,
    gridArea: "text2",
    color:'white',
    whiteSpace: 'pre-line',
  },
  btn:{
    color:'white',
    backgroundColor: "#131924",
    '&:hover': {
      backgroundColor: '#13171E'
  }
  }
});



function App() {
  const classes = useStyles();
  const [code ,setCode] = useState('#include <iostream> \n int main(){\n std::cout<<"hello";\n}')
  const [ans, setAns] = useState('')

  const compile = () => {
    setAns("Compiling...")
    axios.post('http://localhost:9090/compile/', {
      code: code
    })
    .then((response) => {
      setAns(response.data.ans);
    })
    .catch((error) => {
      setAns("Something wrong happend");
      console.log(error);
    });
  };
 return(
   <Paper className={classes.root}>
     <TextField  InputProps={{className: classes.input}} variant="outlined" multiline className={classes.inputField} value={code} onChange={(e) => setCode(e.target.value)}></TextField >
     <Typography className={classes.outputField}>{ans}</Typography>
     <Button variant="outlined" className={classes.btn} onClick={compile}>Compile</Button>
   </Paper>
 )
}

export default App;
