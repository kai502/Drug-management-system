<style>
.demo-login{
  width: 400px !important;
  margin: 0 auto;
}
.demo-login-captcha .ivu-btn{
  padding: 0;
}
.demo-login-captcha .ivu-btn img{
  height: 28px;
  position: relative;
  top: 4px;
}
</style>

<template>

  <transition name="slide-up">
    <div class="demo-login" v-show="anim">

      <Title style="text-align: center" :level="2">登录</Title>

      <Login @on-submit="handleSubmit">
        <UserName name="username" placeholder="请输入账号"/>
        <Password name="password" />
        <Captcha class="demo-login-captcha" name="captcha" :count-down="0" @on-get-captcha="handleGetCaptcha">
          <template #text>
            <img :src="'https://file.iviewui.com/iview-pro/' + captcha">
          </template>
        </Captcha>
        <Submit  :loading="loading"/>
      </Login>

    </div>
  </transition>

</template>

<script>
import {getUserInfo, loginApi} from "@/api/UserApi";

export default {
  data () {
    return {
      anim:false,
      loading:false,
      userInfo:{
        username:'',
        userPwd:'',
      },
      captcha: 'captcha1.png'
    }
  },
  methods: {
    handleSubmit (valid, { username, password, captcha }) {

      this.userInfo.username = username;
      this.userInfo.userPwd = password;

      let code = null,message = null;

      loginApi(this.userInfo)
          .then( response => {
              // console.log(response.data);
              if(response.data.data != null){
                  const token = response.data.data.token
                  localStorage.setItem('token', token)
              }
              code = response.data.code;
              message = response.data.message;

            if (valid && code === 200) {
              // this.$Modal.info({
              //   title: '登录成功',
              //   content: "您好," + this.userInfo.username
              // })
              this.loading = true
              setTimeout(function (){
                    this.$router.push('/main')
                  }.bind(this),1500
              )
            }
            else if(valid && code === 500){
              this.$Modal.info({
                title: '登录失败',
                content: "用户名不正确"
              })
            }
            else if(valid && code === 501){
              this.$Modal.info({
                title: '登录失败',
                content: "密码错误"
              })
            }

          })

    },
    handleGetCaptcha () {
      // 这里只是模拟切换图形验证码
      if (this.captcha === 'captcha1.png') {
        this.captcha = 'captcha2.png';
      } else {
        this.captcha = 'captcha1.png';
      }
    }
  },
  mounted(){
    this.anim = !this.anim;
    getUserInfo()
        .then(response =>{
          if(response.data.code === 200){
            this.$router.push('/main')
          }
        })
        .catch(function (reason){
          console.log("没有token")
        })
  },
}
</script>

