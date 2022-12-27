package com.codewithisa.filmservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(example = "1")
    private Long filmCode;

    @Column(
            unique = true
    )
    @Schema(example = "Nemo")
    private String filmName;

    @Schema(example = "true")
    private boolean sedangTayang;
}
