<style>

</style>

<template>

  <div>
    <router-link to="supplierAdd"><Button type="primary" size="default" style="margin: 8px">添加供应商</Button></router-link>
    <Input placeholder="搜索" v-model="pageInfo.keyword" style="width: auto; margin: 8px">
      <template #suffix>
        <Icon type="ios-search" />
      </template>
    </Input>
  </div>

  <Table
      border
      stripe
      height="500"
      :columns="columns"
      :data="supplierList"
  >
    <template #action="{ row, index }">
      <Button type="primary" size="small" style="margin: 5px" @click="update(index)">修改</Button>
      <Button type="error" size="small" style="margin: 5px" @click="remove(index)">删除</Button>
    </template>
  </Table>

  <!--分页菜单-->
  <div style="text-align: center">
    <Page :total="pageTotal" show-total :model-value="pageInfo.pageNum" :page-size="pageInfo.pageSize" @on-change="changePage"/>
  </div>

</template>
<script>
import {deleteSupplier, getSuppliers} from "@/api/SupplierApi";

export default {
  data () {
    return {
      columns: [
        {
          title: '供应商名称',
          key: 'sname'
        },
        {
          title: '供应商电话',
          key: 'phoneNumber',
        },
        {
          title: '操作',
          slot: 'action',
          width: 150,
          align: 'center'
        },
      ],
      supplierList: [],
      pageInfo:{
        keyword: '',
        pageNum: 1,
        pageSize: 7
      },
      pageTotal:0,
    }
  },
  methods:{
    //修改
    update(index){
      this.$router.push({ name: 'supplierUpdate', params:{supplierId : this.supplierList[index].sid} })
    },
    //删除
    remove (index) {
      // console.log(this.drugList[index].dname);
      const SupplierName = { "sname" : this.supplierList[index].sname }
      deleteSupplier(SupplierName)
          .then( response => {
            if(response.data.code === 200){
              this.$Notice.success({
                title: '删除成功',
                desc: '已经成功删除供应商：'+ this.supplierList[index].sname
              });
              this.supplierList.splice(index, 1);
              // this.getSupplierApi();
            }else{
              this.$Notice.success({
                title: '删除失败',
                desc: "删除供应商出错"
              });
            }

          });
    },
    changePage(page){
      this.pageInfo.pageNum = page;
      this.getSupplierApi();
    },
    getSupplierApi(){
      getSuppliers(this.pageInfo)
          .then(response => {
            // console.log(response.data)
            this.pageTotal = response.data.data.pageTotal;
            this.pageInfo.pageNum = response.data.data.pageNum;
            this.supplierList = response.data.data.pageData;
          })
    }
  },
  watch:{
    'pageInfo.keyword'(newVal,oldVal){
      // console.log(newVal)
      this.pageInfo.keyword = newVal;
      this.getSupplierApi();
    }
  },
  mounted() {
    this.getSupplierApi();
  }
}
</script>
