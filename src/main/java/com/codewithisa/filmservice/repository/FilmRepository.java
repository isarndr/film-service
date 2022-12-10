package com.codewithisa.filmservice.repository;

import com.codewithisa.filmservice.entity.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Films,Long> {
    @Query(
            nativeQuery = true,
            value = "select * from films where film_code=:filmCode"
    )
    Films findFilmByFilmCode(@Param("filmCode") Long filmCode);
}

