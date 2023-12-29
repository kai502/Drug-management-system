<style>
.main{
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
</style>

<template>
  <div class="main">
    <Layout>

      <Header/>
      <router-view/>
      <Footer/>

    </Layout>
  </div>
</template>


<script>

import Contenter from "@/components/app/Contenter.vue";
import Header from "@/components/app/Header.vue";
import Footer from "@/components/app/Footer.vue";

export default {
  components: {Contenter, Header, Footer},
  data(){
    return{

    }
  },
  methods:{

  }
}
</script>