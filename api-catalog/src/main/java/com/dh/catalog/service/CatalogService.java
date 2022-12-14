package com.dh.catalog.service;

import com.dh.catalog.client.MovieFeign;
import com.dh.catalog.client.SerieFeign;
import com.dh.catalog.controller.GetNews;
import com.dh.catalog.repository.MovieRepositoryMongo;
import com.dh.catalog.repository.SerieRepositoryMongo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class CatalogService {

    private final MovieRepositoryMongo movieRepositoryMongo;
    private final SerieRepositoryMongo serieRepositoryMongo;

    private final MovieFeign movieFeign;
    private final SerieFeign serieFeign;


    public GetNews getNewsOnline(){
        GetNews response = new GetNews();
        findAllNewsOfSeries(response);
        findAllNewsOfMovies(response);
        return response;
    }

    @Retry(name = "retryMovie")
    @CircuitBreaker(name = "clientMovie", fallbackMethod = "findAllNewsOfMoviesFallBack")
    private void findAllNewsOfMovies(GetNews response) {
        var NewsOfMovies = movieFeign.getAll().stream().collect(Collectors.toList());
        response.setMovies(NewsOfMovies.stream().map(movie->{
            GetNews.MovieDto movieResponse = new GetNews.MovieDto();
            BeanUtils.copyProperties(movie,movieResponse);
            return movieResponse;
        }).collect(Collectors.toList()));
    }

    public void findAllNewsOfMoviesFallBack(GetNews response,Throwable t){
        var NewsOfMovies = movieRepositoryMongo.findAll().stream().collect(Collectors.toList());
        response.setMovies(NewsOfMovies.stream().map(movie->{
            GetNews.MovieDto movieResponse = new GetNews.MovieDto();
            BeanUtils.copyProperties(movie,movieResponse);
            return movieResponse;
        }).collect(Collectors.toList()));
    }


    @Retry(name = "retryMovie")
    @CircuitBreaker(name = "clientMovie", fallbackMethod = "findAllNewsOfSeriesFallBack")
    private void findAllNewsOfSeries(GetNews response) {
        var NewsOfSeries = serieFeign.getAll().stream().collect(Collectors.toList());
        response.setSeries(NewsOfSeries.stream().map(serie->{
            GetNews.SerieDto serieResponse = new GetNews.SerieDto();
            BeanUtils.copyProperties(serie,serieResponse);
            return serieResponse;
        }).collect(Collectors.toList()));
    }

    public void findAllNewsOfSeriesFallBack(GetNews response,Throwable t){
        var NewsOfSeries = serieRepositoryMongo.findAll().stream().collect(Collectors.toList());
        response.setSeries(NewsOfSeries.stream().map(serie->{
            GetNews.SerieDto serieResponse = new GetNews.SerieDto();
            BeanUtils.copyProperties(serie,serieResponse);
            return serieResponse;
        }).collect(Collectors.toList()));
    }

    //En esta aplicacion, el esquema de resiliencia consiste en realizar 3 intentos y si el sistema continua
    // dando error, aun podemos emplear el metodo offline para acceder a la base de datos nosql de catalogo.
    public GetNews getNewsOffline(){
        GetNews response = new GetNews();
        var NewsOfSeries = serieRepositoryMongo.findAll().stream().collect(Collectors.toList());
        response.setSeries(NewsOfSeries.stream().map(serie->{
            GetNews.SerieDto serieResponse = new GetNews.SerieDto();
            BeanUtils.copyProperties(serie,serieResponse);
            return serieResponse;
        }).collect(Collectors.toList()));


        var NewsOfMovies = movieRepositoryMongo.findAll().stream().collect(Collectors.toList());
        response.setMovies(NewsOfMovies.stream().map(movie->{
            GetNews.MovieDto movieResponse = new GetNews.MovieDto();
            BeanUtils.copyProperties(movie,movieResponse);
            return movieResponse;
        }).collect(Collectors.toList()));
        return response;
    }

}
