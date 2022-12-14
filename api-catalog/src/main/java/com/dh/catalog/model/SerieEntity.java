package com.dh.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Serie")
public class SerieEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long serieId;
    private String serieName;
    private String serieGenre;
    private List<Season> seasons = new ArrayList<>();

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Season{
        private Long seasonId;
        private Integer seasonNumber;
        private List<Chapter> chapters = new ArrayList<>();
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
