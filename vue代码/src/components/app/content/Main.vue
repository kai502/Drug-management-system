<style scoped>

</style>

<template>
<!--  <h3>这是Main</h3>-->
  <Row>
    <Col span="5">
      <Menu/>
    </Col>
    <Col span="19" style="border: 1px solid black;border-radius: 15px;overflow: hidden">
      <router-view/>
    </Col>
  </Row>
</template>

<script>

import Menu from "@/components/app/Menu.vue";
import {getUserInfo} from "@/api/UserApi";

export default {
  components:{Menu},
  mounted() {
    getUserInfo()
        .then(response =>{
          if(response.data.code !== 200){
            this.$router.push('/login')
          }
          else {
            this.$router.push('/main/Message')
          }
        })
  }
}
</script>
