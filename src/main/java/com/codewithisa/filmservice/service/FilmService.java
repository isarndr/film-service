package com.codewithisa.filmservice.service;

import com.codewithisa.filmservice.entity.Films;
import org.springframework.stereotype.Service;

@Service
public interface FilmService {
    Films saveFilm(Films film);

    Films findFilmByFilmCode(Long filmCode);
}
