<style>
.demo-register{
  width: 100%;
  margin: 30px auto !important;
}
.formItem{
  padding:5px 5px;
  width: 250px;
}
</style>

<template>

  <transition name="slide-up">
    <div class="demo-register" v-show="anim">

      <Form ref="formInline" :model="drugInfo" :rules="ruleInline" inline>

        <FormItem prop="dname" class="formItem">
          <Input type="text" v-model="drugInfo.dname" placeholder="药品名称">
            <template #prepend>
              <Icon type="ios-beaker-outline" />
            </template>
          </Input>
        </FormItem>

        <FormItem prop="sid" class="formItem">
          <Select v-model="drugInfo.sid" clearable placeholder="选择供应商">
            <Option v-for="item in supplierList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>

        <FormItem prop="type" class="formItem">
          <Select v-model="drugInfo.type" clearable placeholder="选择类型">
            <Option v-for="item in cityList2" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>

        <FormItem prop="size" class="formItem">
          <Input type="text" v-model="drugInfo.size" placeholder="规格">
            <template #prepend>
              <Icon type="ios-albums-outline" />
            </template>
          </Input>
        </FormItem>

        <FormItem prop="number" class="formItem">
          <InputNumber v-model="drugInfo.number" controls-outside style="width: 100%" placeholder="数量"/>
        </FormItem>

        <FormItem prop="price" class="formItem">
          <InputNumber v-model="drugInfo.price" controls-outside style="width: 100%" placeholder="价格"/>
        </FormItem>

        <FormItem prop="valid" class="formItem">
          <DatePicker type="date" v-model="drugInfo.valid" @on-change="getDate" placeholder="选择有效日期" :options="options" />
        </FormItem>

        <FormItem prop="details" style="width: 80%">
          <Input type="textarea" v-model="drugInfo.details" :autosize="{minRows: 4,maxRows: 8}" placeholder="描述" >
            <template #prepend>
              <Icon type="ios-book-outline" />
            </template>
          </Input>
        </FormItem>

        <br>

        <FormItem>
          <Button type="primary" @click="handleSubmit('formInline')">提交更改</Button>
        </FormItem>
        <FormItem>
          <Button type="default" @click="back" style="width: 100px">返回</Button>
        </FormItem>

      </Form>

    </div>
  </transition>

</template>

<script>
import {Input} from "view-ui-plus";
import {drugAdd} from "@/api/DrugApi";
import {getSupplierAll} from "@/api/SupplierApi";

export default {
  components: {Input},
  data () {
    return {
      anim:false,
      loading:false,
      drugInfo:{
        sid:'',
        dname:'',
        type:'',
        size:'',
        number:0,
        price:0,
        valid:'',
        details:''
      },
      ruleInline: {
        sid: [
          { required: true,  type: "number",message: '供应商不能为空', trigger: 'blur' }
        ],
        dname: [
          { required: true, message: '药品名称不能为空', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '药品类型不能为空', trigger: 'blur' },
        ],
        size: [
          { required: true, message: '药品规格不能为空', trigger: 'blur' }
        ],
        // number: [
        //   { required: true, message: '药品数量不能空', trigger: 'blur' },
        //   { type: 'string', pattern: /^[0-9]+$/ ,message: '只能输入整数', trigger: 'blur' }
        // ],
        // price: [
        //   { required: true, message: '药品价格不能为空', trigger: 'blur' },
        //   { type: 'string', pattern: /^[0-9]+$/ ,message: '只能输入整数', trigger: 'blur' }
        // ],
        valid: [
          { required: true, message: '药品有效期不能为空', trigger: 'blur',type:'date',min:1 }
        ],
        details: [
          { required: true, message: '描述不能为空', trigger: 'blur' }
        ],
      },
      options: {
        disabledDate (date) {
          return date && date.valueOf() < Date.now() - 86400000;
        }
      },
      supplierList: [],
      cityList2: [
        {
          value: '处方',
          label: '处方'
        },
        {
          value: '非处方',
          label: '非处方'
        }
      ],
    }
  },
  methods: {
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          // console.log(this.drugInfo);
          drugAdd(this.drugInfo)
              .then(response =>{
                // console.log(response.data);
                if(response.data.code === 200){
                  this.$Notice.success({
                    title: '成功',
                    desc: '药品已添加'
                  });
                  this.$router.push('/main/drugStock');
                }
                else if(response.data.code === 701){
                  this.$Notice.error({
                    title: '失败',
                    desc: '该药品已经存在'
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
            desc: '添加药品失败，请检查格式'
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
      this.$router.push('/main/drugStock');
    },
  },
  mounted(){
    this.anim = !this.anim;
    getSupplierAll()
        .then(response =>{
          for(let i = 0; i<response.data.data.length; i++){
            const supplierObject = {"value":response.data.data[i].sid,"label":response.data.data[i].sname}
            this.supplierList[i] = supplierObject;
          }
          // console.log(this.supplierList);
          // console.log(this.cityList2)
        })

  },
}
</script>
