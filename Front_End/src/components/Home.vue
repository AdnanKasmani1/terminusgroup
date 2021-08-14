<template>
  <div id="app">

    <div class="row">
      <div class = "col-sm-3">
    <globalmenu/>
      </div>
    <div class = "col-sm-9">
  
      <table class="table">
        <thead>
          <th scope="col"> S.no</th>
          <th scope="col"> IMEI</th>
          <th scope="col"> SIM</th>
          <th scope="col"> ENABLE</th>
          <th scope="col"> CONFIGURED</th>

        </thead>
        <tbody>
    <tr v-for="item in categories" :key="item">
    <td>{{ item.id }}</td>
    <td>{{ item.imei }}</td>
    <td>{{ item.sim }}</td>
    <td>{{ item.enable }}</td>
    <td>{{ item.configured }}</td>
    </tr>
    </tbody>
      </table>
    </div>
      
  </div>
</div>
</template>

<script>
import globalmenu from './Menu.vue';
import Constants from "./Constants.js";

export default {
  name: 'App',
  components: {
    globalmenu
  },
      data: function () {
        return {
            categories: [],
        }
    },
    methods: {
        async getDefaultFilter() {
            const headers = {

            'Authorization': 'Bearer '+localStorage.getItem('user-token'),
            "Access-Control-Allow-Origin": "*"
          }

           try {
             const response = await this.$http.post(
               Constants.URL+'/api/v2.0/devices/listalldevices',{},{headers:headers});
               this.categories=response.data;

        } catch (error) {
        console.log(error);
      
      }
        }
    },
    beforeMount() {
        this.getDefaultFilter();
    }
}

</script>


