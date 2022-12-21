package com.codewithisa.filmservice.service;

import com.codewithisa.filmservice.entity.Films;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FirstServiceSchedulerImpl implements FirstServiceScheduler {
    @Autowired
    FilmService filmService;
    public void firstMethod(){
        List<Films> filmsList = filmService.findFilmsYangSedangTayang();
        log.info("");
        log.info("Film yang sedang tayang:");
        filmsList.forEach(film -> {
            log.info(film.getFilmName());
        });
    }
}
