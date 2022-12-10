package com.codewithisa.filmservice.service;

import com.codewithisa.filmservice.VO.ResponseTemplateFSVO;
import com.codewithisa.filmservice.VO.Schedules;
import com.codewithisa.filmservice.entity.Films;
import com.codewithisa.filmservice.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class FilmServiceImpl implements FilmService{
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    RestTemplate restTemplate;
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

    @Override
    public ResponseTemplateFSVO findFilmWithSchedules(Long filmCode) {
        log.info("Inside findFilmWithSchedules of FilmServiceImpl");
        ResponseTemplateFSVO vo = new ResponseTemplateFSVO();
        Films film = filmRepository.findFilmByFilmCode(filmCode);

        Schedules schedules = restTemplate.getForObject("http://localhost:9003/schedules/" + 294,
                Schedules.class);

        vo.setSchedules(schedules);
        vo.setFilm(film);

        return vo;
    }
}
