<template>
    <div class="vue-tempalte">
                 <div class="App">
      <div class="vertical-center">
        <div class="inner-block">
                  <h5 class="errorMessage">{{errorMessage}}</h5>
        <form>
            <h3>Sign Up</h3>

            <div class="form-group">
                <label>Full Name</label>
                <input type="text" v-model="userName" class="form-control form-control-lg"/>
            </div>

            <div class="form-group">
                <label>Email address</label>
                <input type="email" v-model="email" class="form-control form-control-lg" />
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" v-model="password" class="form-control form-control-lg" />
            </div>

            <button v-on:click= "doSignUp()" type="button" class="btn btn-dark btn-lg btn-block">Sign Up</button>

            <p class="forgot-password text-right">
                Already registered 
                <router-link :to="{name: 'login'}">sign in?</router-link>
            </p>
        </form>
    </div></div></div></div>
</template>

<script>
import Constants from "./Constants.js";
    export default {
        userName:String,
        email:String,
        password:String,
        errorMessage:String,
        data() {
            return {

                 userName: this.userName,
                 email:this.email,
                 password:this.password,
                 errorMessage:this.errorMessage


            }
        },
             methods: {
      async doSignUp () {

          
          const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Basic dGVzdDprYXJhY2hpMTIz'
    }

 
     
           try {
               
                this.errorMessage ="";
        const response = await this.$http.post(
          Constants.URL+"/auth/register",{name:this.userName,email:this.email,password:this.password},{headers:headers});
             
                  alert(response.data['message']);
            this.$router.push('login') 
            
           

        } catch (error) {
            if(error.response.data['error_description']!=null)
            this.errorMessage = error.response.data['error_description'];
            else if(error.response.data['message']!=null)
            this.errorMessage = error.response.data['message'];
            else if(error.response.data['fieldErrors']!=null)
            this.errorMessage = error.response.data['fieldErrors'];

            else
            this.errorMessage = "Network Error"
      }
        
      }
      
    }
    }
</script>