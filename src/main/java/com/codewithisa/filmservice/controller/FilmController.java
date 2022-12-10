package com.codewithisa.filmservice.controller;

import com.codewithisa.filmservice.entity.Films;
import com.codewithisa.filmservice.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    FilmService filmService;

    @Operation(
            summary = "untuk menambahkan film baru"
    )
    @PostMapping("/")
    public ResponseEntity<Films> saveFilm(@RequestBody Films film){
        log.info("Inside saveFilm of FilmController");
        return new ResponseEntity<>(filmService.saveFilm(film), HttpStatus.CREATED);
    }

    @Operation(
            summary = "untuk mengambil film"
    )
    @GetMapping("/{filmCode}")
    public ResponseEntity<Films> findFilmByFilmCode(@PathVariable("filmCode") Long filmCode){
        log.info("Inside findFilmByFilmCode of FilmController");
        return new ResponseEntity<>(filmService.findFilmByFilmCode(filmCode), HttpStatus.OK);
    }

}

