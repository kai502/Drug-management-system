<style>


</style>

<template>

  <transition name="slide-up">
    <div class="demo-register" v-show="anim">

      <Form ref="formInline" :model="userInfo" :rules="ruleInline" inline>

        <FormItem prop="username">
          <Input type="text" v-model="userInfo.username" placeholder="账号">
            <template #prepend>
              <Icon type="ios-paper-plane-outline" />
            </template>
          </Input>
        </FormItem>

        <FormItem prop="fullName">
          <Input type="text" v-model="userInfo.fullName" placeholder="用户名">
            <template #prepend>
              <Icon type="ios-person-add-outline" />
            </template>
          </Input>
        </FormItem>

        <FormItem prop="email">
          <Input type="text" v-model="userInfo.email" placeholder="邮箱">
            <template #prepend>
              <Icon type="ios-mail-open-outline" />
            </template>
          </Input>
        </FormItem>

        <FormItem prop="phoneNumber">
          <Input type="text" v-model="userInfo.phoneNumber" placeholder="电话号">
            <template #prepend>
              <Icon type="ios-phone-portrait" />
            </template>
          </Input>
        </FormItem>

        <FormItem>
          <Button type="primary" @click="handleSubmit('formInline')">提交更改</Button>
        </FormItem>

      </Form>

    </div>
  </transition>

</template>

<script>
import {getUserInfo, updateUserInfo} from "@/api/UserApi";
import {Input} from "view-ui-plus";

export default {
  components: {Input},
  data () {
    return {
      anim:false,
      loading:false,
      userInfo:{
        uid:'',
        username: "",
        fullName: "",
        email: "",
        phoneNumber: "",
      },
      ruleInline: {
        username: [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ],
        fullName: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' }
        ],
        phoneNumber: [
          { required: true, message: '电话不能为空', trigger: 'blur' },
          { type: 'string', max:11,min: 11, message: '电话格式不正确', trigger: 'blur' }
        ],
      },
    }
  },
  methods: {
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          updateUserInfo(this.userInfo)
              .then(response =>{
                if(response.data.code === 200){
                  this.$Notice.success({
                    title: '成功',
                    desc: '修改成功'
                  });
                }
                else if(response.data.code === 500){
                  this.$Notice.error({
                    title: '失败',
                    desc: '已经存在相同的用户名'
                  });
                }
                else{
                  this.$Notice.error({
                    title: '失败',
                    desc: '修改失败,状态码错误'
                  });
                }
              })
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
            this.userInfo.uid = userInfo.uid;
            this.userInfo.username = userInfo.username;
            this.userInfo.fullName = userInfo.fullName;
            this.userInfo.email = userInfo.email;
            this.userInfo.phoneNumber = userInfo.phoneNumber;
          }
        })
  },
}
</script>
