package com.joword.snv.impl;

import com.alibaba.fastjson.JSONObject;
import com.joword.snv.entity.*;
import com.joword.snv.mapper.QualityControlMapper;
import com.joword.snv.mapper.SampleDataMapper;
import com.joword.snv.StatisticInfoService;
import com.joword.snv.utils.SampleInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Joword
 * @date: 2023/1/31 15:55
 * @version: 1.0
 * @description: 业务统计接口实现类
 */
@Service
public class StatisticsInfoServiceImpl implements StatisticInfoService {

    final String niftyBasicTag = "6M";
    final String niftyProTag = "25M";
    @Autowired
    private SampleDataMapper sampleDataMapper;

    @Autowired
    private QualityControlMapper qualityControlMapper;

    @Override
    public JSONObject selectOriginalData(String date) {
        List<String> niftyBasicId = new ArrayList<>();
        List<String> niftyProId = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        JSONObject basicMap = new JSONObject();
        JSONObject proMap = new JSONObject();
        List<SampleInfo> sampleInfoLists = sampleDataMapper.selectDateSample(date);
        List<String> niftyBasic = sampleInfoLists.parallelStream().filter(q -> q.getTag().equals(niftyBasicTag)).map(SampleInfo::getSlideId)
                .map(v -> v.split("_")[0]).distinct().collect(Collectors.toList());
        List<String> niftyPro = sampleInfoLists.parallelStream().filter(q -> q.getTag().equals(niftyProTag)).map(SampleInfo::getSlideId)
                .map(v -> v.split("_")[0]).distinct().collect(Collectors.toList());
        basicMap.put("L01", "");
        basicMap.put("L02", "");
        basicMap.put("L03", "");
        basicMap.put("L04", "");
        /*基础版*/
        for (String basic : niftyBasic) {
            int basicNumber = sampleDataMapper.selectSampleNumber(basic + "_L01");
            List<QualityControlInfo> qualityControlBasicLists = qualityControlMapper.selectOriginalData(basic.split("_")[0]);
            boolean l01 = qualityControlBasicLists.parallelStream().anyMatch(q -> q.getSlideId().split("_")[1].equals("L01"));
            boolean l02 = qualityControlBasicLists.parallelStream().anyMatch(q -> q.getSlideId().split("_")[1].equals("L02"));
            boolean l03 = qualityControlBasicLists.parallelStream().anyMatch(q -> q.getSlideId().split("_")[1].equals("L03"));
            boolean l04 = qualityControlBasicLists.parallelStream().anyMatch(q -> q.getSlideId().split("_")[1].equals("L04"));
            if (l01) {
                /*原始数据波动情况统计*/
                double l01URMean = qualityControlBasicLists.parallelStream().filter(quality -> quality.getSlideId().split("_")[1].equals("L01")).mapToDouble(QualityControlInfo::getUr).average().getAsDouble();
                double l01RawDataMean = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L01")).mapToDouble(QualityControlInfo::getRawData).average().getAsDouble();
                double l01Value = l01URMean / l01RawDataMean;
                /*lane产量波动情况统计*/
                double l01LaneProduction = l01RawDataMean * basicNumber;
                /*原始数据量波动情况*/
                List<Integer> l01RawDataLists = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L01")).map(x -> x.getRawData()).collect(Collectors.toList());
                /*重复率波动情况*/
                double l01DuprateMean = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L01")).mapToDouble(QualityControlInfo::getDuprate).average().getAsDouble();
                /*比对率波动情况*/
                double l01MaprateMean = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L01")).mapToDouble(QualityControlInfo::getMaprate).average().getAsDouble();
                /*男胎胎儿浓度波动情况*/
                double l01YFractionMean = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L01")).mapToDouble(QualityControlInfo::getChrYFraction).average().getAsDouble();
                basicMap.put("L01", String.valueOf(l01Value));
            }
            if (l02) {
                double l02URMean = qualityControlBasicLists.parallelStream().filter(quality -> quality.getSlideId().split("_")[1].equals("L02")).mapToDouble(QualityControlInfo::getUr).average().getAsDouble();
                double l02RawDataMean = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L02")).mapToDouble(QualityControlInfo::getRawData).average().getAsDouble();
                double l02Value = l02URMean / l02RawDataMean;
                basicMap.put("L02", String.valueOf(l02Value));
            }
            if (l03) {
                double l03URMean = qualityControlBasicLists.parallelStream().filter(quality -> quality.getSlideId().split("_")[1].equals("L03")).mapToDouble(QualityControlInfo::getUr).average().getAsDouble();
                double l03RawDataMean = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L03")).mapToDouble(QualityControlInfo::getRawData).average().getAsDouble();
                double l03Value = l03URMean / l03RawDataMean;
                basicMap.put("L03", String.valueOf(l03Value));
            }
            if (l04) {
                double l04URMean = qualityControlBasicLists.parallelStream().filter(quality -> quality.getSlideId().split("_")[1].equals("L04")).mapToDouble(QualityControlInfo::getUr).average().getAsDouble();
                double l04RawDataMean = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L04")).mapToDouble(QualityControlInfo::getRawData).average().getAsDouble();
                double l04Value = l04URMean / l04RawDataMean;
                basicMap.put("L04", String.valueOf(l04Value));
            }
            basicMap.put("name", basic);
            basicMap.put("time", date);
        }
        /*全因版*/
//        for (String pro:niftyPro) {
//            int proNumber = sampleDataMapper.selectSampleNumber(pro+"_L01");
//            List<QualityControlInfo> qualityControlBasicLists = qualityControlMapper.selectOriginalData(pro.split("_")[0]);
//            boolean l01 = qualityControlBasicLists.parallelStream().anyMatch(q -> q.getSlideId().split("_")[1].equals("L01"));
//            boolean l02 = qualityControlBasicLists.parallelStream().anyMatch(q -> q.getSlideId().split("_")[1].equals("L02"));
//            boolean l03 = qualityControlBasicLists.parallelStream().anyMatch(q -> q.getSlideId().split("_")[1].equals("L03"));
//            boolean l04 = qualityControlBasicLists.parallelStream().anyMatch(q -> q.getSlideId().split("_")[1].equals("L04"));
//            if (l01){
//                double l01URMean = qualityControlBasicLists.parallelStream().filter(quality -> quality.getSlideId().split("_")[1].equals("L01")).mapToDouble(QualityControlInfo::getUr).average().getAsDouble();
//                double l01RawDataMean = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L01")).mapToDouble(QualityControlInfo::getRawData).average().getAsDouble();
//                double l01Value = l01URMean/l01RawDataMean;
//                proMap.put("L01",String.valueOf(l01Value));
//            }
//            if (l02){
//                double l02URMean = qualityControlBasicLists.parallelStream().filter(quality -> quality.getSlideId().split("_")[1].equals("L02")).mapToDouble(QualityControlInfo::getUr).average().getAsDouble();
//                double l02RawDataMean = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L02")).mapToDouble(QualityControlInfo::getRawData).average().getAsDouble();
//                double l02Value = l02URMean/l02RawDataMean;
//                proMap.put("L02",String.valueOf(l02Value));
//            }
//            if (l03){
//                double l03URMean = qualityControlBasicLists.parallelStream().filter(quality -> quality.getSlideId().split("_")[1].equals("L03")).mapToDouble(QualityControlInfo::getUr).average().getAsDouble();
//                double l03RawDataMean = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L03")).mapToDouble(QualityControlInfo::getRawData).average().getAsDouble();
//                double l03Value = l03URMean/l03RawDataMean;
//                proMap.put("L03",String.valueOf(l03Value));
//            }
//            if (l04){
//                double l04URMean = qualityControlBasicLists.parallelStream().filter(quality -> quality.getSlideId().split("_")[1].equals("L04")).mapToDouble(QualityControlInfo::getUr).average().getAsDouble();
//                double l04RawDataMean = qualityControlBasicLists.parallelStream().filter(q -> q.getSlideId().split("_")[1].equals("L04")).mapToDouble(QualityControlInfo::getRawData).average().getAsDouble();
//                double l04Value = l04URMean/l04RawDataMean;
//                proMap.put("L04",String.valueOf(l04Value));
//            }
//            proMap.put("name",pro);
//            proMap.put("date",date);
//        }
        jsonObject.put("basic", basicMap);
//        jsonObject.put("pro",proMap);

        return jsonObject;
    }

