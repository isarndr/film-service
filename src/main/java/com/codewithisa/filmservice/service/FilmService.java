package com.codewithisa.filmservice.service;

import com.codewithisa.filmservice.VO.ResponseTemplateFSVO;
import com.codewithisa.filmservice.entity.Films;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public interface FilmService {
    Films saveFilm(Films film);

    Films findFilmByFilmCode(Long filmCode);

    ResponseTemplateFSVO findFilmWithSchedules(Long filmCode);
}
