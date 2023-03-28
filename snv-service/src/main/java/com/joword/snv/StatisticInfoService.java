package com.joword.snv;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author Joword
 * @date: 2023/1/31 15:55
 * @version: 1.0
 * @description: 统计接口
 */
public interface StatisticInfoService {


    /**
     * 原始数据接口
     *
     * @param date 日期
     *
     * @return 样本数据实体类
     */
    JSONObject selectOriginalData(String date);

    /**
     * 获取本数据批次时间
     *
     * @return data time
     */
    List<String> getAllDataTime();

    /**
     * 获取样本Id相关数据
     *
     * @param sampleId 样本Id
     *
     * @return JSONObject
     */
    JSONObject getSampleData(String sampleId);

    /**
     * 获取barcode数据
     *
     * @param barcode 编号
     *
     * @return JSONObject
     */
    JSONObject getBarcodeData(String barcode);

    /**
     * 获取基础版lane数据
     *
     * @param tag lane号
     *
     * @return JSONObject
     */
    JSONObject getLaneBasicData(String tag);

    /**
     * 以片区区分所有样本
     *
     * @return JSONObject
     */
    JSONObject getAllSampleAreaTime();

}
