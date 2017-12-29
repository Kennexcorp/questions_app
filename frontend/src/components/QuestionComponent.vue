<template>

  <div class="QuestionComponent container">

    <div class="row">
      <div class="col-md-8">
        <div class="panel panel-primary">
        <div class="panel-heading">
          <p>Questions upload here</p>
        </div>
        <div class="panel-body">
        <form class="form-horizontal" role="form" method="post" action="http://localhost:8080/questions_api/add_question">

          <!-- question -->
          <div class="form-group">
            <label for="question" class="col-md-2 control-label">Question</label>
            <div class="col-md-10">
              <textarea v-model="question.question_desc" type="text" class="form-control" id="question" name="question_desc" rows="10" placeholder="Enter Question here" required></textarea>
            </div>
          </div>

          <!-- Option a -->
          <div class="form-group">
            <label for="option_a" class="col-md-2 control-label">Option A</label>
            <div class="col-md-10">
              <input v-model="question.option_a" type="text" name="option_a" id="option_a" class="form-control" placeholder="Enter option A" required>
            </div>
          </div>

          <!-- Option b -->
          <div class="form-group">
            <label for="option_b" class="col-md-2 control-label">Option B</label>
            <div class="col-md-10">
              <input v-model="question.option_b" type="text" name="option_b" id="option_b" class="form-control" placeholder="Enter option B" required>
            </div>
          </div>

          <!-- Option c -->
          <div class="form-group">
            <label for="option_c" class="col-md-2 control-label">Option C</label>
            <div class="col-md-10">
              <input v-model="question.option_c" type="text" name="option_c" id="option_c" class="form-control" placeholder="Enter option C" required>
            </div>
          </div>

          <!-- Option d -->
          <div class="form-group">
            <label for="option_d" class="col-md-2 control-label">Option D</label>
            <div class="col-md-10">
              <input v-model="question.option_d" type="text" name="option_d" id="option_d" class="form-control" placeholder="Enter option D" required>
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
                  <label><input v-model="question.correct_ans" name="correct_ans" type="radio" value="option_a">Option A</label>
                </div>

                <div class="radio">
                  <label><input v-model="question.correct_ans" name="correct_ans" type="radio" value="option_b">Option B</label>
                </div>

                <div class="radio">
                  <label><input v-model="question.correct_ans" name="correct_ans" type="radio" value="option_c">Option C</label>
                </div>

                <div class="radio">
                  <label><input v-model="question.correct_ans" name="correct_ans" type="radio" value="option_d">Option D</label>
                </div>
              </div>
              <p>
                <input type="text" placeholder="User ID" name="user_id" v-model="question.user_id" id="user_id">
              </p>
            </div>
          </div>

          <!-- Submit form -->
          <div class="form-group">
            <div class="col-md-12">
              <input type="submit" class="btn btn-primary col-md-12" value="Submit"/>
            </div>
          </div>
        <p v-if="submitData">{{submitData}}</p>
        </form>
        </div>
      </div>
      </div>

      <div class="col-md-4">
        <!-- <p>Questions List here</p> -->
        <div class="panel panel-primary">
          <div class="panel-heading">
          <p>Uploaded Questions</p>
          </div>
          <div class="panel-body">
            <questionList v-bind:questions="questions"></questionList>
          </div>

        </div>
        
      </div>
      </div>

  </div>
</template>

<script>
  // /* eslint-disable */
  import {AXIOS} from './http-common'
  import QuestionsList from './QuestionsListComponent.vue'

  export default {
    name: 'QuestionComponent',
    components: {
      'questionList': QuestionsList
    },

    data () {
      return {
        questions: [],
        submitData: null,
        question: {
          question_desc: '',
          option_a: '',
          option_b: '',
          option_c: '',
          option_d: '',
          correct_ans: '',
          user_id: ''
        }
      }
    },
    mounted () {
      this.getUserQuestions(0)
    },
    methods: {
      addQuestion (question) {
        AXIOS.post('/questions_api/add_question/' + this.question)
          .then(response => {
            console.log('Success ' + response.data)
          })
          .catch(e => {
            console.log('Failure ' + e)
          })
      },
      doSubmit () {
        this.submitData = this.question
      },
      getUserQuestions (id) {
        AXIOS.get('questions_api/get_questions?id=' + id)
        .then(function (response) {
          this.questions = response.data
          console.log('Successful ')
          // this.questions[0].option_a
        }.bind(this))
        .catch(function (e) {
          console.log('error ' + e)
        })
      },
      logout () {
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
