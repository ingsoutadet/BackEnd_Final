package com.dh.serie.controller;

import com.dh.serie.model.Serie;
import com.dh.serie.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/series")
public class SerieController {
    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Serie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(serieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Serie> saveMovie(@RequestBody Serie serie) {
        return ResponseEntity.ok().body(serieService.save(serie));
    }
}
