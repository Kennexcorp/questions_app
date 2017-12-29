<template>
  <div class="hello">
    <input type="text" placeholder="Insert id">
    <button v-on:click="getUserQuestions">Click me</button>

    <!-- <h1>{{ id }}</h1> -->
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
      msg2: 'Fuck it!! it works damn it',
      Question: []
    }
  },
  mounted() {
    this.getUserQuestions(1)
  },
  methods: {
      testRestService() {
          AXIOS.get("/questions_api/test")
            .then(function (response) {
                this.msg = response.data;
                console.log("Success " + response.data);
            })
            .catch(e => {
                console.log("Failure " + e);
            })
      },
      getUserQuestions (id) {
        AXIOS.get("questions_api/get_questions?id=" + id)
        .then(function (response) {
          this.Question = response.data
          console.log('Successful ' + this.Question[0].option_a)
        }.bind(this))
        .catch(function (e) {
          console.log("error " + e)
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
