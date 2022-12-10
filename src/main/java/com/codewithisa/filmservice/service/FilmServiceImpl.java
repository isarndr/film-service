package com.codewithisa.filmservice.service;

import com.codewithisa.filmservice.VO.ResponseTemplateFSSVO;
import com.codewithisa.filmservice.VO.ResponseTemplateFSVO;
import com.codewithisa.filmservice.VO.Schedules;
import com.codewithisa.filmservice.VO.Seats;
import com.codewithisa.filmservice.entity.Films;
import com.codewithisa.filmservice.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

        ResponseEntity<List<Schedules>> schedulesList = restTemplate.exchange(
                "http://localhost:9003/schedules/schedules-by-film-code/" + filmCode,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Schedules>>(){});

        vo.setSchedulesList(schedulesList.getBody());
        vo.setFilm(film);

        return vo;
    }

    @Override
    public ResponseTemplateFSSVO findFilmWithScheduleAndSeats(Long filmCode, Long scheduleId) {
        log.info("Inside findFilmWithSchedulesAndSeats of FilmServiceImpl");
        ResponseTemplateFSSVO vo = new ResponseTemplateFSSVO();
        Films film = filmRepository.findFilmByFilmCode(filmCode);

        Schedules schedule = restTemplate.getForObject(
                "http://localhost:9003/schedules/" + scheduleId,
                Schedules.class);

        ResponseEntity<List<Seats>> seatsList = restTemplate.exchange(
                "http://localhost:9004/seats/" + scheduleId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Seats>>(){});

        vo.setSchedule(schedule);
        vo.setSeatsList(seatsList.getBody());
        vo.setFilm(film);

        return vo;
    }
}
