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
      :data="receiptList"
  >
    <template #action="{ row, index }">
      <Button type="error" size="small" style="margin-right: 5px" @click="remove(index)">删除</Button>
    </template>

  </Table>
  <!--分页菜单-->
  <div style="text-align: center">
    <Page :total="pageTotal" show-total :model-value="pageNum" :page-size="pageInfo.pageSize" @on-change="changePage"/>
  </div>


</template>

<script>
import {Input, Upload} from "view-ui-plus";
import {deleteReceipts, getReceipts} from "@/api/receipt";
import {deleteProblem} from "@/api/Problem";


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
          title: '操作',
          key: 'type'
        },
        {
          title: '数量',
          key: 'number'
        },
        {
          title: '操作人员',
          key: 'administrator'
        },
        {
          title: '操作时间',
          key: 'time'
        },
        {
          title: '操作',
          slot: 'action',
          width: 150,
          align: 'center'
        },
      ],
      receiptList: [],
      modal:false,
      receiptType:0,
    }
  },
  methods: {
    //删除
    remove (index) {
      // console.log(this.drugList[index].dname);
      const receiptId = { "rid" : this.receiptList[index].rid }
      // console.log(receiptId);
      deleteReceipts(receiptId)
          .then( response => {
            if(response.data.code === 200){
              this.$Notice.success({
                title: '删除成功',
                desc: '已经成功删除药品：' + this.receiptList[index].dname + ' 的出入库记录'
              });
              this.receiptList.splice(index, 1);
              // this.getSupplierApi();
            }else{
              this.$Notice.error({
                title: '删除失败',
                desc: "删除该药品出入库记录出错"
              });
            }
          });
    },
    //更改页数
    changePage(page){
      // console.log(page);
      this.pageInfo.pageNum = page;
      this.getReceiptsApi();
    },
    getReceiptsApi(){
      getReceipts(this.pageInfo)
          .then(response=>{
            this.receiptList = Array.from(response.data.data.pageData);
            this.pageTotal = response.data.data.pageTotal;
          })
    },
  },
  watch:{
    'pageInfo.keyword'(newVal,oldVal){
      console.log(newVal)
      this.pageInfo.keyword = newVal;
      this.getReceiptsApi();
    }
  },
  mounted() {
    this.getReceiptsApi();
  }
}
</script>
