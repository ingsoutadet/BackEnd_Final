package com.dh.serie.service;

import com.dh.serie.event.NewSerieProducer;
import com.dh.serie.model.Serie;
import com.dh.serie.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {
    private final SerieRepository serieRepository;
    private final NewSerieProducer newSerieProducer;

    public SerieService(SerieRepository serieRepository, NewSerieProducer newSerieProducer) {
        this.serieRepository = serieRepository;
        this.newSerieProducer = newSerieProducer;
    }

    public Serie save(Serie serie){
        newSerieProducer.sendMessage(serie);
        return serieRepository.save(serie);
    }

    public List<Serie> getAll(){return serieRepository.findAll();}
    public Serie getById(Long id){return serieRepository.findById(id).orElse(null);}
    public void deleteById(Long id){serieRepository.deleteById(id);}
    public void update(Serie serie){
        if(serieRepository.existsById(serie.getSerieId())){
            serieRepository.save(serie);
        }}

    public List<Serie> findByGenre(String genre) {
        return serieRepository.findByGenre(genre);
    }
}
