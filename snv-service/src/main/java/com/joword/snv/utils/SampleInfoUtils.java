package com.joword.snv.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joword.snv.entity.QualityControlInfo;
import com.joword.snv.entity.SampleInfo;
import com.joword.snv.mapper.QualityControlMapper;
import com.joword.snv.model.SampleInfoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Joword
 * @date: 2022/11/23 14:29
 * @version: 1.0
 * @description: Utils about information
 */
public class SampleInfoUtils {
    public static List<SampleInfoDTO> reformatStructure(List<SampleInfo> samples) {
        List<SampleInfoDTO> result = new ArrayList<>();
        for (SampleInfo sample : samples) {
            SampleInfoDTO sampleInfoDTO = new SampleInfoDTO();
            sampleInfoDTO.setId(sample.getId());
            sampleInfoDTO.setSlidId(sample.getSlideId());
            sampleInfoDTO.setPoolingId(sample.getPooling());
            sampleInfoDTO.setSampleId(sample.getSampleId());
            sampleInfoDTO.setArea(sample.getArea());
            sampleInfoDTO.setPlatform(sample.getPlatform());
            sampleInfoDTO.setBarcode(sample.getBarcode());
            sampleInfoDTO.setRunId(sample.getRunId());
            sampleInfoDTO.setLane(sample.getLane());
            sampleInfoDTO.setDate(sample.getDate().split(" ")[0]);
            sampleInfoDTO.setMonth(sample.getMonth());
            sampleInfoDTO.setChip(sample.getChip());
            sampleInfoDTO.setFetalType(sample.getFetalType());
            sampleInfoDTO.setEnrichment(sample.getEnrichment());
            sampleInfoDTO.setHospital(sample.getHospital());
            sampleInfoDTO.setBrand(sample.getBrand());
            sampleInfoDTO.setGestationalAge(sample.getGestationalAge());
            sampleInfoDTO.setGestationalWeek(sample.getGestationalWeek());
            sampleInfoDTO.setTag(sample.getTag());
            sampleInfoDTO.setStatus(sample.getStatus());
            sampleInfoDTO.setCreateTime(sample.getCreateTime());
            sampleInfoDTO.setUpdateTime(sample.getUpdateTime());
            result.add(sampleInfoDTO);
        }

        return result;
    }

    public static JSONObject getDataFiltering(List<SampleInfo> sampleInfos, QualityControlMapper qualityMapper) {
        JSONObject jsonObject = new JSONObject();
        List<String> lists = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        /*List<String>转List<对象>*/
        List<QualityControlInfo> areaQcSamplesBasic = strings.parallelStream().map(
                x -> {
                    QualityControlInfo q = new QualityControlInfo();
                    for (QualityControlInfo quality : qualityMapper.selectSampleData(x)) {
                        q = quality;
                    }
                    ;
                    return q;
                }).collect(Collectors.toList());
        /*List<对象>转Map<String,List<String>>*/
        Map<String, List<String>> areaBasicString = sampleInfos.parallelStream().filter(x -> lists.contains(x.getArea()))
                .collect(Collectors.groupingBy(SampleInfo::getArea, Collectors.mapping(a -> a.getSlideId().split("_")[0] + "_" + a.getSlideId().split("_")[1], Collectors.toList())));
        areaBasicString.replaceAll((k, v) -> areaBasicString.get(k).stream().distinct().collect(Collectors.toList()));

        return jsonObject;
    }

