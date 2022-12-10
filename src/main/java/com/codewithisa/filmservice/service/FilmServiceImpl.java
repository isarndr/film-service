package com.codewithisa.filmservice.service;

import com.codewithisa.filmservice.entity.Films;
import com.codewithisa.filmservice.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FilmServiceImpl implements FilmService{
    @Autowired
    FilmRepository filmRepository;
    @Override
    public Films saveFilm(Films film) {
        log.info("Inside saveFilm of FilmServiceImpl");
        return filmRepository.save(film);
    }

    @Override
    public Films findFilmByFilmCode(Long filmCode) {
        log.info("Inside getFilmByFilmCode of FilmServiceImpl");
        return filmRepository.findFilmByFilmCode(filmCode);
    }
}
