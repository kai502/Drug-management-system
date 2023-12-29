<style scoped>
.upload{
  margin:0 auto;
  width: 120px;
  height: 120px;
}
.image{
  margin-top: 5px;
  border-radius: 30px;
  overflow:hidden
}
</style>

<template>

  <transition name="slide-down">

  <div v-show="anim" style="margin: 15px">

    <!-- 已登录-->
    <div  :style="{textAlign: 'center' }">
      <!--头像上传-->
      <div class="upload">
        <Upload
            multiple
            :on-exceeded-size="handleMaxSize"
            :max-size="1024"
            :on-success="handleSuccess"
            type="drag"
            :format="['jpg','jpeg','png']"
            :on-format-error="handleFormatError"
            :data=userInfo
            :show-upload-list="false"
            action="http://localhost:8080/user/sendImage"
        >
          <Image
              :src="'src/assets/userImage/user_'+userInfo.userId+'.png?randUrl='+Math.ceil(new Date()/3600000)"
              width="100px"
              height="100px"
              class="image">
            <!-- 找不到图片显示-->
            <template #error>
              <div style="padding: 20px 0">
                <Icon type="ios-camera" size="20"></Icon>
                <p>点击上传头像图片</p>
              </div>
            </template>
          </Image>

        </Upload>
      </div>

      <div style="margin: 30px auto;width: 600px">
        <UpdateUserInfo/>
      </div>

    </div>
  </div>
  <!--已登录-->
  </transition>

</template>


<script>
import {getUserInfo} from "@/api/UserApi";
import UpdateUserInfo from "@/components/app/content/userInfo/UpdateUserInfo.vue";

export default {
  components: {UpdateUserInfo},
  data(){
    return{
      anim:false,
      fullName: "",

      userInfo:{
        userId:1
      },
      isImage:true,
      visible:true,
    }
  },
  methods:{
    handleSuccess (res, file) {
      this.$router.push('upload_UserMain')
    },
    handleFormatError (file) {
      this.$Notice.warning({
        title: '文件格式错误',
        desc: file.name + ' 不是png、jgp、jpeg格式的文件'
      });
    },
    handleMaxSize (file) {
      this.$Notice.warning({
        title: '文件大小不合格',
        desc: file.name + ' 太大了，必须小于1M'
      });
    },
    ImageError(){
      console.log("1111")
      this.isImage = false;
    },
  },
  mounted(){
    this.anim = true;
    getUserInfo()
        .then(response =>{
          // console.log(response.data);
          if(response.data.code === 200){
            this.message = ""
            this.fullName = response.data.data.userInfo.fullName;
            this.userInfo.userId = response.data.data.userInfo.uid;
          }
          else{
            this.$router.push("/login")
          }
        })

  },

}
</script>
