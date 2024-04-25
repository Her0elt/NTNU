<template>
   <form id="content" @submit="submitForm">
      <div>
        <h1>Welcome to the Vue3 WebSocket Chat App</h1>
        <h2>Nummber of chatters: <span id="connected">{{connected}}</span></h2>
      </div>
      <div id="messages">
        <div  v-for="message in messages" :key="message">
        {{message}}
        </div>
      </div>
      <div class="input">
        <input
          placeholder="Message..."
          v-model="inputArea"
          width="100%"
          name="inputArea"
          type="text"
        />
      </div>
      <button type="submit">Send message</button>
      <p>{{info}}</p>
    </form>
</template>

<script>
  import { ref, defineComponent, onMounted, watchEffect } from 'vue';
export default defineComponent ( {
  name: 'ChatApp',
  setup(){
    const messages = ref([]);
    const connected = ref(''); 
    const info = ref(''); 
    const inputArea = ref('')
    const ws = new WebSocket("ws://localhost:8000", ["json"])

    onMounted(()=>{
      ws.addEventListener("open", () => {
            info.value= "The connection is ready";
      });
      ws.addEventListener("message", (event) => {
          const data = JSON.parse(event.data);
          if (data.message) {
            messages.value.push(data.message);
          } else if (data.connected) {
            connected.value = data.connected;
          }
        });
    })

  
      watchEffect(()=>{
        if(info.value != ""){
          setTimeout(() => info.value= "", 3000)
        }
      })


      const submitForm = async (e) => {
        e.preventDefault();
        if (inputArea.value !== "") {
        const json = JSON.stringify({ message: inputArea.value });
        ws.send(json);
        info.value="The message was sent";
        inputArea.value = "";
      } else {
          info.value="You can't send an empty message";
        }
      };
          
  return{messages, connected, info, submitForm, inputArea}
    
  }
})
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #content{
    align-content: center;
    justify-content: center;
  }
  #messages{
    margin: 0 auto;
    justify-content: center;
    background-color: #131924;
    padding: 25;
    color:white;
    box-sizing: border-box;
    padding: 10px;
    border-radius: 5px;
    box-shadow: 5px 5px 5px 5px rgba(0,0,0,0.75);
    max-width: 450px;
    min-height: 200px;
    font-size: 22px;
  }
  #messages div{
    margin: 0 auto;
    margin-top: 10px;
    background-color:white;
    box-sizing: border-box;
    padding: 10px;
    border-radius: 5px;
    box-shadow: 5px 5px 5px 5px rgba(0,0,0,0.75);
    width: 40%;
  }
  input{
    margin: 0 auto;
    color: black;
    background-color: white;
    box-sizing: border-box;
    padding: 10px;
    border-radius: 5px;
    box-shadow: 5px 5px 5px 5px rgba(0,0,0,0.75);
    font-size: 18px;
    margin-bottom: 10px;
  }
  button{
    margin: 0 auto;
    display:inline-block;
    padding:0.35em 1.2em;
    border:0.1em solid #FFFFFF;
    border-radius:0.12em;
    box-sizing: border-box;
    text-decoration:none;
    font-family:'Roboto',sans-serif;
    font-weight:300;
    color:#FFFFFF;
    text-align:center;
    transition: all 0.2s;
    
  }
  button::hover{
    background-color: #13171E;
  }
</style>
