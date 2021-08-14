<template>

    <div class="vue-tempalte">
         <div class="App">
      <div class="vertical-center">
        <div class="inner-block">

        <h5 class="errorMessage">{{errorMessage}}</h5>
        <form>
            <h3>Sign In</h3>

            <div class="form-group">
                <label>Email address</label>
                <input v-model="email" type="email" class="form-control form-control-lg" />
            </div>

            <div class="form-group">
                <label>Password</label>
                <input v-model="password" type="password" class="form-control form-control-lg" />
            </div>

            <button v-on:click="doLogin()" type="button" class="btn btn-dark btn-lg btn-block">Sign In</button>

            <p class="forgot-password text-right mt-2 mb-4">
                Dont have account ?
                <router-link to="/signup">Signup</router-link>
            </p>





        </form>
    </div>
      </div></div></div>
</template>

<script>
import Constants from "./Constants.js";
    export default {
        email:String,
        password:String,
        errorMessage:String,

        data() {
            
            return { 
                
                email: this.email,
                password:this.password,
                errorMessage:this.errorMessage
                
                
                }
        },

         methods: {
      async doLogin () {
          const headers = {
  'Content-Type': 'application/json',
  'Authorization': 'Basic dGVzdDprYXJhY2hpMTIz'
    }

     const form = new FormData()
     form.append('grant_type', 'password')
     form.append( 'username', this.email)
     form.append( 'password', this.password)
     
           try {
                this.errorMessage ="";
        const response = await this.$http.post(
          Constants.URL+"/oauth/token",form,{headers:headers});
            localStorage.setItem('user-token', response.data['access_token']) 

            this.$router.push('Home') 
        } catch (error) {
        console.log(error.response.data['error_description']);
        this.errorMessage = error.response.data['error_description'];
      }
        
      }
      
    }
    }
</script>