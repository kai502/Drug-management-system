
<template>

  <Table
      stripe
      height="520"
      :columns="columns"
      :data="problemList"
  >
    <template #action="{ row, index }">
<!--      <Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">View</Button>-->
      <Button type="error" size="small" @click="remove(index)">删除</Button>
    </template>
  </Table>

  <!--分页菜单-->
  <div style="text-align: center">
    <Page :total="pageTotal" show-total :model-value="pageInfo.pageNum" :page-size="pageInfo.pageSize" @on-change="changePage"/>
  </div>

</template>

<script>
import {deleteProblem, getProblems} from "@/api/Problem";

export default {
  data () {
    return {
      columns: [
        {
          title: '问题药品名称',
          key: 'dname',
        },
        {
          title: '问题描述',
          key: 'article',
        },
        {
          title: '提交时间',
          key: 'time',
        },
        {
          title: '操作',
          slot: 'action',
          width: 100,
          align: 'center',
        }
      ],
      problemList: [],
      pageInfo:{
        keyword:"",
        pageNum:1,
        pageSize:8
      },
      pageTotal:0
    }
  },
  methods:{
    //删除
    remove (index) {
      // console.log(this.drugList[index].dname);
      const problemId = { "pid" : this.problemList[index].pid }
      console.log(problemId);
      deleteProblem(problemId)
          .then( response => {
            if(response.data.code === 200){
              this.$Notice.success({
                title: '删除成功',
                desc: '已经成功删除药品：' + this.problemList[index].dname + ' 的问题'
              });
              this.problemList.splice(index, 1);
              // this.getSupplierApi();
            }else{
              this.$Notice.error({
                title: '删除失败',
                desc: "删除该药品问题出错"
              });
            }
          });
    },
    getProblemsApi(){
      getProblems(this.pageInfo)
          .then(response =>{
            console.log(response.data.data)
            this.problemList = response.data.data.pageData;
            this.pageTotal = response.data.data.pageTotal;
          })
    },
    //更改页数
    changePage(page){
      // console.log(page);
      this.pageInfo.pageNum = page;
      this.getProblemsApi();
    },
  },
  mounted() {
    this.getProblemsApi();
  }
}
</script>
