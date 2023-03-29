<template>
	<el-row id="header">
		<el-col :span="2" class="nav-first">
			<a @click="clickEvent('index')">SNV</a>
		</el-col>
		<el-col :span="1" class="nav-second">
			<span :style="colors.color1" @click="clickEvent('index')" id="first">Home</span>
		</el-col>
		<el-col :span="2" class="nav-second">
			<span :style="colors.color2" @click="clickEvent('faq')" id="second">FAQ</span>
		</el-col>
		<el-col :span="18">
			<el-row justify="end">
				<el-col :span="1" class="nav-third" v-if="store.isLogin">
					<el-popover placement="bottom" trigger="hover" popper-class="popoverClass">
						<template #reference>
							<span @click="" style="text-align: right">{{ userInfo.firstName }}</span>
						</template>
						<template #default>
							<div class="logout-style" @click="go2Message">profile</div>
							<div class="logout-style" @click="logout">logout</div>
						</template>
					</el-popover>
				</el-col>
				<el-col :span="1" class="nav-third" v-else>
					<div style="text-align: right">login</div>
				</el-col>
			</el-row>
		</el-col>
	</el-row>
</template>

<script lang="ts" setup>
    import {useRouter} from "vue-router";
    import {mainStore} from '../store/modules/main';
    import {ref, reactive, onMounted, getCurrentInstance, watchEffect, watch} from "vue";

    const router = useRouter();
    const store = mainStore();
    const {proxy} = getCurrentInstance();
    const {globalProperties} = getCurrentInstance().appContext.config;

    const isClick = ref(true);
    const userInfo = reactive({firstName: '', lastName: ''});
    const colors = reactive({color1: {color: "white"}, color2: {color: "grey"}});
    const clickEvent = (arg) => {
        if ('index' === arg) {
            router.push({name: 'home'});
            colors.color1.color = 'white';
            colors.color2.color = 'grey';
        } else if ('faq' === arg) {
            router.push({name: 'faq'});
            colors.color1.color = 'grey';
            colors.color2.color = 'white';
        }
    };
    const logout = () => {
        store.isLogin = false;
        router.push({name: 'home'});
    };
    const go2Message = () => {
        router.push({name: 'profile'})
    }

    onMounted(() => {
        if (store.isLogin) {
            userInfo.firstName = store.firstName;
            userInfo.lastName = store.lastName;
        } else {
            router.push({name: 'home'});
        }
    })

</script>

<style lang="css">
    [v-cloak] {
        display: none !important;
    }

    a:hover {
        color: #3a8ee6;
    }

    .a-color-white {
        color: #fff !important;
    }

    .a-color-grey {
        color: grey !important;
    }

    div.el-card__body button.el-button.el-button--primary {
        font-size: 12px;
        padding: 5px 10px;
        margin: 10px 0 0 0;
    }

    div.el-card__body button.el-button.el-button--info {
        font-size: 12px;
        padding: 5px 10px;
        margin: 10px 0 0 0;
    }

    div.el-card__body {
        min-height: 120px;
    }

    #header {
        position: sticky;
        left: 0;
        top: 0;
        z-index: 1031;
        background-color: #343a40 !important;
        color: #fff;
        padding: 0.5rem 0 0.5rem 1rem;
        line-height: 1.8;
        box-sizing: border-box;
        width: 100%;
        min-width: 1900px;
    }

    .nav-first {
        cursor: pointer;
        color: #fff;
        font-size: 1.25rem;
        padding-top: 0.3125rem;
        padding-bottom: 0.3125rem;
        text-align: left;
        height: 45px;
        margin-right: 5px;
    }

    .nav-second {
        cursor: pointer;
        text-align: left;
        color: #fff;
        font-size: 1.25rem;
        padding-top: 0.3125rem;
        padding-bottom: 0.3125rem;
        height: 45px;
    }

    .nav-third {
        cursor: pointer;
        text-align: center;
        color: #fff;
        font-size: 1.25rem;
        padding-top: 0.3125rem;
        padding-bottom: 0.3125rem;
        height: 45px;
    }

    .el-input__inner {
        border-radius: 0;
    }

    .popperClassName {
        display: none !important;
        position: static !important;
    }


    .user-firstname-show {
        font-size: 16px;
        position: relative;
        top: 2px;
    }


    .el-badge__content {
        font-size: 5px;
        border-radius: 8px;
        height: 12px;
        line-height: 11px;
        padding: 0 3px;
    }

    .el-badge__content.is-fixed {
        position: absolute;
        top: 5px;
        right: 9px;
    }

    /*.el-input__inner {*/
    /*  border-radius: 0 !important;*/
    /*  border: 1px solid grey !important;*/
    /*  !*!*height: 30px;*!*!*/
    /*  vertical-align: super !important;*/
    /*}*/

    .el-input__icon {
        line-height: 0 !important;
    }

    .logout-style {
        font-size: 20px;
        text-align: center;
        cursor: pointer;
        margin: 5px 0;
    }

</style>
