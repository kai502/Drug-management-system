// import './assets/app.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router/router.js'

import ViewUIPlus from 'view-ui-plus'
import 'view-ui-plus/dist/styles/viewuiplus.css'
import './my-theme/index.less';

import axios from 'axios'

const app = createApp(App)

// app.config.globalProperties.$drugStock_Upload = 'no';


app.use(router)
app.use(ViewUIPlus, {
    transfer: true,
    size: 'large',
    capture: false,
    select: {
        arrow: 'md-arrow-dropdown',
        arrowSize: 20
    }
});

app.config.globalProperties.$axios = axios;

app.mount("#app")


// createApp(App).mount('#app')
