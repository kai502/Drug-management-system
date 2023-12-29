import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  css: {
    preprocessorOptions: {
      less: {
        javascriptEnabled: true,/*这个可以解决less方法无法执行的问题*/
        rewriteUrls:'all',/*这个解决字体图标无法显示的问题*/
        math: 'always'
      }
    }
  },
  // 打包基本路径
  base: './',
  // 跨域的代理中转服务器服务
  // proxy: {
  //   "/api":{   // /vue代理target
  //     target: 'http://localhost:8080/',      // 后端接口的根目录
  //     // secure: true,           // 如果是 https ,需要开启这个选项，http为false
  //     changeOrigin: true,        // 是否跨域
  //     pathRewrite: {            // 是否重写路径，看代理前端路径是否与后端路径一致
  //       '^/':'', //将所有含/路径的，去掉/转发给服务器
  //     }
  //   }
  // },

  // css: {
  //   // css预处理器
  //   preprocessorOptions: {
  //     less: {
  //       charset: false,
  //       additionalData: '@import "./src/styles/index.less";',
  //     },
  //   },
  // }

})