    @Override
    public List<String> getAllDataTime() {
        return sampleDataMapper.fetchOriginalDataTime();
    }

    @Override
    public JSONObject getSampleData(String sampleId) {
        JSONObject jsonObject = new JSONObject();
        JSONObject basic = new JSONObject();
        JSONObject niftyPro = new JSONObject();
        List<SampleInfo> areaBasic = sampleDataMapper.selectZone(niftyBasicTag);
        List<SampleInfo> areaPro = sampleDataMapper.selectZone(niftyProTag);
        List<String> areaBasicLists = areaBasic.parallelStream().map(SampleInfo::getArea).distinct().collect(Collectors.toList());
        List<String> areaProLists = areaPro.parallelStream().map(SampleInfo::getArea).distinct().collect(Collectors.toList());
        for (String area : areaBasicLists) {
            JSONObject basicJson = new JSONObject();
            List<SampleQualityControlInfo> samples = sampleDataMapper.selectSampleLeftJoinQc(niftyBasicTag, area);
            /*单个UR分布情况*/
            List<Integer> urLists = samples.parallelStream().map(SampleQualityControlInfo::getUr).collect(Collectors.toList());
            basicJson.put("ur", urLists);
            /*单个样本重复率分布*/
            List<Double> dupLists = samples.parallelStream().map(SampleQualityControlInfo::getDuprate).collect(Collectors.toList());
            basicJson.put("duprate", dupLists);
            /*单个样本比对率分布*/
            List<Double> mapLists = samples.parallelStream().map(SampleQualityControlInfo::getMaprate).collect(Collectors.toList());
            basicJson.put("maprate", mapLists);
            /*单个样本胎儿浓度分布情况*/
            List<Double> fetalFractionLists = samples.parallelStream().map(SampleQualityControlInfo::getFetalFraction).collect(Collectors.toList());
            basicJson.put("fetalFraction", fetalFractionLists);
            /*单个样本GC分布情况*/
            List<Double> gcLists = samples.parallelStream().map(SampleQualityControlInfo::getGc).collect(Collectors.toList());
            basicJson.put("gc", fetalFractionLists);
            basic.put(area, basicJson);
        }
        for (String pro : areaProLists) {
            JSONObject proJson = new JSONObject();
            List<SampleQualityControlInfo> samplePros = sampleDataMapper.selectSampleLeftJoinQc(niftyProTag, pro);
            /*单个UR分布情况*/
            proJson.put("ur", samplePros.parallelStream().map(SampleQualityControlInfo::getUr).collect(Collectors.toList()));
            /*单个样本重复率分布*/
            proJson.put("duprate", samplePros.parallelStream().map(SampleQualityControlInfo::getDuprate).collect(Collectors.toList()));
            /*单个样本比对率分布*/
            proJson.put("maprate", samplePros.parallelStream().map(SampleQualityControlInfo::getMaprate).collect(Collectors.toList()));
            /*单个样本胎儿浓度分布情况*/
            proJson.put("fetalFraction", samplePros.parallelStream().map(SampleQualityControlInfo::getFetalFraction).collect(Collectors.toList()));
            /*单个样本GC分布情况*/
            proJson.put("gc", samplePros.parallelStream().map(SampleQualityControlInfo::getGc).collect(Collectors.toList()));
            niftyPro.put(pro, proJson);
        }
        jsonObject.put("basic", basic);
        jsonObject.put("pro", niftyPro);

        return jsonObject;
    }

