import router from '../index';
import nProgress from 'nprogress';
import 'nprogress/nprogress.css';
// @ts-ignore
import dimensionData from '@/store/modules/dimension';
import {storeToRefs} from "pinia";

nProgress.configure({
    showSpinner: false
});

router.beforeEach((to, from) => {
    nProgress.start();
    const dimension = dimensionData();
    const {sampleQC} = storeToRefs(dimension);

    return true;
});

router.afterEach(() => {
    nProgress.done(true);
})

