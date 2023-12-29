<style scoped>
.demo-register{
  width: 400px;
  margin: 0 auto !important;
}
.demo-register .ivu-poptip, .demo-register .ivu-poptip-rel {
  display: block;
}
.demo-register-tip{
  text-align: left;
}
.demo-register-tip-title{
  font-size: 14px;
}
.demo-register-tip-desc{
  white-space: initial;
  font-size: 14px;
  margin-top: 6px;
}
</style>

<template>

  <transition name="slide-up">
    <div class="demo-register" v-show="anim">

      <Title style="text-align: center" :level="2">注册</Title>

      <Login ref="form" @on-submit="handleSubmit">
<!--用户名-->
        <UserName name="username" placeholder="请输入账号"/>
<!--密码框-->
        <Poptip trigger="focus" placement="right" width="240">
          <Password name="password" :rules="passwordRule" placeholder="至少6位密码，区分大小写" @on-change="handleChangePassword" style="width: 400px"/>
          <template #content>
            <div class="demo-register-tip">
              <div class="demo-register-tip-title" :class="passwordTip.class">
                强度：{{ passwordTip.strong }}
              </div>
              <Progress :percent="passwordTip.percent" hide-info :stroke-width="6" :stroke-color="passwordTip.color" />
              <div class="demo-register-tip-desc">
                请至少输入 6 个字符。请不要使用容易被猜到的密码。
              </div>
            </div>
          </template>
        </Poptip>
<!--确认密码-->
        <Password name="passwordConfirm" :rules="passwordConfirmRule" placeholder="确认密码" />
<!--用户名-->
        <UserName name="fullName" />
<!--邮箱-->
        <Email name="mail" />
<!--手机号-->
        <Mobile name="phone"/>
        <Submit :loading="loading">注册</Submit>
      </Login>
    </div>
  </transition>

</template>

<script>
import {loginApi, regist} from "@/api/UserApi";

export default {
  data () {
    const validatePassCheck = (rule, value, callback) => {
      if (value !== this.$refs.form.formValidate.password) {
        callback(new Error('两次输入的密码不匹配！'));
      } else {
        callback();
      }
    };

    return {
      anim:false,
      loading:false,
      userInfo:{
        username: "",
        userPwd: "",
        fullName: "",
        email: "",
        phoneNumber: "",
      },
      passwordRule: [
        {
          required: true, message: '密码不能为空！', trigger: 'change'
        },
        {
          min: 6, message: '密码不能少于6位！', trigger: 'change'
        }
      ],
      passwordConfirmRule: [
        {
          required: true, message: '确认密码不能为空！', trigger: 'change'
        },
        { validator: validatePassCheck, trigger: 'change' }
      ],
      // 密码长度，在密码强度提示时作为判断依据
      passwordLen: 0
    }
  },
  computed: {
    // 密码强度提示文案等
    passwordTip () {
      let strong = '强';
      let className = 'strong';
      let percent = this.passwordLen > 10 ? 10 : this.passwordLen;
      let color = '#19be6b';

      if (this.passwordLen < 6) {
        strong = '太短';
        className = 'low';
        color = '#ed4014';
      } else if (this.passwordLen < 10) {
        strong = '中';
        className = 'medium';
        color = '#ff9900';
      }

      return {
        strong,
        class: 'demo-register-tip-' + this.passwordLen < 6 ? 'low' : (this.passwordLen < 10 ? 'medium' : 'strong'),
        percent: percent * 10,
        color
      }
    }
  },
  methods: {
    handleChangePassword (val) {
      this.passwordLen = val.length;
    },
    handleSubmit (valid, { username, password, fullName, mail, phone }) {

      this.userInfo.username = username
      this.userInfo.userPwd = password
      this.userInfo.fullName = fullName
      this.userInfo.email = mail
      this.userInfo.phoneNumber = phone
      console.log(this.userInfo);

      if(valid){
        regist(this.userInfo)
            .then( response => {
              console.log(response.data);

              if(response.data.code === 200){
                // this.$Modal.info({
                //   title:"注册成功",
                //   content: '正在自动登录...'
                // })
                this.loading = true;
                loginApi(this.userInfo).then(response =>{
                  if(response.data.data != null){
                    const token = response.data.data.token
                    localStorage.setItem('token', token)
                  }
                  setTimeout(function (){
                      this.$router.push("/main")
                    }.bind(this),1500
                  )
                })

              }else{
                this.$Modal.info({
                  title:"注册失败",
                  content: response.data.message
                })
              }

            })
      }


      // if (valid) {
      //   this.$Modal.info({
      //     title: '输入的内容如下：',
      //     content: 'mail: ' + mail + ' | password: ' + password
      //   });
      // }
    }
  },
  mounted(){
    this.anim = !this.anim;
  },
}
</script>
