package com.joword.snv.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Joword
 * @date: 2023/3/23 09:45
 * @version: 1.0
 * @description: 芯片统计接口
 */
@Getter
@Setter
@Schema(name = "ChipSampleInfoDto", description = "芯片统计接口")
public class ChipSampleInfoDto {

    @JsonProperty("basicCount")
    @Schema(name = "basicCount", description = "基础版芯片数数量")
    private Integer basicCount;
    @JsonProperty("proCount")
    @Schema(name = "proCount", description = "全因版芯片数总量")
    private Integer proCount;
    @Schema(name = "allCount", description = "芯片数总量")
    private Integer allCount;

}