    @Override
    public JSONObject getBarcodeData(String barcode) {
        JSONObject jsonObject = new JSONObject();
        JSONObject maprateJson = new JSONObject();
        JSONObject rawDataJson = new JSONObject();
        JSONObject duprateJson = new JSONObject();
        JSONObject gcJson = new JSONObject();
        List<String> areas = sampleDataMapper.selectAllSampleInfo().stream().map(SampleInfo::getArea).distinct().collect(Collectors.toList());
        for (String area : areas) {
            List<SampleQualityControlInfo> sampleArea = sampleDataMapper.selectSampleArea(area);
            /*各片区barcode rawData均值*/
            Map<String, Double> rawDataMean = sampleArea.parallelStream().collect(
                    Collectors.groupingBy(SampleQualityControlInfo::getBarcode, Collectors.averagingDouble(SampleQualityControlInfo::getRawData)));
            List<String> rawDataIndex = sampleArea.parallelStream().map(SampleQualityControlInfo::getBarcode).filter(rawDataMean::containsKey).distinct().sorted(Comparator.comparing(x -> new BigDecimal((String) x))).collect(Collectors.toList());
            /*各片区barcode maprate均值*/
            Map<String, Double> maprateMean = sampleArea.parallelStream().collect(
                    Collectors.groupingBy(SampleQualityControlInfo::getBarcode, Collectors.averagingDouble(SampleQualityControlInfo::getMaprate)));
            List<String> maprateIndex = sampleArea.parallelStream().map(SampleQualityControlInfo::getBarcode).filter(maprateMean::containsKey).distinct().sorted(Comparator.comparing(x -> new BigDecimal((String) x))).collect(Collectors.toList());
            /*各片区barcode duprate均值*/
            Map<String, Double> duprateMean = sampleArea.parallelStream().collect(
                    Collectors.groupingBy(SampleQualityControlInfo::getBarcode, Collectors.averagingDouble(SampleQualityControlInfo::getDuprate)));
            List<String> duprateIndex = sampleArea.parallelStream().map(SampleQualityControlInfo::getBarcode).filter(duprateMean::containsKey).distinct().sorted(Comparator.comparing(x -> new BigDecimal((String) x))).collect(Collectors.toList());
            /*各片区barcode gc均值*/
            Map<String, Double> gcMean = sampleArea.parallelStream().collect(
                    Collectors.groupingBy(SampleQualityControlInfo::getBarcode, Collectors.averagingDouble(SampleQualityControlInfo::getGc))
            );
            List<String> gcIndex = sampleArea.parallelStream().map(SampleQualityControlInfo::getBarcode).filter(gcMean::containsKey).distinct().sorted(Comparator.comparing(x -> new BigDecimal((String) x))).collect(Collectors.toList());
            rawDataJson.put(area, rawDataMean);
            rawDataJson.put("index", rawDataIndex);
            maprateJson.put(area, maprateMean);
            maprateJson.put("index", maprateIndex);
            duprateJson.put(area, duprateMean);
            duprateJson.put("index", duprateIndex);
            gcJson.put(area, gcMean);
            gcJson.put("index", gcIndex);
        }
        jsonObject.put("rawData", rawDataJson);
        jsonObject.put("maprate", maprateJson);
        jsonObject.put("duprate", duprateJson);
        jsonObject.put("gc", gcJson);

        return jsonObject;
    }

