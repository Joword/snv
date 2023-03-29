<template>
	<el-row>
		<el-col>
			<el-row>
				<el-col :span="12">
					<Barmap></Barmap>
				</el-col>
				<el-col :span="12">
					<el-row>
						<el-col>
							<LineMap v-if="main.lineData!==null"></LineMap>
						</el-col>
						<el-col>
							<TimeLineArea v-if="main.timeLineData!==null"></TimeLineArea>
						</el-col>
					</el-row>
				</el-col>
			</el-row>
		</el-col>
	</el-row>
</template>

<script setup lang="ts">
    import Barmap from "./amap";
    import LineMap from "./homeLine";
    import TimeLineArea from "./timeLine";
    import TimeLine from "./timeLine.vue";
    import {barcodeData} from "@/store/modules/barcode";
    import {mainStore} from '@/store/modules/main';
    import {getCurrentInstance, onMounted} from "vue";

    const {globalProperties} = getCurrentInstance().appContext.config
    const main = mainStore();
    const barcode = barcodeData();
    const getSampleAreaInfo = () => {
        let data = new FormData();
        data.append("q", "all");
        // globalProperties.$ajax.post('/midApi/info',data).then(res => {});
        globalProperties.$ajax.post("/midApi/info/barcode", data).then(res => {
            barcode.duprate = res.data.duprate;
            barcode.gc = res.data.gc;
            barcode.maprate = res.data.maprate;
            barcode.rawData = res.data.rawData;
        })
    }
    const getLineData = () => {
        let data = new FormData();
        globalProperties.$ajax.post("/midApi/home/data").then(res => {
            main.lineData = res.data.lineData;
            main.timeLineData = res.data.timeLineData;
        })
    }
    onMounted(() => {
        getSampleAreaInfo();
        getLineData();
    })

</script>

<style>

</style>