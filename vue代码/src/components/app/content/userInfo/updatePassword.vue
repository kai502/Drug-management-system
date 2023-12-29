<style scoped>
.form{
  width: 400px;
  margin: 80px auto ;
  text-align: center;
}


</style>

<template>

  <transition name="slide-up">
    <div class="demo-register" v-show="anim">

      <Form ref="formInline" :model="user" :rules="ruleInline" class="form">

        <FormItem prop="userPwd_Old">
          <Input type="password" password v-model="user.userPwd_Old" placeholder="旧密码"/>
        </FormItem>

        <FormItem prop="userPwd">
          <Input type="password" password v-model="user.userPwd" placeholder="密码"/>
        </FormItem>

        <FormItem prop="userPwd_Confirm">
          <Input type="password" password v-model="user.userPwd_Confirm" placeholder="确认密码"/>
        </FormItem>

        <FormItem>
          <Button type="primary" @click="handleSubmit('formInline')">提交更改</Button>
        </FormItem>

      </Form>

    </div>
  </transition>

</template>

<script>
import {getUserInfo, updateUserPassword} from "@/api/UserApi";
import {Input} from "view-ui-plus";

export default {
  components: {Input},
  data () {
    return {
      anim:false,
      loading:false,
      userPassword:{
        uid:'',
        oldPassword:'',
        newPassword:''
      },
      user:{
        userPwd_Old:'',
        userPwd:'',
        userPwd_Confirm:'',
      },
      ruleInline: {
        userPwd_Old: [
          { required: true, message: '旧密码不能为空', trigger: 'blur' }
        ],
        userPwd: [
          { required: true, message: '新密码不能为空', trigger: 'blur' },
          { type: 'string', min: 6, message: '密码不能少于6位', trigger: 'blur' }
        ],
        userPwd_Confirm: [
          { required: true, message: '确认新密码不能为空', trigger: 'blur' },
          { type: 'string', min: 6, message: '密码不能少于6位', trigger: 'blur' }
        ]
      },
    }
  },
  methods: {
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          if(this.user.userPwd !== this.user.userPwd_Confirm){
            this.$Notice.error({
              title: '失败',
              desc: '两次密码不一致'
            });
          }else{
            this.userPassword.oldPassword = this.user.userPwd_Old;
            this.userPassword.newPassword = this.user.userPwd;

            updateUserPassword(this.userPassword)
                .then(response =>{
                  if(response.data.code === 200){
                    this.$Notice.success({
                      title: '成功',
                      desc: '修改成功'
                    });
                  }
                  else if(response.data.code === 501){
                    this.$Notice.error({
                      title: '失败',
                      desc: '旧密码不正确'
                    });
                  }
                  else{
                    this.$Notice.error({
                      title: '失败',
                      desc: '修改失败，状态码错误'
                    });
                  }
                })
          }
        } else {
          this.$Notice.error({
            title: '失败',
            desc: '修改失败，请检查格式'
          });
        }
      })
    }
  },
  mounted(){
    this.anim = !this.anim;
    getUserInfo()
        .then(response =>{
          // console.log(response.data);
          if(response.data.code === 200){
            const userInfo = response.data.data.userInfo;
            this.userPassword.uid = userInfo.uid;
          }
        })
  },
}
</script>
