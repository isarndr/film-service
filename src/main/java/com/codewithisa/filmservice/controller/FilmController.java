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
    @PostMapping("/add-film")
    public ResponseEntity<Films> saveFilm(@RequestBody Films film){
        log.info("Inside saveFilm of FilmController");
        Boolean filmNameExists = filmService.existsByFilmName(film.getFilmName());
        if(filmNameExists){
            log.error("film name is already in the database");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(filmService.saveFilm(film), HttpStatus.CREATED);
    }

    @Operation(
            summary = "untuk mengambil film"
    )
    @GetMapping("/find-film-by-film-code/{filmCode}")
    public ResponseEntity<Films> findFilmByFilmCode(@PathVariable("filmCode") Long filmCode){
        log.info("Inside findFilmByFilmCode of FilmController");
        return new ResponseEntity<>(filmService.findFilmByFilmCode(filmCode), HttpStatus.OK);
    }

    // FS for Films and Schedules
    @GetMapping("/find-film-with-schedules/{filmCode}")
    public ResponseEntity<ResponseTemplateFSVO> findFilmWithSchedules(@PathVariable("filmCode") Long filmCode){
        log.info("Inside findFilmWithSchedules of FilmController");
        Boolean filmCodeExists = filmService.existsByFilmCode(filmCode);
        if(!filmCodeExists){
            log.error("film code is not exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResponseTemplateFSVO>(filmService.findFilmWithSchedules(filmCode), HttpStatus.OK);
    }

    @GetMapping("/find-film-with-schedule-and-seats/")
    public ResponseEntity<ResponseTemplateFSSVO> findFilmWithScheduleAndSeats(
            @RequestParam("filmCode") Long filmCode,
            @RequestParam("scheduleId") Long scheduleId){
        log.info("Inside findFilmWithScheduleAndSeats of FilmController");
        Boolean filmCodeExists = filmService.existsByFilmCode(filmCode);
        if(!filmCodeExists){
            log.error("film code is not exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ResponseTemplateFSSVO responseTemplateFSSVO = filmService.findFilmWithScheduleAndSeats(filmCode, scheduleId);
        if(responseTemplateFSSVO.getSchedule()==null){
            log.error("schedule id is not exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResponseTemplateFSSVO>(responseTemplateFSSVO,
                HttpStatus.OK);
    }

    @Operation(
            summary = "untuk mengubah nama film yang sudah terdaftar"
    )
    @PutMapping("update-film-name-by-film-code/{filmCode}")
    public ResponseEntity<Films> updateFilmName(
            @Schema(example = "1") @PathVariable("filmCode") Long filmCode, @RequestBody Films film)
    {
        log.info("Inside updateFilmName of FilmController");
        Boolean filmCodeExists = filmService.existsByFilmCode(filmCode);
        if(!filmCodeExists){
            log.error("film code is not exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Boolean filmNameExists = filmService.existsByFilmName(film.getFilmName());
        if(filmNameExists){
            log.error("film name is already in the database");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(filmService.updateFilmName(film, filmCode),HttpStatus.OK);
    }

    @Operation(
            summary = "untuk menghapus film"
    )
    @DeleteMapping("/delete-film-by-film-code/{filmCode}")
    public ResponseEntity<String> deleteFilmByFilmCode(@Schema(example = "1") @PathVariable("filmCode") Long filmCode){
        log.info("Inside deleteFilmByFilmCode of FilmController");
        filmService.deleteFilm(filmCode);
        log.info("Film deleted");
        return new ResponseEntity<>("Film deleted",HttpStatus.OK);
    }

    @Operation(summary = "untuk menampilkan semua film yang sedang tayang")
    @GetMapping("/get-all-film-yang-sedang-tayang")
    public List<Films> getAllFilmYangSedangTayang(){
        log.info("Inside getAllFilmYangSedangTayang of FilmController");
        return filmService.findFilmsYangSedangTayang();
    }

    @GetMapping("/find-film-by-film-name/{filmName}")
    public ResponseEntity<Films> findFilmByFilmName(@PathVariable("filmName") String filmName){
        log.info("Inside findFilmByFilmName of FilmController");
        Boolean filmNameExists = filmService.existsByFilmName(filmName);
        if(!filmNameExists){
            log.error("film name is not exist in the database");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(filmService.findFilmByFilmName(filmName), HttpStatus.OK);
    }
}

