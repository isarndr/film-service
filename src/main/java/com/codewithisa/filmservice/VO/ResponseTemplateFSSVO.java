package com.codewithisa.filmservice.VO;

import com.codewithisa.filmservice.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// FSS stands for Film Schedule Seat
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateFSSVO {
    private Film film;
    private Schedule schedule;
    private List<Seat> seatList;
}
