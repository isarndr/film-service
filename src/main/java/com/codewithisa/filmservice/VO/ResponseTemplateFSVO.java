package com.codewithisa.filmservice.VO;

import com.codewithisa.filmservice.entity.Films;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateFSVO {
    private Films film;
    private List<Schedules> schedulesList;
}
