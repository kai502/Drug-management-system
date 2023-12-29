<style scoped>
.demo-register{
  width: 100%;
  margin: 30px auto !important;
}
.formItem{
  padding: 5px 0;
  width: 500px;
  margin: 0 auto;
}
.formItem-button{
  margin: 20px;
}
</style>

<template>

  <transition name="slide-up">
    <div class="demo-register" v-show="anim">

      <Form ref="formInline" :model="supplierInfo" :rules="ruleInline" inline>

        <FormItem prop="dname" class="formItem">
          <Input type="text" v-model="supplierInfo.sname" placeholder="供应商名称">
            <template #prepend>
              <Icon type="ios-beaker-outline" />
            </template>
          </Input>
        </FormItem>

        <FormItem prop="phoneNumber" class="formItem" >
          <Input type="text" v-model="supplierInfo.phoneNumber" placeholder="供应商电话">
            <template #prepend>
              <Icon type="ios-beaker-outline" />
            </template>
          </Input>
        </FormItem>

        <br>

        <FormItem  class="formItem-button" >
          <Button type="primary" @click="handleSubmit('formInline')">提交更改</Button>
        </FormItem>
        <FormItem  class="formItem-button">
          <Button type="default" @click="back" style="width: 100px">返回</Button>
        </FormItem>

      </Form>

    </div>
  </transition>

</template>

<script>
import {getUserInfo, updateUserInfo} from "@/api/UserApi";
import {Input} from "view-ui-plus";
import {drugAdd} from "@/api/DrugApi";
import {addSupplier} from "@/api/SupplierApi";

export default {
  components: {Input},
  data () {
    return {
      anim:false,
      loading:false,
      supplierInfo:{
        sname:'',
        phoneNumber:'',
      },
      ruleInline: {
        sname: [
          { required: true, message: '供应商名称不能为空', trigger: 'blur' }
        ],
        phoneNumber: [
          { required: true, message: '供应商电话不能为空', trigger: 'blur' },
          { type: 'string', pattern: /^[0-9]+$/ ,message: '电话格式不准确', trigger: 'blur' }
        ]
      },
    }
  },
  methods: {
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          // console.log(this.drugInfo);
          addSupplier(this.supplierInfo)
              .then(response =>{
                // console.log(response.data);
                if(response.data.code === 200){
                  this.$Notice.success({
                    title: '成功',
                    desc: '供应商已添加'
                  });
                  this.$router.push('/main/supplier');
                }
                else if(response.data.code === 801){
                  this.$Notice.error({
                    title: '失败',
                    desc: '该供应商已经存在'
                  });
                }
                else{
                  this.$Notice.error({
                    title: '失败',
                    desc: '添加失败，状态码错误'
                  });
                }
              })
        } else {
          this.$Notice.error({
            title: '失败',
            desc: '添加供应商失败，请检查格式'
          });
        }
      })
    },
    //选择日期触发
    getDate(time){
      // console.log(time)
      this.drugInfo.valid = time;
    },
    back(){
      this.$router.push('/main/supplier');
    },
  },
  mounted(){
    this.anim = !this.anim;
  },
}
</script>