    public static JSONObject formatBarcode(JSONObject json) {
        List<List<String>> rawDataLists = new ArrayList<>();
        List<List<String>> duprateList = new ArrayList<>();
        List<List<String>> maprateList = new ArrayList<>();
        List<List<String>> gcList = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        headers.add("id");
        headers.add("name");
        headers.add("value");
        headers.add("area");
        JSONObject jsonObject = new JSONObject();
        JSONObject rawData = json.getJSONObject("rawData");
        JSONObject duprate = json.getJSONObject("duprate");
        JSONObject maprate = json.getJSONObject("maprate");
        JSONObject gc = json.getJSONObject("gc");
        List<String> index = JSONArray.parseArray(rawData.getJSONArray("index").toJSONString(), String.class).stream().collect(Collectors.toList());
        List<String> duprateIndex = JSONArray.parseArray(duprate.getJSONArray("index").toJSONString(), String.class).stream().collect(Collectors.toList());
        List<String> maprateIndex = JSONArray.parseArray(maprate.getJSONArray("index").toJSONString(), String.class).stream().collect(Collectors.toList());
        List<String> gcIndex = JSONArray.parseArray(gc.getJSONArray("index").toJSONString(), String.class).stream().collect(Collectors.toList());
        rawData.remove("index");
        duprate.remove("index");
        maprate.remove("index");
        gc.remove("index");
        for (String key : rawData.keySet()) {
            for (String num : index) {
                List<String> dataLists = new ArrayList<>();
                if (rawData.getJSONObject(key).getDoubleValue(num) > 0.0) {
                    dataLists.add(num);
                    dataLists.add("rawData");
                    dataLists.add(String.valueOf(rawData.getJSONObject(key).getDoubleValue(num)));
                    dataLists.add(key);
                }
                rawDataLists.add(dataLists);
            }
        }
        for (String key1 : duprate.keySet()) {
            for (String num1 : duprateIndex) {
                List<String> duprateLists = new ArrayList<>();
                if (duprate.getJSONObject(key1).getDoubleValue(num1) > 0.0) {
                    duprateLists.add(num1);
                    duprateLists.add("duprate");
                    duprateLists.add(String.valueOf(duprate.getJSONObject(key1).getDoubleValue(num1)));
                    duprateLists.add(key1);
                }
                duprateList.add(duprateLists);
            }
        }
        for (String key2 : maprate.keySet()) {
            for (String num2 : maprateIndex) {
                List<String> maprateLists = new ArrayList<>();
                if (maprate.getJSONObject(key2).getDoubleValue(num2) > 0.0) {
                    maprateLists.add(num2);
                    maprateLists.add("maprate");
                    maprateLists.add(String.valueOf(maprate.getJSONObject(key2).getDoubleValue(num2)));
                    maprateLists.add(key2);
                }
                maprateList.add(maprateLists);
            }
        }
        for (String key3 : gc.keySet()) {
            for (String num3 : gcIndex) {
                List<String> gcLists = new ArrayList<>();
                if (gc.getJSONObject(key3).getDoubleValue(num3) > 0.0) {
                    gcLists.add(num3);
                    gcLists.add("gc");
                    gcLists.add(String.valueOf(gc.getJSONObject(key3).getDoubleValue(num3)));
                    gcLists.add(key3);
                }
                gcList.add(gcLists);
            }
        }
        rawDataLists.add(0, headers);
        duprateList.add(0, headers);
        maprateList.add(0, headers);
        gcList.add(0, headers);
        jsonObject.put("rawData", rawDataLists);
        jsonObject.put("duprate", duprateList);
        jsonObject.put("maprate", maprateList);
        jsonObject.put("gc", gcList);
        jsonObject.put("rawDataAreas", new ArrayList<>(rawData.keySet()).stream().distinct().collect(Collectors.toList()));
        jsonObject.put("duprateAreas", new ArrayList<>(duprate.keySet()).stream().distinct().collect(Collectors.toList()));
        jsonObject.put("maprateAreas", new ArrayList<>(maprate.keySet()).stream().distinct().collect(Collectors.toList()));
        jsonObject.put("gcAreas", new ArrayList<>(gc.keySet()).stream().distinct().collect(Collectors.toList()));

        return jsonObject;
    }

    public static double getLaneDataCv(List<Integer> args) {
        double[] laneArrays = new double[args.size()];
        for (int i = 0; i < args.size(); i++) {
            laneArrays[i] = args.get(i).intValue();
        }
        double sum = 0.0, standardDeviation = 0.0;
        int length = laneArrays.length;
        for (double num : laneArrays) {
            sum += num;
        }
        double mean = sum / length;
        for (double num : laneArrays) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / length) / mean;
    }

    /**
     * area in abbreviation
     *
     * @return List<String>
     */
    public static List<String> areas() {
        List<String> lists = new ArrayList<>();
        lists.add("SZ");
        lists.add("TJ");
        lists.add("WH");
        lists.add("HB");
        lists.add("TH");
        lists.add("CS");
        lists.add("CQ");
        lists.add("GY");
        lists.add("YN");
        lists.add("SH");
        return lists;
    }

    /**
     * area in Chinese or English
     * @param area area in English
     * @return area in Chinese
     */
    public static List<String> areaMap(List<String> area) {
        List<String> map = new ArrayList<>();
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<>();
        hashMap.put("SZ", "深圳");
        hashMap.put("TJ", "天津");
        hashMap.put("WH", "武汉");
        hashMap.put("HB", "河北");
        hashMap.put("TH", "太和");
        hashMap.put("CS", "长沙");
        hashMap.put("CQ", "重庆");
        hashMap.put("GY", "贵阳");
        hashMap.put("YN", "云南");
        hashMap.put("SH", "上海");
        for (String str : area) {
            map.add(String.valueOf(hashMap.get(str)));
        }
        return map;
    }

    public static List<Integer> monthMap() {
        List<Integer> monthLists = new ArrayList<>();
        monthLists.add(1);
        monthLists.add(2);
        monthLists.add(3);
        monthLists.add(4);
        monthLists.add(5);
        monthLists.add(6);
        monthLists.add(7);
        monthLists.add(8);
        monthLists.add(9);
        monthLists.add(10);
        monthLists.add(11);
        monthLists.add(12);
        return monthLists;
    }
}
