<template>

  <div>
    <router-link to="drugAdd"><Button type="primary" size="default" style="margin: 8px">添加药品</Button></router-link>
    <Input placeholder="搜索" v-model="pageInfo.keyword" style="width: auto; margin: 8px">
      <template #suffix>
        <Icon type="ios-search" />
      </template>
    </Input>
  </div>


  <Table
      stripe
      height="500"
      :columns="columns"
      :data="drugList"
  >
    <template #image="{ row, index }">

      <upload
          style="height: 70px"
          type="drag"
          :max-size="1024"
          :format="['jpg','jpeg','png']"
          :show-upload-list="false"
          :on-success="handleSuccess"
          :data="getDidObject(index)"
          action="http://localhost:8080/drug/addDrugImage"
      >
        <Image
            style="height: 70px"
            :src="'src/assets/drugImage/drug_'+ drugList[index].did +'.png?randUrl='+(Math.random() * 1000)"
        >
          <!-- 找不到图片显示-->
          <template #error >
            <div>
              <Icon type="ios-cloud-upload-outline" />
            </div>
          </template>
        </Image>
      </upload>

    </template>
    <template #action="{ row, index }">
      <Button type="primary" size="small" style="margin-right: 5px" @click="update(index)">修改</Button>
      <Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">详情</Button>
      <Button type="warning" size="small" style="margin-right: 5px" @click="mark(index)">问题</Button>
      <Button type="error" size="small" style="margin-right: 5px" @click="remove(index)">删除</Button>
    </template>

  </Table>
<!--分页菜单-->
  <div style="text-align: center">
    <Page :total="pageTotal" show-total :model-value="pageNum" :page-size="pageInfo.pageSize" @on-change="changePage"/>
  </div>

<!--详情弹窗-->
  <Modal
      title="药品详情"
      v-model="modal"
      :styles="{top: '0px'}">
    <Image
        style="height: 70px;width: 300px"
        :src="'src/assets/drugImage/drug_'+ drugId+'.png?randUrl='+(Math.random() * 1000)"
    >
      <!-- 找不到图片显示-->
      <template #error >
        <div>
          暂未上传相关药品的图片
        </div>
      </template>
    </Image>
    <List>
      <ListItem>
        <ListItemMeta title="药品名称" :description="drugName" />
      </ListItem>
      <ListItem>
        <ListItemMeta title="药品供应商" :description="drugSupplier" />
      </ListItem>
      <ListItem>
        <ListItemMeta title="药品类型" :description="drugType" />
      </ListItem>
      <ListItem>
        <ListItemMeta title="药品规格" :description="drugSize" />
      </ListItem>
      <ListItem>
        <ListItemMeta title="药品数量" :description="drugNumber" />
      </ListItem>
      <ListItem>
        <ListItemMeta title="药品价格" :description="drugPrice" />
      </ListItem>
      <ListItem>
        <ListItemMeta title="药品描述" :description="drugDetail" />
      </ListItem>
      <ListItem>
        <ListItemMeta title="药品有效期" :description="drugValid" />
      </ListItem>
    </List>
  </Modal>

  <!--问题反馈窗口-->
  <Modal
      title="药品问题反馈"
      v-model="modal2"
      :styles="{top: '25%'}"
      @on-ok="ok"
  >
    <List>
      <ListItem>
        <Input type="text" v-model="article" placeholder="请输入问题描述" />
      </ListItem>
    </List>
  </Modal>

</template>

<script>
import {Input, Upload} from "view-ui-plus";
import {deleteDrug, getDrugs} from "@/api/DrugApi";
import {getSupplier} from "@/api/SupplierApi";
import {addProblem} from "@/api/Problem";

