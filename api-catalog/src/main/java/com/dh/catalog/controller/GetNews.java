package com.dh.catalog.controller;

import com.dh.catalog.event.NewSerieEventConsumer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetNews {
    private List<GetNews.MovieDto> movies = new ArrayList<>();
    private List<GetNews.SerieDto> series = new ArrayList<>();


        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class SerieDto {
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
            @Getter
            @Setter
            @AllArgsConstructor
            @NoArgsConstructor
            public static class MovieDto implements Serializable {
                private Long id;
                private String name;
                private String genre;
                private String urlStream;
        }
    }


