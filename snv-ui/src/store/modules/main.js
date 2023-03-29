import { defineStore } from "pinia/dist/pinia";
import { defineAsyncComponent } from "vue";

export const mainStore = defineStore('main', {
	persist: {
		key: 'storeKey',
		storage: sessionStorage,
		paths: ['winWidth', 'winHeight', 'isLogin', 'Header', 'Footer']
	},
	state: () => {
		/*原始数据*/
		return {
			winWidth: '',
			winHeight: '',
			isLogin: true,
			user: 'test',
			email: '',
			firstName: 'test',
			lastName: 'test',
			lineData: null,
			timeLineData: null,
		};
	},
	getters: {
		/*getter是个方法对象集合*/

	},
	actions: {
		/*actions是业务逻辑处理*/
		saveWidth(winWidth) {
			this.winWidth = winWidth;
		},
		saveHeight(winHeight) {
			this.winHeight = winHeight;
		},
		setIsLogin(isLogin) {
			this.isLogin = isLogin;
		},
		setUser(user) {
			this.user = user;
		},
		setEmail(arg) {
			this.email = arg;
		}
	}
})