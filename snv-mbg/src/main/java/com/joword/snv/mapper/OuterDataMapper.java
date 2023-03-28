package com.joword.snv.mapper;

import com.snv.common.model.OtherDataExcel;
import com.snv.common.model.QualityControlExcel;
import com.snv.common.model.ResultExcel;
import com.snv.common.model.SampleExcel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Joword
 * @date: 2022/10/12 0012 21:25
 * @version: 1.0.0
 * @description: 外部excel数据处理接口
 */
@Repository
public interface OuterDataMapper {

    /**
     * 插入result_info.xlsx文件数据至db
     *
     * @param lists 集合
     *
     * @return int
     */
    int insertResultExcel(@Param("e") List<ResultExcel> lists);

    /**
     * 插入sample_info.xlsx至db
     *
     * @param args 集合
     *
     * @return int
     */
    int insertSampleInfoExcel(@Param("s") List<SampleExcel> args);

    /**
     * 插入quality_control_info.xlsx至db
     *
     * @param args 集合
     *
     * @return int
     */
    int insertQualityControlExcel(@Param("q") List<QualityControlExcel> args);

    /**
     * 插入其他数据.xlsx至db
     *
     * @param args 集合
     *
     * @return int
     */
    int insertOtherDataExcel(@Param("o") List<OtherDataExcel> args);


}
