import { createApp } from 'vue'
import App from './App.vue'
import './index.css'
import VueAxios from "vue-axios";
import router from './router/index';
import ElementPlus, { ElMessage } from 'element-plus';
import 'element-plus/theme-chalk/base.css';
import 'element-plus/theme-chalk/index.css';
import 'element-plus/theme-chalk/display.css';
import '../src/assets/css/main.css';
import Validator from './assets/utils/common';
import axios from "axios";
import pinia from "./store";

const app = createApp(App);

app.use(router);
app.use(pinia);
app.use(ElementPlus);
app.use(VueAxios, axios)

axios.defaults.withCredentials = true;
axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8;multipart/form-data;application/json';

app.config.globalProperties.$ajax = axios;
app.config.globalProperties.$Utils = ElMessage;
app.config.globalProperties.$Validator = Validator;

app.mount('#app');
