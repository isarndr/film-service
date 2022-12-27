package com.codewithisa.filmservice.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Schedule {

    @Schema(example = "1")
    private Long scheduleId;

    @Schema(example = "18 November 2022")
    private String tanggalTayang;

    @Schema(example = "20.00 WIB")
    private String jamMulai;

    @Schema(example = "22.30 WIB")
    private String jamSelesai;

    @Schema(example = "50000")
    private Integer hargaTiket;

    @Schema(example = "A")
    private Character studioName;

    @Schema(example = "1")
    private Long filmCode;
}
