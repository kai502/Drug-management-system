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
import {Input} from "view-ui-plus";
import {getSupplier, updateSupplier} from "@/api/SupplierApi";

export default {
  components: {Input},
  data () {
    return {
      anim:false,
      loading:false,
      supplierInfo:{
        sid:'',
        sname:'',
        phoneNumber: '',
      },
      ruleInline: {
        sname: [
          { required: true, message: '供应商名称不能为空', trigger: 'blur' }
        ],
        phoneNumber: [
          { required: true, message: '供应商电话不能为空', trigger: 'blur' },
          // { type: 'number' ,message: '电话格式不准确', trigger: 'blur' }
        ]
      },
    }
  },
  created() {
    this.supplierInfo.sid = this.$route.params.supplierId
  },
  methods: {
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          // console.log(this.drugInfo);
          updateSupplier(this.supplierInfo)
              .then(response =>{
                if(response.data.code == 200){
                  this.$Notice.success({
                    title: '成功',
                    desc: '修改供应商成功'
                  });
                }
                else if(response.data.code == 803){
                  this.$Notice.error({
                    title: '失败',
                    desc: '该供应商已经存在'
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
    back(){
      this.$router.push('/main/supplier');
    },
    getSupplierApi(){
      getSupplier(this.supplierInfo)
          .then(repsonse =>{
            this.supplierInfo.sname=repsonse.data.data.sname;
            this.supplierInfo.phoneNumber = repsonse.data.data.phoneNumber.toString();
          })
    }
  },
  mounted(){
    this.anim = !this.anim;
    this.getSupplierApi();
  },
}
</script>
