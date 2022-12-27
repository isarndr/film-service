package com.codewithisa.filmservice.service;

import com.codewithisa.filmservice.VO.ResponseTemplateFSSVO;
import com.codewithisa.filmservice.VO.ResponseTemplateFSVO;
import com.codewithisa.filmservice.entity.Film;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmService {
    Film saveFilm(Film film) throws Exception;
    Film findFilmByFilmCode(Long filmCode) throws Exception;
    ResponseTemplateFSVO findFilmWithSchedules(Long filmCode) throws Exception;
    ResponseTemplateFSSVO findFilmWithScheduleAndSeats(Long filmCode, Long scheduleId) throws Exception;
    Film updateFilmName(Film film, Long filmCode) throws Exception;
    void deleteFilm(Long filmCode) throws Exception;
    List<Film> findFilmsYangSedangTayang();
    Film findFilmByFilmName(String filmName) throws Exception;

    Boolean existsByFilmCode(Long filmCode);
    Boolean existsByFilmName(String filmName);
}
