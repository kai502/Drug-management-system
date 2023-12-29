<style scoped>


</style>

<template>

  <Content :style="{padding: '88px 50px ',minHeight: (windowHeight-80) + 'px'}">
    <Card>
      <div style="min-height: 200px;">
<!--        {{web}}-->
<!--        <component :is="web"></component>-->
            <router-view/>
<!--        <test/>-->
      </div>
    </Card>
  </Content>

</template>

<script>
import Test from "@/test/test.vue";

export default {
  components: {Test},
  data(){
    return{
      windowWidth: document.documentElement.clientWidth,  //实时屏幕宽度
      windowHeight: document.documentElement.clientHeight,   //实时屏幕高度
    }
  },
  // <!--在watch中监听实时宽高-->
  watch: {
    windowHeight (val) {
      let that = this;
      // console.log("实时屏幕高度：",val, that.windowHeight );
    },
    windowWidth (val) {
      let that = this;
      // console.log("实时屏幕宽度：",val, that.windowHeight );
    }
  },

  mounted() {
    var that = this;
    // <!--把window.onresize事件挂在到mounted函数上-->
    window.onresize = () => {
      return (() => {
        window.fullHeight = document.documentElement.clientHeight;
        window.fullWidth = document.documentElement.clientWidth;
        that.windowHeight = window.fullHeight;  // 高
        that.windowWidth = window.fullWidth; // 宽
      })()
    };
  },
}
</script>

