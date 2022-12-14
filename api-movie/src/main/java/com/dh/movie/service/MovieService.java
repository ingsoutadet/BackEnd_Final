package com.dh.movie.service;


import com.dh.movie.event.NewMovieProducer;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final NewMovieProducer newMovieProducer;

    public MovieService(MovieRepository movieRepository, NewMovieProducer newMovieProducer) {
        this.movieRepository = movieRepository;
        this.newMovieProducer = newMovieProducer;
    }

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        newMovieProducer.sendMessage(movie);
        return movieRepository.save(movie);
    }


    public List<Movie> getAll(){return movieRepository.findAll();}
    public Movie getById(Long id){return movieRepository.findById(id).orElse(null);}
    public void deleteById(Long id){movieRepository.deleteById(id);}
    public void update(Movie movie){
        if(movieRepository.existsById(movie.getId())){
            movieRepository.save(movie);
        }}



}
