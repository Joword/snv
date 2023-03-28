package com.joword.snv.impl;

import com.joword.snv.FileParserService;
import com.joword.snv.mapper.OuterDataMapper;
import com.snv.common.utils.FileParserUtils;
import com.snv.common.model.OtherDataExcel;
import com.snv.common.model.QualityControlExcel;
import com.snv.common.model.ResultExcel;
import com.snv.common.model.SampleExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Joword
 * @date: 2022/3/8 0008 16:17
 * @version: 1.0
 * @description: file parser implements
 */
@Service
public class FileParserServiceImpl implements FileParserService {

    @Autowired
    private OuterDataMapper outerDataMapper;

    private final static String path = System.getProperty("user.dir") + "\\nifty-common\\src\\main\\resources\\file\\result_info.xlsx";
    private final static String samplePath = System.getProperty("user.dir") + "\\nifty-common\\src\\main\\resources\\file\\sample_info.xlsx";
    private final static String qualityPath = System.getProperty("user.dir") + "\\nifty-common\\src\\main\\resources\\file\\quality_info.xlsx";
    private final static String otherPath = System.getProperty("user.dir") + "\\nifty-common\\src\\main\\resources\\file\\其他数据.xlsx";

    @Override
    public String getExcelResult() throws Exception {
        ResultExcel resultExcel = new ResultExcel();
        List<ResultExcel> resultExcelLists = new ArrayList<>();
        Class resultClass = (Class) resultExcel.getClass();
        Object resultCls = resultClass.newInstance();
        List<Object> result = FileParserUtils.readEasyExcel(path, resultCls).stream().distinct().collect(Collectors.toList());
        for (Object obj : result) {
            ResultExcel resultInfo = (ResultExcel) obj;
            resultExcelLists.add(resultInfo);
        }
        outerDataMapper.insertResultExcel(resultExcelLists);
        return "success";
    }

    @Override
    public String getExcelSampleInfo() {
        try {
            SampleExcel sample = new SampleExcel();
            List<SampleExcel> sampleExcelLists = new ArrayList<>();
            Class cls = (Class) sample.getClass();
            Object classObj = cls.newInstance();
            List<Object> resultSample = FileParserUtils.readEasyExcel(samplePath, classObj);
            for (Object object : resultSample) {
                SampleExcel sampleInfo = (SampleExcel) object;
                sampleExcelLists.add(sampleInfo);
            }
            outerDataMapper.insertSampleInfoExcel(sampleExcelLists.stream().distinct().collect(Collectors.toList()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public String getExcelQualityControl() {
        try {
            QualityControlExcel qualityControlExcel = new QualityControlExcel();
            List<QualityControlExcel> qualityControlLists = new ArrayList<>();
            Class clz = (Class) qualityControlExcel.getClass();
            Object clzObj = clz.newInstance();
            List<Object> qualityControlResult = FileParserUtils.readEasyExcel(qualityPath, clzObj);
            for (Object object : qualityControlResult) {
                QualityControlExcel qualityControlInfo = (QualityControlExcel) object;
                qualityControlLists.add(qualityControlInfo);
            }
            outerDataMapper.insertQualityControlExcel(qualityControlLists.stream().distinct().collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    @Override
    public String getOtherData() {
        try {
            OtherDataExcel otherDataExcel = new OtherDataExcel();
            List<OtherDataExcel> otherDataExcelLists = new ArrayList<>();
            Class clz = (Class) otherDataExcel.getClass();
            Object clzObj = clz.newInstance();
            List<Object> OtherDataResult = FileParserUtils.readEasyExcel(otherPath, clzObj);
            for (Object object : OtherDataResult) {
                OtherDataExcel otherDataExcelInfo = (OtherDataExcel) object;
                otherDataExcelLists.add(otherDataExcelInfo);
            }
            outerDataMapper.insertOtherDataExcel(otherDataExcelLists.stream().distinct().collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    @Override
    public String putExceltoDatabase() {
        try {
            List<String> files = FileParserUtils.getExcelList();
            ResultExcel resultExcel = new ResultExcel();
            QualityControlExcel qualityControlExcel = new QualityControlExcel();
            SampleExcel sampleExcel = new SampleExcel();
            OtherDataExcel otherDataExcel = new OtherDataExcel();
            System.out.println(files);
            for (String name : files) {
                switch (name.split("\\\\")[name.split("\\\\").length - 1].split("\\.")[0]) {
                    case "result_info":
                        List<ResultExcel> resultExcelLists = new ArrayList<>();
                        List<Object> results = FileParserUtils.readEasyExcel(name, resultExcel.getClass().newInstance());
                        for (Object obj1 : results) {
                            resultExcelLists.add((ResultExcel) obj1);
                        }
                        break;
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return "success";
    }
}
