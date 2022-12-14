package com.dh.catalog.client;

import com.dh.catalog.event.NewSerieEventConsumer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name="api-serie")
public interface SerieFeign {

	@GetMapping("/api/v1/series/{genre}")
	List<Serie> getSerieByGenre(@PathVariable (value = "genre") String genre);

	@GetMapping("/api/v1/series")
	List<Serie> getAll();
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	class Serie{
			private Long serieId;
			private String serieName;
			private String serieGenre;
			private List<NewSerieEventConsumer.Data.SerieDto.Season> seasons = new ArrayList<>();

			@Getter
			@Setter
			@AllArgsConstructor
			@NoArgsConstructor
			public static class Season{
				private Long seasonId;
				private Integer seasonNumber;
				private List<NewSerieEventConsumer.Data.SerieDto.Chapter> chapters = new ArrayList<>();
			}

			@Getter
			@Setter
			@AllArgsConstructor
			@NoArgsConstructor
			public static class Chapter{
				private Long chapterId;
				private String chapterName;
				private Integer chapterNumber;
				private String chapterUrlStream;
			}
		}
}
