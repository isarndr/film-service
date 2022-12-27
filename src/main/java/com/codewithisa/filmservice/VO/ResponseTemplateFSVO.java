package com.codewithisa.filmservice.VO;

import com.codewithisa.filmservice.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// FS stands for Film Schedule
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateFSVO {
    private Film film;
    private List<Schedule> scheduleList;
}
