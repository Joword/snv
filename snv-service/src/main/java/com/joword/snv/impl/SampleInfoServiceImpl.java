package com.joword.snv.impl;

import com.joword.snv.SampleInfoService;
import com.joword.snv.entity.SampleChipEntity;
import com.joword.snv.entity.SampleInfo;
import com.joword.snv.mapper.SampleDataMapper;
import com.joword.snv.model.ChipSampleInfoDto;
import com.joword.snv.model.SampleInfoDTO;
import com.joword.snv.utils.SampleInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author Joword
 * @date: 2022/11/21 09:51
 * @version: 1.0
 * @description: 样本信息实现类，包含：uuid、poolingId、sampleId、片区、平台、barcode、runId、lane号、时间、月份、芯片号、胎型、富集、
 * 管型、孕龄、孕周、25M/6M
 */
@Service
public class SampleInfoServiceImpl implements SampleInfoService {

    final static String basicValue = "6M";
    final static String proValue = "25M";

    @Autowired
    private SampleDataMapper sampleDataMapper;


    @Override
    public List<SampleInfoDTO> fetchSlideId(String slideId) {
        List<SampleInfo> sampleInfoLists = sampleDataMapper.selectSlideSample(slideId);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(sampleInfoLists);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchPoolingId(String poolingId) {
        List<SampleInfo> samplePoolingLists = sampleDataMapper.selectPoolingSample(poolingId);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(samplePoolingLists);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchSampleId(String sampleId) {
        List<SampleInfo> sampleSampleIdLists = sampleDataMapper.selectSampleIdSample(sampleId);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(sampleSampleIdLists);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchAreaId(String areaId) {
        List<SampleInfo> sampleAreaIdLists = sampleDataMapper.selectAreaSample(areaId);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(sampleAreaIdLists);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchPlatform(String platform) {
        List<SampleInfo> samplePlatformLists = sampleDataMapper.selectPlatformSample(platform);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(samplePlatformLists);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchBarcode(String barcode) {
        List<SampleInfo> sampleInfoBarcodeLists = sampleDataMapper.selectBarcodeSample(barcode);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(sampleInfoBarcodeLists);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchRunId(String runId) {
        List<SampleInfo> sampleInfoRunIdLists = sampleDataMapper.selectRunIdSample(runId);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(sampleInfoRunIdLists);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchLane(String lane) {
        List<SampleInfo> sampleInfoLaneLists = sampleDataMapper.selectLaneSample(lane);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(sampleInfoLaneLists);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchDate(String date) {
        List<SampleInfo> sampleInfoDateLists = sampleDataMapper.selectDateSample(date);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(sampleInfoDateLists);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchMonth(String month) {
        List<SampleInfo> sampleInfoMonthLists = sampleDataMapper.selectMonthSample(month);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(sampleInfoMonthLists);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchChip(String chipId) {
        List<SampleInfo> sampleInfoChipList = sampleDataMapper.selectChipSample(chipId);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(sampleInfoChipList);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchFetalType(String fetalType) {
        List<SampleInfo> sampleInfoFetalTypeList = sampleDataMapper.selectFetalTypeSample(fetalType);
        List<SampleInfoDTO> sampleInfoDTOLists = SampleInfoUtils.reformatStructure(sampleInfoFetalTypeList);

        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchEnrichment(String rich) {
        List<SampleInfo> sampleInfoEnrichmentLists = sampleDataMapper.selectEnrichmentSample(rich);
        List<SampleInfoDTO> sampleInfoDTOS = SampleInfoUtils.reformatStructure(sampleInfoEnrichmentLists);

        return sampleInfoDTOS;
    }

    @Override
    public List<SampleInfoDTO> fetchHospital(String hospital) {
        List<SampleInfo> sampleInfoHospitalList = sampleDataMapper.selectHospitalSample(hospital);
        List<SampleInfoDTO> sampleInfoDTOS = SampleInfoUtils.reformatStructure(sampleInfoHospitalList);

        return sampleInfoDTOS;
    }

    @Override
    public List<SampleInfoDTO> fetchBrand(String brand) {
        List<SampleInfo> sampleInfoBrandLists = sampleDataMapper.selectBrandSample(brand);
        List<SampleInfoDTO> sampleInfoDTOS = SampleInfoUtils.reformatStructure(sampleInfoBrandLists);

        return sampleInfoDTOS;
    }

    @Override
    public List<SampleInfoDTO> fetchGestationalAge(Integer age) {
        List<SampleInfo> sampleInfoGestationalAgeLists = sampleDataMapper.selectGestationalAgeSample(age);
        List<SampleInfoDTO> sampleInfoDTOS = SampleInfoUtils.reformatStructure(sampleInfoGestationalAgeLists);

        return sampleInfoDTOS;
    }

    @Override
    public List<SampleInfoDTO> fetchGestationalWeek(String week) {
        List<SampleInfo> sampleInfoGestationalWeekLists = sampleDataMapper.selectGestationalWeekSample(week);
        List<SampleInfoDTO> sampleInfoDTOS = SampleInfoUtils.reformatStructure(sampleInfoGestationalWeekLists);

        return sampleInfoDTOS;
    }

    @Override
    public List<SampleInfoDTO> fetchTags(String tags) {
        List<SampleInfo> sampleInfoTagLists = sampleDataMapper.selectTagsSample(tags);
        List<SampleInfoDTO> sampleInfoDTOS = SampleInfoUtils.reformatStructure(sampleInfoTagLists);

        return sampleInfoDTOS;
    }

    @Override
    public List<SampleInfoDTO> fetchStatus(Integer status) {
        List<SampleInfo> sampleStatusLists = sampleDataMapper.selectStatusSample(status);
        List<SampleInfoDTO> sampleInfoDTOS = SampleInfoUtils.reformatStructure(sampleStatusLists);

        return sampleInfoDTOS;
    }

    @Override
    public List<SampleInfoDTO> fetchChipData() {
        List<SampleInfoDTO> sampleInfoDTOLists = new ArrayList<>();


        return sampleInfoDTOLists;
    }

    @Override
    public List<SampleInfoDTO> fetchAllData() {
        List<SampleInfo> sampleInfoLists = sampleDataMapper.selectAllSampleInfo();
        List<SampleInfoDTO> sampleInfoDTOS = SampleInfoUtils.reformatStructure(sampleInfoLists);

        return sampleInfoDTOS;
    }

    /**
     * 重点：使用stream进行实体类Lists去重
     *
     * @return entity
     */
    @Override
    public ChipSampleInfoDto fetchChipSampleData() {
        ChipSampleInfoDto chipSampleInfoDto = new ChipSampleInfoDto();
        List<SampleChipEntity> sampleInfoLists = sampleDataMapper.selectSampleChip();
        List<SampleChipEntity> chipLists = sampleInfoLists.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(Comparator.comparing(SampleChipEntity::getChip))), ArrayList::new
                )
        );
        List<SampleChipEntity> basicLists = chipLists.stream().filter(x -> x.getTag().equals(basicValue)).collect(Collectors.toList());
        List<SampleChipEntity> proLists = chipLists.stream().filter(x -> x.getTag().equals(proValue)).collect(Collectors.toList());
        chipSampleInfoDto.setAllCount(chipLists.size());
        chipSampleInfoDto.setBasicCount(basicLists.size());
        chipSampleInfoDto.setProCount(proLists.size());

        return chipSampleInfoDto;
    }
}
