package com.joword.snv;

import com.joword.snv.model.ChipSampleInfoDto;
import com.joword.snv.model.SampleInfoDTO;

import java.util.List;

/**
 * @author Joword
 * @date: 2022/11/21 09:51
 * @version: 1.0
 * @description: 样本信息业务层接口
 */
public interface SampleInfoService {

    /**
     * 获取唯一Id并查找数据
     *
     * @param slideId 唯一Id
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchSlideId(String slideId);

    /**
     * 获取pooling并查找数据
     *
     * @param poolingId 库id
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchPoolingId(String poolingId);

    /**
     * 获取sampleId并查找数据
     *
     * @param sampleId 样本Id
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchSampleId(String sampleId);

    /**
     * 获取AreaId并查找数据
     *
     * @param areaId 片区
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchAreaId(String areaId);

    /**
     * 获取测序平台型号并查找数据
     *
     * @param platform 型号
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchPlatform(String platform);

    /**
     * 获取条形码并查找数据
     *
     * @param barcode 条形码
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchBarcode(String barcode);

    /**
     * 获取runId并查找数据
     *
     * @param runId 跑道
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchRunId(String runId);

    /**
     * 获取Lane号并查找数据
     *
     * @param lane 通道
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchLane(String lane);

    /**
     * 获取时间并查找数据
     *
     * @param date 时间
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchDate(String date);

    /**
     * 获取月份并查找数据
     *
     * @param month 月份
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchMonth(String month);

    /**
     * 获取芯片号并查找数据
     *
     * @param chipId 芯片号
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchChip(String chipId);

    /**
     * 获取胎型并查找数据
     *
     * @param fetalType 胎型：S-单胎，D-双胎，V-双胎减胎
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchFetalType(String fetalType);

    /**
     * 获取富集与非富集并查找数据
     *
     * @param rich 分为rich/normal
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchEnrichment(String rich);

    /**
     * 获取医院并查找数据
     *
     * @param hospital 医院名称
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchHospital(String hospital);

    /**
     * 获取管型并查找数据
     *
     * @param brand 管型
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchBrand(String brand);

    /**
     * 获取孕龄并查找数据
     *
     * @param age 孕龄
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchGestationalAge(Integer age);

    /**
     * 获取孕周并查找数据
     *
     * @param week 孕周
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchGestationalWeek(String week);

    /**
     * 获取6M/25M并查找数据
     *
     * @param tags 6M/25M
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchTags(String tags);

    /**
     * 获取数据状态，判断该数据是否可用
     *
     * @param status 0为异常，非0为正常
     *
     * @return SampleInfo data transfer object
     */
    List<SampleInfoDTO> fetchStatus(Integer status);

    /**
     * 获取芯片数据
     *
     * @return List<>SampleInfoDTO>
     */
    List<SampleInfoDTO> fetchChipData();

    /**
     * 获取样本所有数据，仅供测试，慎用！！！！！！！！！
     *
     * @return SampleInfo
     */
    List<SampleInfoDTO> fetchAllData();

    /**
     * 获取芯片总数、基础版芯片总数、全因芯片总数
     *
     * @return entity dto
     */
    ChipSampleInfoDto fetchChipSampleData();
}
