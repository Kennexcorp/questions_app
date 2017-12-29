import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import LoginComponent from '@/components/LoginComponent'
import QuestionComponent from '@/components/QuestionComponent'
import RegistrationComponent from '@/components/RegistrationComponent'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'LoginComponent',
      component: LoginComponent
    },
    {
      path: '/reg',
      name: 'RegistrationComponent',
      component: RegistrationComponent
    },
    {
      path: '/hello',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/question_upload',
      name: 'QuestionComponent',
      component: QuestionComponent
    }
  ]
})
