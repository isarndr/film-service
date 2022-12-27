package com.codewithisa.filmservice.service;

import com.codewithisa.filmservice.VO.ResponseTemplateFSSVO;
import com.codewithisa.filmservice.VO.ResponseTemplateFSVO;
import com.codewithisa.filmservice.VO.Schedules;
import com.codewithisa.filmservice.VO.Seats;
import com.codewithisa.filmservice.entity.Film;
import com.codewithisa.filmservice.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
    public Film saveFilm(Film film) throws Exception{
        Boolean filmNameExists = existsByFilmName(film.getFilmName());
        if(filmNameExists){
            log.error("film name is already in the database");
            throw new Exception("film name is already in the database");
        }
        return filmRepository.save(film);
    }

    @Override
    public Film findFilmByFilmCode(Long filmCode) throws Exception{
        Boolean filmCodeExists = existsByFilmCode(filmCode);
        if(!filmCodeExists){
            log.error("film code is not exist");
            throw new Exception("film code is not exist");
        }
        return filmRepository.findFilmByFilmCode(filmCode);
    }

    @Override
    public ResponseTemplateFSVO findFilmWithSchedules(Long filmCode) throws Exception{
        Boolean filmCodeExists = existsByFilmCode(filmCode);

        if(!filmCodeExists){
            log.error("film code is not exist");
            throw new Exception("film code is not exist");
        }

        ResponseTemplateFSVO vo = new ResponseTemplateFSVO();
        Film film = filmRepository.findFilmByFilmCode(filmCode);

        ResponseEntity<List<Schedules>> schedulesList = restTemplate.exchange(
                "https://schedule-service-production-12cd.up.railway.app/schedules/find-schedules-by-film-code/" + filmCode,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Schedules>>(){});

        vo.setSchedulesList(schedulesList.getBody());
        vo.setFilm(film);

        return vo;
    }

    @Override
    public ResponseTemplateFSSVO findFilmWithScheduleAndSeats(Long filmCode, Long scheduleId) throws Exception{

        Boolean filmCodeExists = existsByFilmCode(filmCode);
        if(!filmCodeExists){
            log.error("film code is not exist");
            throw new Exception("film code is not exist");
        }

        ResponseTemplateFSSVO vo = new ResponseTemplateFSSVO();
        Film film = filmRepository.findFilmByFilmCode(filmCode);

        Schedules schedule = restTemplate.getForObject(
                "https://schedule-service-production-12cd.up.railway.app/schedules/find-schedule-by-schedule-id/" + scheduleId,
                Schedules.class);

        ResponseEntity<List<Seats>> seatsList = restTemplate.exchange(
                "https://seat-service-production-3b8e.up.railway.app/seats/find-seats-by-schedule-id/" + scheduleId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Seats>>(){});

        vo.setSchedule(schedule);
        vo.setSeatsList(seatsList.getBody());
        vo.setFilm(film);

        return vo;
    }

    @Override
    public Film updateFilmName(Film film, Long filmCode) throws Exception{
        Boolean filmCodeExists = existsByFilmCode(filmCode);
        if(!filmCodeExists){
            log.error("film code is not exist");
            throw new Exception("film code is not exist");
        }

        Boolean filmNameExists = existsByFilmName(film.getFilmName());
        if(filmNameExists){
            log.error("film name is already in the database");
            throw new Exception("film name is already in the database");
        }

        Film existingFilm = filmRepository.findFilmByFilmCode(filmCode);

        existingFilm.setFilmName(film.getFilmName());

        filmRepository.save(existingFilm);

        log.info("film name is successfully updated");

        return existingFilm;
    }

    @Override
    public void deleteFilm(Long filmCode) throws Exception{
        Boolean filmCodeExists = existsByFilmCode(filmCode);

        if(!filmCodeExists){
            log.error("film code is not exist");
            throw new Exception("film code is not exist");
        }

        filmRepository.deleteFilmByFilmCode(filmCode);
        log.info("Film successfully deleted");
    }

    @Override
    public List<Film> findFilmsYangSedangTayang() {
        return filmRepository.findFilmsYangSedangTayang();
    }

    @Override
    public Film findFilmByFilmName(String filmName) throws Exception{
        Boolean filmNameExists = existsByFilmName(filmName);

        if(!filmNameExists){
            log.error("film name is not exist in the database");
            throw new Exception("film name is not exist in the database");
        }
        return filmRepository.findFilmByFilmName(filmName);
    }

    @Override
    public Boolean existsByFilmCode(Long filmCode) {
        return filmRepository.existsById(filmCode);
    }

    @Override
    public Boolean existsByFilmName(String filmName) {
        return filmRepository.existsByFilmName(filmName);
    }
}
