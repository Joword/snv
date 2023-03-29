<template>
	<el-container>
		<el-header>
			<Header></Header>
		</el-header>
		<el-main>
		
		</el-main>
		<el-footer>
			<Footer></Footer>
		</el-footer>
	</el-container>
</template>

<script setup lang="ts">

    import {onMounted} from "vue";
    import Header from "../components/header.vue";
    import Footer from "../components/footer.vue";

    const test = () => {
        console.log()
    }

    onMounted(() => {
        test();
    })

</script>

<style lang="scss">

</style>