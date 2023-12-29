<style scoped>
  .header{
    width: 100%;
    height: 60px;
    background-color: black;
    background: linear-gradient(to right,#191970, #a9a9ea);
    position: fixed;
    top: 0;
  }
  .layout-logo{
    width: 150px;
    height: 40px;
    color: white;
    font-size: 20px;
    float: left;
    position: relative;
    top: 13px;
    left: 80px;
  }
  .laout-center{
    width: 320px;
    height: 60px;
    position: fixed;
    margin: 10px 0;
    color: white;
    left: 46%;
  }
  .layout-nav{
    width: 280px;
    height: 60px;
    position: relative;
    padding: 10px 80px;
    margin: 0 auto;
    margin-right: 0;
    z-index: 0;
  }

</style>

<template>
<!--  <Header :style="{position: 'fixed', width: '100%'}">-->

  <div class="header">
    <transition name="move-left">
      <div class="layout-logo" >
        <div v-if="username === '' ">
          <Badge dot>
            <Icon type="ios-notifications-outline" size="26"></Icon>
          </Badge>
          用户未登录
        </div>
        <div v-else>
          <div style="color: white">
            <Avatar :src="'src/assets/userImage/user_'+uid+'.png'" />

            {{ username }}
          </div>
        </div>
      </div>
    </transition>

    <transition name="move-right">
      <div class="laout-center" v-show="anim" @click="toBack" v-if="username === '' ">
          <Button type="primary" shape="circle" :loading="true" >
            <span v-if="loading">正在登录</span>
            <span v-else>正在注册</span>
          </Button>
      </div>
    </transition>

    <transition name="move-left" >
      <div class="layout-nav" v-show="!anim"  v-if="username === '' ">
        <Space :size="20">
          <Button type="primary" shape="circle" @click="toLogin">
            <span>登录</span>
          </Button>
          <Button type="default" shape="circle" ghost @click="register">
            <span>注册</span>
          </Button>
        </Space>
      </div>
      <div class="layout-nav" v-else>
        <Space :size="20">
          <Button type="default" shape="circle" ghost @click="exit">
            <span>注销</span>
          </Button>
        </Space>
      </div>
    </transition>

  </div>

<!--  </Header>-->

</template>

<script>
import {Col} from "view-ui-plus";
import {getUserInfo} from "@/api/UserApi";

export default {
  components: {Col},
  data () {
    return {
      anim:false,
      loading: false,
      username:'',
      uid:''
    }
  },
  methods: {
    toBack(){
      this.anim = !this.anim;
      this.$router.push("/login")
      if(this.username === ''){
        this.$Notice.warning({
          title: '未登录，已重新回到登录界面',
        });
      }
    },
    toLogin () {
      this.anim = !this.anim;
      this.loading = true;
      this.$router.push("/login")
    },
    register () {
      this.anim = !this.anim;
      this.loading = false;
      this.$router.push("/register")
    },
    exit () {
      localStorage.setItem('token','');
      location.reload();
    },
    reload(){
      // console.log("reload")
      getUserInfo()
          .then(response =>{
            if(response.data.code === 200){
              this.username = response.data.data.userInfo.fullName;
              this.uid = response.data.data.userInfo.uid
            }
          })
    }
  },
  watch:{
    "$route":"reload"
  },
  mounted() {
    // getUserInfo()
    //     .then(response =>{
    //       if(response.data.code === 200){
    //         this.username = response.data.data.userInfo.fullName
    //       }
    //     })
    //     .catch(function (reason){
    //       console.log("没有token")
    //     })
  }
}
</script>

