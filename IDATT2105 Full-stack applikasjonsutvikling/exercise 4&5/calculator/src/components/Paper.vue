<template>
     <div :class="[shadow ? 'shadow' : 'border']">
        <slot/>
     </div>
</template>

<script lang = "ts">
  import {defineComponent} from 'vue';
export default defineComponent ( {
  name: 'Paper',
  props:{
    shadow:{
      type:Boolean,
      required: false
    }
  }
})
</script>

<style scoped>
    div{
        padding: 25px;
        border-radius: 5px;
    }
    .border{
      border:0.1em solid black;
    }
    .shadow{
      box-shadow: 5px 5px 5px 5px rgba(0,0,0,0.75);
    }
</style>
