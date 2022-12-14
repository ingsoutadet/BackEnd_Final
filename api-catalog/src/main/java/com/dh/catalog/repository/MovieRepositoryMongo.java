package com.dh.catalog.repository;

import com.dh.catalog.model.MovieEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositoryMongo extends MongoRepository<MovieEntity,Long> {
}
