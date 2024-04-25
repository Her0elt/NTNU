<template>
  <div id="grid">
    <Paper id="container" shadow>
        <div id="display">
        {{display}}
      </div>
      <div id = "edit">
        <CalculatorButton class="buttons" @clicked="() => current=''" sign='C' />
        <CalculatorButton class="buttons" @clicked="clear" sign='AC' />
        <CalculatorButton class="buttons" @clicked="del" sign='DEL' />
      </div>
      <div id="action-container">
        <div id="numbers">
          <CalculatorButton class="buttons nummber" v-for="number in numbers" :key="number" @clicked="addNumber" :sign = "number" />
        </div>
          <div id="signs">
          <CalculatorButton class="buttons" v-for="sign in signs" :key="sign" @clicked="setSign" :sign = "sign" />
          <CalculatorButton class="buttons" @clicked="addDesimale" sign='.' />
        </div>
          <CalculatorButton class="buttons nummber" @clicked="addNumber" sign='0' />
          <CalculatorButton class="buttons" id="equals" @clicked="setSign" sign='=' />
      </div>
    </Paper>
    <div id ="log-container">
      <button id="hide-btn" @click="seen=!seen">{{seen? "Hide" : "Show"}}</button>
      <Paper id="log" v-if="seen" shadow>
        <div v-for="equation in log" :key="equation">
          {{equation}}
        </div>
      </Paper>
    </div>
  </div>
</template>

<script lang = "ts">//lang = "ts"
  import {ref, defineComponent, computed} from 'vue';
  import axios from 'axios';
  import CalculatorButton from '@/components/CalculatorButton.vue'
  import Paper from '@/components/Paper.vue'
export default defineComponent ({
  name: 'Calculator',
  components: {
    CalculatorButton,
    Paper
  },
  // data(){ for using this instead of .value of state might be vue2 option api 
  // 
  // },
  setup(){
    // reactive for non primitive values, so lager objects
    const signs = ["+","-","*","/"];
    const numbers = ["1", "2", "3", "4", "5", "6", "7", "8", "9"];
    const log = ref<Array<String>>([]);
    const sign = ref<String>("");
    const saved = ref<String>("");
    const current = ref<String>("");
    const seen = ref<Boolean>(true);
    let desimale = false;

    const addNumber =(nr:String) =>{
      if(display.value.length > 9) return;
      if(sign.value === "=")saved.value = "";
      current.value = `${current.value}${nr}`;
    }

    const setSign = (newSign: String) =>{
      desimale = false;
      if(sign.value !== "")evaluate()
      else {
        saved.value = current.value
        current.value = "";
        sign.value = newSign;
      }
    }

    const del = () => {
      current.value = current.value.slice(0, current.value.length-1);
    }

    const evaluate =  async () =>{
      const prevSaved = saved.value;
      saved.value = (await calculate(Number(saved.value), Number(current.value? current.value: saved.value ), sign.value)).toString();
      if(sign.value !== "=") log.value.unshift(`${prevSaved} ${sign.value} ${current.value? current.value: prevSaved } = ${saved.value}`);
      current.value = "";
      sign.value = "";
    }
    
    const calculate = async(nr1:number, nr2:number, sign:String): Promise<number> => {
      return await axios.post('http://localhost:8080/api/calculator/', {
      nr1: nr1,
      nr2: nr2,
      sign:sign
      })
      .then((response) => {
        return response.data.ans
      })
      .catch((error) => {
       alert(error.response.data.message)
        return 0;
      });
    }

    const addDesimale = () =>{
      if(!desimale){
        current.value += '.';
        desimale = true;
      }
    }
    const clear = () =>{
      current.value = "";
      saved.value = "";
      sign.value = "";
    }

    const display = computed(()=>{ return saved.value ? 
        current.value ? 
        current.value : saved.value
        : current.value})
          
  return{ signs, numbers, addNumber, setSign, display, addDesimale, log, clear, saved, current, del, seen }
  },
})
</script>

<style scoped>
  *{
   padding: 0;
   margin: auto; 
  }
  #grid{
    margin: 0 auto;
    margin-top: 25px;
    display: grid;
    width: 100%;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: minmax(0, 1fr);
  }
  #container{
    margin: 0px 0px 0px 100px;
    display: grid;
    grid-template-columns: 1fr;
    box-sizing: border-box;
    max-width: 350px;
  }
  #log-container{
    width: 80%;
    align-content: center;
    justify-content: center;
    height: 100%;
  }
  #log{
    font-size: 50px;
    margin-top: 20px;
    height: 370px;
    margin: 5px;
    overflow-y: auto;
    text-align: center;
  }
  #hide-btn {
    display: flex;
    width: 50%;
    margin: 0 auto;
    padding:0.35em 1.2em;
    border:0.1em solid black;
    border-radius:0.12em;
    box-sizing: border-box;
    text-decoration:none;
    font-weight:300;
    color: black;
    text-align:center;
    transition: all 0.2s;
    outline: none;
  }
  #hide-btn::hover{
    background-color: #13171E;
  }
  #edit{
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    width: 100%;
  }
  #display{
    font-size: 42px;
    margin: 0 auto;
    border:0.1em solid black;
    border-radius:0.12em;
    margin-bottom: 10px;
    width: 100%;
    height: 70px;
    text-align: right;
    padding: 10px;
      -moz-box-sizing:border-box;
      -webkit-box-sizing:border-box;
      box-sizing:border-box;
  }
  #action-container{
    padding: 5px 5px 5px 5px;
    display: grid;
    grid-template-columns: 4fr 1fr;
    height: 100%;
    width: 100%;
    grid-gap: 5px;

  }
  #numbers{
    margin: 0;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-rows: 4fr 4fr 4fr;
    grid-gap: 5px;
  }
  .nummber{
    background-color: grey; 
    color: white;
  }
  #equals{
    background-color: orange;
  }
  #signs{
    display: grid;
    grid-template-columns: 1fr;
     grid-template-rows: 1fr 1fr 1fr 1fr 1fr;
    grid-gap: 5px;
  }
  .buttons {
    font-size: 20px;
  }
  @media only screen and (max-width: 1200px){
    #grid{
      margin: 0 auto;
      grid-template-columns: 1fr;
    }

    #container {
      margin: 0 auto;
      width: 100%;
      margin-top: 5px;
    }
    #log-container{
      margin: 0 0 0 0;
      margin-top: 20px;
      width: 100%;
    }
    #log{
      width: 82%;
      height: 45px;
    }
  }

</style>
