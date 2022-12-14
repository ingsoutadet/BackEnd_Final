package com.dh.catalog.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="api-movie")
public interface MovieFeign {

	@GetMapping("/api/v1/movies/{genre}")
	List<Movie> getMovieByGenre(@PathVariable (value = "genre") String genre);

	@GetMapping("/api/v1/movies")
	List<Movie> getAll();

	@Getter
	@Setter
	class Movie{
		private Long movieId;
		private String movieName;
		private String movieGenre;
		private String movieUrlStream;
	}

}
