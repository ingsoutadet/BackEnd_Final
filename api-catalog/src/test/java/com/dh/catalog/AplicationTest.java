package com.dh.catalog;

import com.dh.catalog.controller.GetNews;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AplicationTest extends BaseAPI{
    @Test
    @Tag("Funcional")
    @DisplayName("Testeando toda la aplicacion con api gateway")
    public void testingAll(){
        given().
                contentType(ContentType.JSON).
        body(
                new MovieRequest(158L,"Nose","Terror","Nose")
        ).when().post("/api/v1/movies");

        given().
                contentType(ContentType.JSON).
                body(
                        new SerieRequest(160L,"Nose2","Terror")
                ).when().post("/api/v1/series");

        GetNews responseOnline = given().when().get("/api/v1/catalog/online/news")
                .as(GetNews.class);
        GetNews responseOffline = given().when().get("/api/v1/catalog/offline/news")
                .as(GetNews.class);

        assertEquals(responseOnline.getMovies().size(),1);
        assertEquals(responseOnline.getSeries().size(),1);

    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MovieRequest{
        private Long id;
        private String name;
        private String genre;
        private String urlStream;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SerieRequest{
        private Long serieId;
        private String serieName;
        private String serieGenre;
    }

}