export default {
  components: {Input, Upload },
  data () {
    return {
      pageNum: 1,
      pageTotal:1,
      pageInfo:{
        keyword: "", // 搜索标题关键字
        sid: '',           // 新闻类型
        pageNum: 1,        // 页码数
        pageSize: 6,     // 页大小
      },
      columns: [
        {
          title: '药品图片',
          slot: 'image',
          width: 120,
          height: 120,
          align: 'center'
        },
        {
          title: '药品名称',
          key: 'dname'
        },
        {
          title: '药品数量',
          key: 'number'
        },
        {
          title: '药品价格',
          key: 'price',
          sortable: true
        },
        {
          title: '有效期',
          key: 'valid',
          sortable: true
        },
        {
          title: '操作',
          slot: 'action',
          width: 150,
          align: 'center'
        },
      ],
      drugList: [],
      modal:false,
      drugId:'',
      drugName:'',
      drugSid:'',
      drugSupplier:'',
      drugType:'',
      drugSize:'',
      drugNumber:'',
      drugPrice:'',
      drugValid:'',
      drugDetail:'',

      modal2:false,
      article:"",
    }
  },
  methods: {
    //更改
    update(index){
      this.$router.push({ name: 'drugUpdate', params:{drugId:this.drugList[index].did} })
    },
    //详情
    show (index) {
      this.drugId = this.drugList[index].did;
      this.drugName = this.drugList[index].dname;
      this.drugSid = this.drugList[index].sid;
      this.drugType = this.drugList[index].type;
      this.drugSize = this.drugList[index].size;
      this.drugNumber = this.drugList[index].number;
      this.drugPrice = this.drugList[index].price;
      this.drugValid = this.drugList[index].valid;
      this.drugDetail = this.drugList[index].details
      this.modal = true;
      const supplierIdObject = {"sid":this.drugSid};
      getSupplier(supplierIdObject)
          .then(response =>{
            this.drugSupplier = response.data.data.sname;
          })
    },
    //删除
    remove (index) {
      // console.log(this.drugList[index].dname);
      const DrugName = { "dname" : this.drugList[index].dname }
      deleteDrug(DrugName)
          .then( response => {
            if(response.data.code === 200){
              this.$Notice.success({
                title: '删除成功',
                desc: '已经成功删除药品：'+ this.drugList[index].dname
              });
              this.drugList.splice(index, 1);
              // this.getSupplierApi();
            }else{
              this.$Notice.error({
                title: '删除失败',
                desc: "删除药品出错"
              });
            }
          });
    },
    //点击标记为问题药品 //确定
    mark(index){
      this.modal2=true;
      this.drugId = this.drugList[index].did;
      this.drugName = this.drugList[index].dname;
    },
    ok(){
      const date = new Date();
      console.log("ok");
      const problemObject = {
        "did":this.drugId,
        "article":this.article,
        "time":date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()
      }
      addProblem(problemObject)
          .then(response => {
            if(response.data.code === 200){
              this.$Notice.success({
                title: '问题已提交',
                desc: this.drugName + " 的问题已经提交"
              })
            }
          })
    },
    //更改页数
    changePage(page){
      // console.log(page);
      this.pageInfo.pageNum = page;
      this.getDrugsApi();
    },
    getDrugsApi(){
      getDrugs(this.pageInfo)
          .then(response=>{
            this.drugList = Array.from(response.data.data.pageData);
            this.pageNum = response.data.data.pageNum;
            this.pageTotal = response.data.data.totalSize;
          })
    },
    //上传成功刷新页面
    handleSuccess (res, file) {
      const pageNum = this.pageNum;
      this.pageInfo.keyword = '';
      this.getDrugsApi();
      this.pageNum = pageNum;
    },
    //上传图片，包装附带上传的值
    getDidObject(index){
      const did = this.drugList[index].did
      return {"drugId":did};
    }
  },
  watch:{
    'pageInfo.keyword'(newVal,oldVal){
        console.log(newVal)
        this.pageInfo.keyword = newVal;
        this.getDrugsApi();
    }
  },
  mounted() {
    this.getDrugsApi();
  }
}
</script>