    @Override
    public JSONObject getLaneBasicData(String tag) {
        JSONObject jsonObject = new JSONObject();
        List<List<Double>> rawDataRatio = new ArrayList<>();
        List<List<Double>> laneProduct = new ArrayList<>();
        List<List<Double>> rawDataCv = new ArrayList<>();
        List<List<Double>> duprate = new ArrayList<>();
        List<List<Double>> maprate = new ArrayList<>();
        List<List<Double>> chryRatio = new ArrayList<>();
        List<String> laneLists = new ArrayList<>(Arrays.asList(new String[]{"L01", "L02", "L03", "L04"}));
//        List<LaneInfo> basicData = qualityControlMapper.selectLaneData("L01",tag);
        for (String laneNum : laneLists) {
            List<LaneInfo> basicData = qualityControlMapper.selectLaneData(laneNum, tag);
            /*各片区数目*/
            List<String> areas = basicData.parallelStream().map(LaneInfo::getArea).distinct().sorted().collect(Collectors.toList());
            jsonObject.put("area", areas);
            /*日期*/
            int[] dateArrays = basicData.parallelStream().mapToInt(LaneInfo::getDate).distinct().toArray();
            List<Integer> dateLists = Arrays.stream(dateArrays).boxed().collect(Collectors.toList());

            for (Integer count : dateLists) {
                for (String zone : areas) {
                    /*原始数据利用率*/
                    double l01UrMeans = basicData.parallelStream().filter(x -> x.getDate().equals(count)).mapToDouble(LaneInfo::getUr).average().getAsDouble();
                    double l01RawDataMeans = basicData.parallelStream().filter(x -> x.getDate().equals(count)).mapToDouble(LaneInfo::getRawData).average().getAsDouble();
                    double l01Ratio = l01UrMeans / l01RawDataMeans;
                    /*lane产量*/
                    Long laneSum = basicData.parallelStream().filter(x -> x.getDate().equals(count)).collect(Collectors.counting());
                    double l01LaneRatio = l01RawDataMeans / laneSum.longValue();
                    /*原始数据量波动*/
                    double laneSd = basicData.parallelStream().filter(x -> x.getDate().equals(count)).map(x -> {
                        return Math.pow(x.getRawData().doubleValue() - l01RawDataMeans, 2);
                    }).mapToDouble(x -> x).sum();
                    double laneCv = Math.sqrt(laneSd / laneSum) / l01RawDataMeans;
//            List<Integer> laneRawDataLists =basicData.parallelStream().filter(x -> x.getDate().equals(count)).map(LaneInfo::getRawData).collect(Collectors.toList());
                    /*重复率波动*/
                    double l01DuprateMean = basicData.parallelStream().filter(x -> x.getDate().equals(count)).mapToDouble(LaneInfo::getDuprate).average().getAsDouble();
                    /*比对率波动情况*/
                    double l01MaprateMean = basicData.parallelStream().filter(x -> x.getDate().equals(count)).mapToDouble(LaneInfo::getMaprate).average().getAsDouble();
                    /*男胎浓度波动*/
                    double l01ChrYMean = basicData.parallelStream().filter(x -> x.getDate().equals(count)).mapToDouble(LaneInfo::getChrYFraction).average().getAsDouble();

                    List<Double> dateTemps = new ArrayList<>();
                    List<Double> laneProductTemp = new ArrayList<>();
                    List<Double> cvTemp = new ArrayList<>();
                    List<Double> duprateTemp = new ArrayList<>();
                    List<Double> maprateTemp = new ArrayList<>();
                    List<Double> chryTemp = new ArrayList<>();
                    dateTemps.add(count.doubleValue());
                    dateTemps.add(l01Ratio);
                    laneProductTemp.add(count.doubleValue());
                    laneProductTemp.add(l01LaneRatio);
                    cvTemp.add(count.doubleValue());
                    cvTemp.add(laneCv);
                    duprateTemp.add(count.doubleValue());
                    duprateTemp.add(l01DuprateMean);
                    maprateTemp.add(count.doubleValue());
                    maprateTemp.add(l01MaprateMean);
                    chryTemp.add(count.doubleValue());
                    chryTemp.add(l01ChrYMean);
//                    dateTemps.add(zone);
//                    dateTemps.add(laneNum);
                    rawDataRatio.add(dateTemps);
                    laneProduct.add(laneProductTemp);
                    rawDataCv.add(cvTemp);
                    duprate.add(duprateTemp);
                    maprate.add(maprateTemp);
                    chryRatio.add(chryTemp);
                    jsonObject.put("rawData", rawDataRatio);
                    jsonObject.put("laneProduct", laneProduct);
                    jsonObject.put("rawDataCv", rawDataCv);
                    jsonObject.put("duprate", duprate);
                    jsonObject.put("maprate", maprate);
                    jsonObject.put("chrY", chryRatio);
                }
            }
        }

        return jsonObject;
    }

