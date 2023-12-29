<template>
  <Space size="large" wrap>
    <DatePicker type="date" v-model="valid" placeholder="Select date" style="width: 200px" @on-change="change"/>
    {{ valid }}
  </Space>
</template>
<script>
export default {
  data(){
    return{
      valid:'2022-08-08'
    }
  },
  methods:{
    change(){
      this.valid = this.valid.toString();
    }
  }
}
</script>
