package com.codewithisa.filmservice.service;

import com.codewithisa.filmservice.VO.ResponseTemplateFSSVO;
import com.codewithisa.filmservice.VO.ResponseTemplateFSVO;
import com.codewithisa.filmservice.entity.Films;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmService {
    Films saveFilm(Films film);
    Films findFilmByFilmCode(Long filmCode);
    ResponseTemplateFSVO findFilmWithSchedules(Long filmCode);
    ResponseTemplateFSSVO findFilmWithScheduleAndSeats(Long filmCode, Long scheduleId);
    Films updateFilmName(Films film, Long filmCode);
    void deleteFilm(Long filmCode);
    List<Films> findFilmsYangSedangTayang();
}
