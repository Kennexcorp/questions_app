<template>
  <div class="QuestionsList" id="question_list">
    
    <div data-spy="scroll" data-target="#question_list" data-offset="0" id="list">
    <table v-for="question in questions" class="table table-hover col-md-12" >
      <tbody>
        <tr> 
          <td v-on:click="toggle">
            <p class="pull-left col-md-12">{{ question.question_desc }}</p>
          </td>
          <!-- <td><button class="btn btn-success" data-toggle="modal"
data-target="#myModal">X</button></td> -->
        </tr>
        <tr v-if="visible">
          <div>
            <button class="btn btn-success col-md-6" data-toggle="modal"
data-target="#myModal" v-on:click="editQuestion(question.id)">Edit</button>
            <button class="btn btn-danger col-md-6" v-on:click="deleteQuestion(question.id)">Delete</button>
          </div>
        </tr>
      </tbody>
    </table>
 
  <!-- Modal -->
 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close"
          data-dismiss="modal" aria-hidden="true">
          &times;
          </button>
          <h4 class="modal-title" id="myModalLabel">
            Edit Question 
          </h4>
          </div>
            <div class="modal-body">
              <form class="form-horizontal" role="form" method="post" action="http://localhost:8080/questions_api/update_question">

          <!-- question -->
          <input name="id" type="text" :value="question.id"/>
          <div class="form-group">
            <label for="question" class="col-md-2 control-label">Question</label>
            <div class="col-md-10">
              <textarea type="text" class="form-control" id="question" name="question_desc" rows="10" placeholder="Enter Question here" :value="question.question_desc" required></textarea>
            </div>
          </div>

          <!-- Option a -->
          <div class="form-group">
            <label for="option_a" class="col-md-2 control-label">Option A</label>
            <div class="col-md-10">
              <input :value="question.option_a" type="text" name="option_a" id="option_a" class="form-control" placeholder="Enter option A" required>
            </div>
          </div>

          <!-- Option b -->
          <div class="form-group">
            <label for="option_b" class="col-md-2 control-label">Option B</label>
            <div class="col-md-10">
              <input :value="question.option_b" type="text" name="option_b" id="option_b" class="form-control" placeholder="Enter option B" required>
            </div>
          </div>

          <!-- Option c -->
          <div class="form-group">
            <label for="option_c" class="col-md-2 control-label">Option C</label>
            <div class="col-md-10">
              <input :value="question.option_c" type="text" name="option_c" id="option_c" class="form-control" placeholder="Enter option C" required>
            </div>
          </div>

          <!-- Option d -->
          <div class="form-group">
            <label for="option_d" class="col-md-2 control-label">Option D</label>
            <div class="col-md-10">
              <input :value="question.option_d" type="text" name="option_d" id="option_d" class="form-control" placeholder="Enter option D" required>
            </div>
          </div>

          <!-- Correct answer -->
          <div class="col-md-4 col-lg-offset-4">
            <div class="panel panel-info">
              <div class="panel-heading">
                <h3 class="panel-title">Correct Answer</h3>
              </div>
              <div class="panel-body">
                <div class="radio">
                  <label><input name="correct_ans" type="radio" value="option_a" id="a">Option A</label>
                </div>

                <div class="radio">
                  <label><input name="correct_ans" type="radio" value="option_b" id="b">Option B</label>
                </div>

                <div class="radio">
                  <label><input name="correct_ans" type="radio" value="option_c" id="c">Option C</label>
                </div>

                <div class="radio">
                  <label><input name="correct_ans" type="radio" value="option_d" id="d">Option D</label>
                </div>
              </div>
              <p>
                <input type="text" placeholder="User ID" name="user_id" :value="question.user_id" id="user_id">
              </p>
            </div>
          </div>

          <!-- Submit form -->
          <div class="form-group">
            <div class="col-md-12">
              <input type="submit" class="btn btn-primary col-md-12" value="Submit"/>
            </div>
          </div>
    
        </form>
            </div>
         </div>
      </div>
    </div>
    </div>
  </div> 
</template>

<script>
  /* eslint-disable */
  import {AXIOS} from './http-common'

export default {
  name: 'QuestionsList',
  errors: '',
  props: ['questions'],
  data () {
    return {
      // questions: ['Question 1', 'Question 2', 'Question 3', 'Question 4', 'Question 5', 'Question 6']
      visible: false,
      edit: false,
      question_desc: null,
      question: {
        id: '',
        question_desc: '',
        option_a: '',
        option_b: '',
        option_c: '',
        option_d: '',
        correct_ans: '',
        user_id: ''
        },
        checked: false,
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
      },
      toggle () {
        this.visible = true
      },
      deleteQuestion (id) {
        AXIOS.get("/questions_api/delete_question?id=" + id)
        .then(function (response) {
          console.log("Success " + response)
          location.reload()
        })
        .catch(function (error) {
          console.log("Failure " + error)
        })
      },
      editQuestion(id) {
        AXIOS.get("/questions_api/edit_question?id=" + id)
        .then(function (response) {
          console.log("Success " + response.data.question_desc)
          console.log("Success " + response.data.id)
          // this.question.question_desc = response.data.question_desc
          this.question.id = response.data.id
          this.question.question_desc = response.data.question_desc
          this.question.option_a = response.data.option_a
          this.question.option_b = response.data.option_b
          this.question.option_c = response.data.option_c
          this.question.option_d = response.data.option_d
          this.question.user_id = response.data.user_id
          this.question.correct_ans = response.data.correct_ans
          console.log("Correct answer " + this.question.correct_ans)
          if (this.question.correct_ans == 'option_a') {
            document.getElementById('a').checked = true
          }
          if (this.question.correct_ans == 'option_b') {
            document.getElementById('b').setAttribute(checked)
          }
          if (this.question.correct_ans == 'option_c') {
            document.getElementById('c').setAttribute(checked)
          }
          if (this.question.correct_ans == 'option_d') {
            document.getElementById('d').setAttribute(checked)
          }
        }.bind(this))
        .catch(function (error) {
          console.log("Failure " + error)
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
li {
  display: inline-block;
  margin: 0;
}
a {
  color: #42b983;
}
#list {
  max-height:600px;
  overflow:auto; 
  position: relative;
}
</style>
