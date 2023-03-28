package com.joword.snv.mapper;

import com.joword.snv.entity.LaneInfo;
import com.joword.snv.entity.QualityControlInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Joword
 * @date: 2022/11/24 20:49
 * @version: 1.0
 * @description: 质控DAO接口
 */
@Repository
public interface QualityControlMapper {

    /**
     * 根据唯一id进行数据筛选
     *
     * @param slideId 唯一id
     *
     * @return QualityControlInfo集合
     */
    List<QualityControlInfo> selectSlideIdQuality(@Param("s") String slideId);

    /**
     * 根据pooling号进行数据筛选
     *
     * @param pooling 库池号
     *
     * @return QuliatyControlInfo集合
     */
    List<QualityControlInfo> selectPoolingQuality(@Param("p") String pooling);

    /**
     * 根据性别进行数据筛选
     *
     * @param gender 性别
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectGenderQuality(@Param("g") String gender);

    /**
     * 根据UR来进行数据筛选
     *
     * @param ur unique read 唯一比对结果
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectUniqueReadQuality(@Param("ur") Integer ur);

    /**
     * 根据GC来进行数据筛选
     *
     * @param gc G：鸟嘌呤，C：胞嘧啶
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectGCQuality(@Param("gc") Float gc);

    /**
     * 根据原始数据进行数据筛选
     *
     * @param rawdata 原始数据
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectRawDataQuality(@Param("r") Integer rawdata);

    /**
     * 根据Maprate进行数据筛选
     *
     * @param maprate
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectMaprateQuality(@Param("m") Float maprate);

    /**
     * 根据重复率进行数据筛选
     *
     * @param duprate 重复率
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectDuprateQuality(@Param("d") Float duprate);

    /**
     * 根据胎儿片段率进行数据筛选
     *
     * @param fetalFraction 胎儿片段率
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectFetalFraction(@Param("f") Float fetalFraction);

    /**
     * 根据新生儿比率进行筛选
     *
     * @param fetalNew
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectFetalNew(@Param("f") Float fetalNew);

    /**
     * 根据21号染色体片段率进行数据筛选
     *
     * @param chr21Fraction T21比率
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectChr21Fraction(@Param("c") Float chr21Fraction);

    /**
     * 根据18号染色体片段率进行数据筛选
     *
     * @param chr18Fraction T18比率
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectChr18Fraction(@Param("c") Float chr18Fraction);

    /**
     * 根据13号染色体片段率进行数据筛选
     *
     * @param chr13Fraction T13比率
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectChr13Fraction(@Param("c") Float chr13Fraction);

    /**
     * 根据X染色体片段率进行数据筛选
     *
     * @param chrXFraction X染色体比率
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectChrXFraction(@Param("c") Float chrXFraction);

    /**
     * 根据Y染色体片段率进行数据筛选
     *
     * @param ChrYFraction Y染色体比率
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectChrYFraction(@Param("c") Float ChrYFraction);

    /**
     * 根据
     *
     * @param concentration 数字
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectConcentration(@Param("c") Float concentration);

    /**
     * 根据21号染色体数量进行数据筛选
     *
     * @param chr21Number T21数量
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectChr21Number(@Param("c") Float chr21Number);

    /**
     * 根据X号染色体数量进行数据筛选
     *
     * @param chrXNumber X染色体数量
     *
     * @return QualityControlInfo
     */
    List<QualityControlInfo> selectChrXNumber(@Param("c") Float chrXNumber);

    /**
     * 全因筛选条件为11<样本量<20
     *
     * @param top    20
     * @param bottom 11
     *
     * @return List<QualityControlInfo>
     */
    List<QualityControlInfo> selectNiftyProData(@Param("t") Integer top, @Param("b") Integer bottom);

    /**
     * 业务：获取所有批次数据用以统计原始数据利用率波动情况
     *
     * @param prefix 批次
     *
     * @return List<QualityControlInfo>
     */
    List<QualityControlInfo> selectOriginalData(@Param("sl") String prefix);

    /**
     * 根据批次号获取质控数据
     *
     * @param sampleId 批次号
     *
     * @return List<QualityControlInfo>
     */
    List<QualityControlInfo> selectSampleData(@Param("s") String sampleId);

    /**
     * 根据lane号获取NIFTY基础版or全因版数据
     *
     * @param lane lane号
     *
     * @return
     */
    List<LaneInfo> selectLaneData(@Param("l") String lane, @Param("t") String tag);
}
