<template>
  <div class="hello">
    <button v-on:click="testRestService">Click me</button>
    <h1>{{ msg }}</h1>
    <h2>{{ msg2 }}</h2>

  </div>
</template>

<script>
  /* eslint-disable */
  import {AXIOS} from './http-common'

export default {
  name: 'HelloWorld',
  errors: '',

  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      msg2: 'Fuck it!! it works damn it'
    }
  },
  methods: {
      testRestService() {
          AXIOS.get("api/test")
            .then(response => {
                this.msg = response.data;
                console.log("Success " + response.data);
            })
            .catch(e => {
                console.log("Failure " + e);
            })
      }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
