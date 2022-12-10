package com.codewithisa.filmservice.controller;

import com.codewithisa.filmservice.VO.ResponseTemplateFSSVO;
import com.codewithisa.filmservice.VO.ResponseTemplateFSVO;
import com.codewithisa.filmservice.entity.Films;
import com.codewithisa.filmservice.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    // FS for Films and Schedules
    @GetMapping("/film-with-schedules/{filmCode}")
    public ResponseEntity<ResponseTemplateFSVO> findFilmWithSchedules(@PathVariable("filmCode") Long filmCode){
        log.info("Inside findFilmWithSchedules of FilmController");
        return new ResponseEntity<ResponseTemplateFSVO>(filmService.findFilmWithSchedules(filmCode), HttpStatus.OK);
    }

    @GetMapping("/schedule-and-seats/{filmCode}/{scheduleId}")
    public ResponseEntity<ResponseTemplateFSSVO> findFilmWithScheduleAndSeats(
            @PathVariable("filmCode") Long filmCode,
            @PathVariable("scheduleId") Long scheduleId){
        log.info("Inside findFilmWithScheduleAndSeats of FilmController");
        return new ResponseEntity<ResponseTemplateFSSVO>(filmService.findFilmWithScheduleAndSeats(filmCode, scheduleId),
                HttpStatus.OK);
    }

    @Operation(
            summary = "untuk mengubah nama film yang sudah terdaftar"
    )
    @PutMapping("update-film-name/{filmCode}")
    public ResponseEntity<Films> updateFilmName(
            @Schema(example = "1") @PathVariable("filmCode") Long filmCode, @RequestBody Films film)
    {
        log.info("Inside updateFilmName of FilmController");
        return new ResponseEntity<>(filmService.updateFilmName(film, filmCode),HttpStatus.OK);
    }

    @Operation(
            summary = "untuk menghapus film"
    )
    @DeleteMapping("/{filmCode}")
    public ResponseEntity<String> deleteFilmByFilmCode(@Schema(example = "1") @PathVariable("filmCode") Long filmCode){
        log.info("Inside deleteFilmByFilmCode of FilmController");
        filmService.deleteFilm(filmCode);
        log.info("Film deleted");
        return new ResponseEntity<>("Film deleted",HttpStatus.OK);
    }
}

