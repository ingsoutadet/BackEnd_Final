package com.dh.catalog.controller;

import com.dh.catalog.client.MovieFeign;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final MovieFeign movieFeign;
	private final CatalogService catalogService;

	public CatalogController(MovieFeign movieFeign, CatalogService catalogService) {
		this.movieFeign = movieFeign;
		this.catalogService = catalogService;
	}
	@GetMapping("/online/news")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<GetNews> getNewsOnline(){
		return ResponseEntity.ok(catalogService.getNewsOnline());
	}

	@GetMapping("/offline/news")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<GetNews> getNewsOffline(){
		return ResponseEntity.ok(catalogService.getNewsOffline());
	}

	@GetMapping("/{genre}")
	ResponseEntity<List<MovieFeign.Movie>> getGenre(@PathVariable String genre) {
		return ResponseEntity.ok(movieFeign.getMovieByGenre(genre));
	}

}
