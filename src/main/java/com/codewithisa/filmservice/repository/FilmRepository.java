package com.codewithisa.filmservice.repository;

import com.codewithisa.filmservice.entity.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Films,Long> {
    @Query(
            nativeQuery = true,
            value = "select * from films where film_code=:filmCode"
    )
    Films findFilmByFilmCode(@Param("filmCode") Long filmCode);
}

