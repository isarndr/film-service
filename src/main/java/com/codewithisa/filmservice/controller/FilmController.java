package com.codewithisa.filmservice.controller;

import com.codewithisa.filmservice.VO.ResponseTemplateFSSVO;
import com.codewithisa.filmservice.VO.ResponseTemplateFSVO;
import com.codewithisa.filmservice.entity.Film;
import com.codewithisa.filmservice.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmService filmService;

    @Operation(
            summary = "untuk menambahkan film baru"
    )
    @PostMapping("/")
    public ResponseEntity<?> saveFilm(@RequestBody Film film){
        try {
            return new ResponseEntity<>(filmService.saveFilm(film), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "untuk mengambil film"
    )
    @GetMapping("/{filmCode}")
    public ResponseEntity<?> findFilmByFilmCode(@PathVariable("filmCode") Long filmCode){
        try {
            return new ResponseEntity<>(filmService.findFilmByFilmCode(filmCode), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // FS for Film and Schedule
    @GetMapping("/with-schedules/{filmCode}")
    public ResponseEntity<?> findFilmWithSchedules(@PathVariable("filmCode") Long filmCode){
        try {
            return new ResponseEntity<ResponseTemplateFSVO>(filmService.findFilmWithSchedules(filmCode), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/with-schedule-and-seats/")
    public ResponseEntity<?> findFilmWithScheduleAndSeats(
            @RequestParam("filmCode") Long filmCode,
            @RequestParam("scheduleId") Long scheduleId){

        ResponseTemplateFSSVO responseTemplateFSSVO = null;

        try {
            responseTemplateFSSVO = filmService.findFilmWithScheduleAndSeats(filmCode, scheduleId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if(responseTemplateFSSVO.getSchedule()==null){
            log.error("schedule id is not exist");
            return new ResponseEntity<>("schedule id is not exist", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResponseTemplateFSSVO>(responseTemplateFSSVO, HttpStatus.OK);
    }

    @Operation(
            summary = "untuk mengubah nama film yang sudah terdaftar"
    )
    @PutMapping("/{filmCode}")
    public ResponseEntity<?> updateFilmName(
            @Schema(example = "1") @PathVariable("filmCode") Long filmCode, @RequestBody Film film)
    {
        try {
            return new ResponseEntity<>(filmService.updateFilmName(film, filmCode),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "untuk menghapus film"
    )
    @DeleteMapping("/{filmCode}")
    public ResponseEntity<String> deleteFilmByFilmCode(@Schema(example = "1") @PathVariable("filmCode") Long filmCode){
        try {
            filmService.deleteFilm(filmCode);
            return new ResponseEntity<>("Film deleted",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "untuk menampilkan semua film yang sedang tayang")
    @GetMapping("/yang-sedang-tayang")
    public List<Film> getAllFilmYangSedangTayang(){
        return filmService.findFilmsYangSedangTayang();
    }

    @GetMapping("/by-film-name/{filmName}")
    public ResponseEntity<?> findFilmByFilmName(@PathVariable("filmName") String filmName){
        try {
            return new ResponseEntity<>(filmService.findFilmByFilmName(filmName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

