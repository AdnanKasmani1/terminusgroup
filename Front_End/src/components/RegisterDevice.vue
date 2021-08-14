<template>

<div class="row">
      <div class = "col-sm-3">
    <globalmenu/>
      </div>
    <div class = "col-sm-9">

    <div class="vue-tempalte">
                 <div >
      <div class="vertical-center">
        <div class="inner-block">
                 <h5 class="errorMessage">{{errorMessage}}</h5>
        <form>
            <h3>Register Device</h3>

            <div class="form-group">
                <label>IMEI</label>
                <input  v-model="imei" type="text" class="form-control form-control-lg"/>
            </div>

            <div class="form-group">
                <label>SIM</label>
                <input  v-model="sim" type="text" class="form-control form-control-lg" />
            </div>

            <button v-on:click= "registerDevice()" type="button" class="btn btn-dark btn-lg btn-block">Register</button>

        </form>
    </div></div></div></div></div></div>
</template>

<script>
import globalmenu from './Menu.vue';
import Constants from "./Constants.js";
    export default {
         imei:String,
         sim:String,
         errorMessage:String,
          components: {
    globalmenu
  },
        data() {
            return {
                   imei :this.imei,
                   sim  :this.sim ,
                   errorMessage:this.errorMessage
            }
        },
              methods: {
      async registerDevice () {
          const headers = {
  'Authorization': 'Bearer '+localStorage.getItem('user-token')
    }

 
     
           try {
                this.errorMessage ="";
        const response = await this.$http.post(
          Constants.URL+"/api/v2.0/devices/register",{imei:this.imei,sim:this.sim},{headers:headers});
            response.data['status'];
              alert("Device register successfully")
            this.$router.push('home'); 
           

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