    @Override
    public JSONObject getAllSampleAreaTime() {
        JSONObject jsonObject = new JSONObject();
        List<SampleAreaTimeInfo> sampleAreaTimeInfoList = sampleDataMapper.selectSampleAreaTime();
        List<String> dateLists = new ArrayList<>();
        JSONObject areaJson = new JSONObject();
        JSONObject dateJson = new JSONObject();
        List<Integer> basicArea = new ArrayList<>();
        List<Integer> proArea = new ArrayList<>();
        List<String> dateAreaLists = new ArrayList<>();
        List<Integer> countAreaLists = new ArrayList<>();
        for (String area : SampleInfoUtils.areas()) {
            List<SampleAreaTimeInfo> basic = sampleAreaTimeInfoList.stream().filter(x -> x.getArea().equals(area)).collect(Collectors.toList());
            List<SampleAreaTimeInfo> pro = sampleAreaTimeInfoList.stream().filter(x -> x.getArea().equals(area)).collect(Collectors.toList());
            basicArea.add(basic.stream().filter(x -> x.getTag().equals(niftyBasicTag)).collect(Collectors.toList()).size());
            proArea.add(pro.stream().filter(x -> x.getTag().equals(niftyProTag)).collect(Collectors.toList()).size());
            areaJson.put("basic", basicArea);
            areaJson.put("pro", proArea);
        }
        areaJson.put("index", SampleInfoUtils.areaMap(SampleInfoUtils.areas()));
        dateLists = sampleAreaTimeInfoList.stream().map(a -> a.getDate()).distinct().sorted().collect(Collectors.toList());
        for (String num : dateLists) {
            if (num.length() == 8) {
                dateAreaLists.add(num.substring(0, 4) + "/" + num.substring(4, 6) + "/" + num.substring(6, 8));
                countAreaLists.add(sampleAreaTimeInfoList.stream().filter(x -> x.getDate().equals(num)).collect(Collectors.toList()).size());
            }
        }
        dateJson.put("dateIndex", dateAreaLists);
        dateJson.put("values", countAreaLists);
        jsonObject.put("lineData", areaJson);
        jsonObject.put("timeLineData", dateJson);
        jsonObject.put("sampleCount", sampleAreaTimeInfoList.size());

        return jsonObject;
    }


}
