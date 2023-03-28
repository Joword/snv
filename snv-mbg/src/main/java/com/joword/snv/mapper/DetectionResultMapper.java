package com.joword.snv.mapper;

import com.joword.snv.entity.DetectionInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Joword
 * @date: 2022/11/24 11:48
 * @version: 1.0
 * @description: 检测结果detection_result
 */
@Repository
public interface DetectionResultMapper {

    /**
     * 根据唯一Id获取数据
     *
     * @param slideId 唯一Id
     *
     * @return DetectionInfo集合
     */
    List<DetectionInfo> selectSlideDetection(@Param("s") String slideId);

    /**
     * 根据pooling号获取数据
     *
     * @param poolingId 库池号
     *
     * @return DetectionInfo集合
     */
    List<DetectionInfo> selectPoolingDetection(@Param("p") String poolingId);

    /**
     * 根据test_chr21获取数据
     *
     * @param testChr21 是否检出T21
     *
     * @return DetectionInfo集合
     */
    List<DetectionInfo> selectTestChr21Detection(@Param("t") String testChr21);

    /**
     * 根据test_chr18获取数据
     *
     * @param testChr18 是否检出T18
     *
     * @return DetectionInfo集合
     */
    List<DetectionInfo> selectTestChr18Detection(@Param("tc") String testChr18);

    /**
     * 根据test_chr13获取数据
     *
     * @param testChr13 是否检出T13
     *
     * @return DetectionInfo
     */
    List<DetectionInfo> selectTestChr13Detection(@Param("t") String testChr13);

    /**
     * 根据X染色体获取数据
     *
     * @param x 判断是否检出X染色体
     *
     * @return DetectionInfo
     */
    List<DetectionInfo> selectTestXDetection(@Param("x") String x);

    /**
     * 根据note1获取数据
     *
     * @param note1 标记1
     *
     * @return DetectionInfo
     */
    List<DetectionInfo> selectNote1Detection(@Param("n") String note1);

    /**
     * 根据note2获取数据
     *
     * @param note2 标记2
     *
     * @return DetectionInfo
     */
    List<DetectionInfo> selectNote2Detection(@Param("n2") String note2);

    /**
     * 根据疾病表型获取数据
     *
     * @param disease 疾病表型
     *
     * @return DetectionInfo
     */
    List<DetectionInfo> selectDiseaseDetection(@Param("d") String disease);

    /**
     * 根据cnv获取数据
     *
     * @param cnv 拷贝数变异
     *
     * @return DetectionInfo
     */
    List<DetectionInfo> selectCNVDetection(@Param("c") String cnv);

    /**
     * 根据autom获取数据
     *
     * @param autom 其他常染色体检出情况，不包括T13、T18、T21
     *
     * @return DetectionInfo集合
     */
    List<DetectionInfo> selectAutomDetection(@Param("a") String autom);

    /**
     * 根据21号染色体z-score打分获取数据
     *
     * @param zscoreChr21 21号染色体打分
     *
     * @return DetectionInfo集合
     */
    List<DetectionInfo> selectZscoreChr21Detection(@Param("z") Double zscoreChr21);

    /**
     * 根据18号染色体z-score打分获取数据
     *
     * @param zscoreChr18 18号染色体z-score打分
     *
     * @return DetectionInfo
     */
    List<DetectionInfo> selectZscoreChr18Detection(@Param("z") Double zscoreChr18);

    /**
     * 根据13号染色体z-score打分获取数据
     *
     * @param zscoreChr13 13号染色体z-score打分
     *
     * @return DetectionInfo
     */
    List<DetectionInfo> selectZscoreChr13Detection(@Param("z") Double zscoreChr13);

    /**
     * 判断数据是否可用
     *
     * @param status 0不可用，非0可用
     *
     * @return DetectionInfo
     */
    List<DetectionInfo> selectStatusDetection(@Param("s") Integer status);

    /**
     * 获取所有detection_result表里的数据，测试接口，慎用！！！！！！！！！
     *
     * @return DetectionInfo
     */
    List<DetectionInfo> selectDetectionAll();

}
