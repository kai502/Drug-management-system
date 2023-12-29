<template>

  <div>
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
    <template #action="{ row, index }">
      <Button type="primary" size="small" style="margin-right: 5px" @click="outReceipt(index)">出售</Button>
      <Button type="primary" size="small" style="margin-right: 5px" @click="inReceipt(index)">进货</Button>
    </template>

  </Table>
  <!--分页菜单-->
  <div style="text-align: center">
    <Page :total="pageTotal" show-total :model-value="pageNum" :page-size="pageInfo.pageSize" @on-change="changePage"/>
  </div>

  <!--出库窗口-->
  <Modal
      title="药品问题反馈"
      v-model="modal"
      :styles="{top: '25%'}"
      @on-ok="ok"
  >
    <List>
      <ListItem>
        <Input type="text" v-model="receiptNumber" placeholder="请输入问题描述" />
      </ListItem>
    </List>
  </Modal>

</template>

<script>
import {Input, Upload} from "view-ui-plus";
import {getDrugs} from "@/api/DrugApi";
import {outInReceipt} from "@/api/receipt";
import {getUserInfo} from "@/api/UserApi";

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
        pageSize: 7,     // 页大小
      },
      columns: [
        {
          title: '药品名称',
          key: 'dname'
        },
        {
          title: '当前药品库存',
          key: 'number'
        },
        {
          title: '操作',
          slot: 'action',
          width: 250,
          align: 'center'
        },
      ],
      drugList: [],
      modal:false,
      receiptType:0,
      drugId:'',
      drugName:'',
      receiptNumber:0,
      userFullName:'',
    }
  },
  methods: {
    //点击出库药品
    outReceipt(index){
      this.modal=true;
      this.drugId = this.drugList[index].did;
      this.drugName = this.drugList[index].dname;
      this.receiptType = 0;
    },
    //点击入库药品
    inReceipt(index){
      this.modal=true;
      this.drugId = this.drugList[index].did;
      this.drugName = this.drugList[index].dname;
      this.receiptType = 1;
    },
    //确定
    ok(){
      const date = new Date();
      console.log("ok");
      const receiptObject = {
        "did":this.drugId,
        "type":this.receiptType,
        "number":this.receiptNumber,
        "administrator":this.userFullName,
        "time":date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()
      }
      outInReceipt(receiptObject)
          .then(response =>{
            if(response.data.code === 200){
              if(this.receiptType === 0){
                this.$Notice.success({
                  title: '出库成功',
                  desc: this.drugName+"已出售 "+this.receiptNumber + "件"
                });
              }else{
                this.$Notice.success({
                  title: 'ji成功',
                  desc: this.drugName+"已进货 "+this.receiptNumber + "件"
                });
              }
              const pageNum = this.pageNum;
              this.pageInfo.keyword = '';
              this.getDrugsApi();
              this.pageNum = pageNum;
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
    getUserInfo()
        .then(response =>{
          this.userFullName = response.data.data.userInfo.fullName;
        })
  }
}
</script>
