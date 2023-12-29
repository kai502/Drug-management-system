<template>
      <Menu :theme="'light'">

        <Submenu name="1">
          <template #title>
            <Icon type="ios-paper" />
            个人信息管理
          </template>
          <router-link to="/main/Message"><MenuItem name="1-1">收信箱</MenuItem></router-link>
          <router-link to="/main/userMain"><MenuItem name="1-2">修改个人信息</MenuItem></router-link>
          <router-link to="/main/updatePassword"><MenuItem name="1-3">修改密码</MenuItem></router-link>
        </Submenu>

        <Submenu name="2">
          <template #title>
            <Icon type="ios-people" />
            药品库存管理
          </template>
          <router-link to="/main/drugStock"><MenuItem name="2-1">药品库存</MenuItem></router-link>
          <router-link to="/main/Supplier"><MenuItem name="2-2">供应商</MenuItem></router-link>
          <router-link to="/main/problemList"><MenuItem name="2-3">问题药品记录</MenuItem></router-link>
          <router-link to="/main/receiptAdd"><MenuItem name="2-4">药品销售管理</MenuItem></router-link>
          <router-link to="/main/receiptList"><MenuItem name="2-6">药品明细查询</MenuItem></router-link>
        </Submenu>

      </Menu>
</template>
<script>
export default {
  data () {
    return {

    }
  }
}
</script>
