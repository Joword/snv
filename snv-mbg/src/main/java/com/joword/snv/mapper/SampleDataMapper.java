package com.joword.snv.mapper;

import com.joword.snv.entity.SampleAreaTimeInfo;
import com.joword.snv.entity.SampleChipEntity;
import com.joword.snv.entity.SampleInfo;
import com.joword.snv.entity.SampleQualityControlInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Joword
 * @date: 2022/11/18 11:48
 * @version: 1.0
 * @description: 样本数据获取，注意数据保护机制
 */
@Repository
public interface SampleDataMapper {

    /**
     * 获取所有样本信息(Test)
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectAllSampleInfo();

    /**
     * 根据唯一Id筛选样本
     *
     * @param slideId 唯一Id
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectSlideSample(@Param("s") String slideId);

    /**
     * 根据poolingId筛选样本
     *
     * @param pooling 库号
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectPoolingSample(@Param("p") String pooling);

    /**
     * 根据sampleId筛选样本
     *
     * @param sampleId 样本Id
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectSampleIdSample(@Param("s") String sampleId);


    /**
     * 根据片区筛选样本
     *
     * @param areaId 片区
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectAreaSample(@Param("ai") String areaId);

    /**
     * 根据测序平台型号筛选样本
     *
     * @param platformId 型号
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectPlatformSample(@Param("p") String platformId);

    /**
     * 根据条形码筛选样本
     *
     * @param barcode 条形码
     *
     * @return Sampleinfo集合
     */
    List<SampleInfo> selectBarcodeSample(@Param("b") String barcode);

    /**
     * 根据测序跑道筛选样本
     *
     * @param runId 测序跑道
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectRunIdSample(@Param("r") String runId);

    /**
     * 根据lane号筛选样本
     *
     * @param lane 通道
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectLaneSample(@Param("l") String lane);

    /**
     * 根据时间日期筛选样本
     *
     * @param date 时间
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectDateSample(@Param("d") String date);

    /**
     * 根据月份筛选样本
     *
     * @param month 月份
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectMonthSample(@Param("m") String month);

    /**
     * 根据芯片id筛选样本
     *
     * @param chipId 芯片
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectChipSample(@Param("c") String chipId);

    /**
     * 根据胎型筛选样本
     *
     * @param fetalType 胎型
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectFetalTypeSample(@Param("f") String fetalType);

    /**
     * 根据富集类型筛选样本
     *
     * @param enrichment 富集与非富集
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectEnrichmentSample(@Param("e") String enrichment);

    /**
     * 根据医院筛选样本
     *
     * @param hospital 医院-中文
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectHospitalSample(@Param("h") String hospital);

    /**
     * 根据管型进行筛选
     *
     * @param brand 管型：G管、K管、EDTA管、STRECK、Others
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectBrandSample(@Param("b") String brand);

    /**
     * 根据孕龄进行筛选
     *
     * @param age 孕龄
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectGestationalAgeSample(@Param("g") Integer age);

    /**
     * 根据孕周进行筛选
     *
     * @param week 孕周
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectGestationalWeekSample(@Param("w") String week);

    /**
     * 根据25M/6M进行筛选
     *
     * @param tag 25M/6M
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectTagsSample(@Param("t") String tag);

    /**
     * 根据数据状态来判断是否进行取值
     *
     * @param status 0-异常，非0正常
     *
     * @return SampleInfo集合
     */
    List<SampleInfo> selectStatusSample(@Param("s") Integer status);

    /**
     * 根据批次号统计出
     *
     * @param id 批次号
     *
     * @return int
     */
    int selectSampleNumber(@Param("n") String id);

    /**
     * 获取数据批次时间
     *
     * @return List<String>
     */
    List<String> fetchOriginalDataTime();


    /**
     * 获取全因和基础版片区数据
     *
     * @param tag basic or pro
     *
     * @return List<SampleInfo>
     */
    List<SampleInfo> selectZone(@Param("t") String tag);


    /**
     * 联合查询获取全因/基础版数据
     *
     * @param tag  全因 or 基础版
     * @param area 片区
     *
     * @return List<SampleQualityControlInfo>
     */
    List<SampleQualityControlInfo> selectSampleLeftJoinQc(@Param("t") String tag, @Param("a") String area);

    /**
     * 获取各片区barcode数据
     *
     * @param area    片区
     * @param barcode 引物
     *
     * @return List<SampleQualityControlInfo>
     */
    List<SampleQualityControlInfo> selectSampleBarcode(@Param("a") String area, @Param("b") Integer barcode);

    /**
     * 获取片区样本维度数据
     *
     * @param area 片区
     *
     * @return List<SampleQualityControlInfo>
     */
    List<SampleQualityControlInfo> selectSampleArea(@Param("a") String area);

    /**
     * 获取批次号、片区、时间以及基础、全因
     *
     * @return List<SampleAreaTimeInfo>
     */
    List<SampleAreaTimeInfo> selectSampleAreaTime();

    /**
     * 统计芯片数量
     *
     * @return List<SampleInfo>
     */
    List<SampleChipEntity> selectSampleChip();
}
