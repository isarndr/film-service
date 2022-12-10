package com.codewithisa.filmservice.VO;

import com.codewithisa.filmservice.entity.Films;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// FSS stands for Film Schedule Seat
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateFSSVO {
    private Films film;
    private Schedules schedule;
    private List<Seats> seatsList;
}
