package com.dh.catalog.event;


import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.MovieEntity;
import com.dh.catalog.repository.MovieRepositoryMongo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;


@Component
public class NewMovieEventConsumer {
    private final MovieRepositoryMongo movieRepositoryMongo;


    public NewMovieEventConsumer(MovieRepositoryMongo movieRepositoryMongo) {
        this.movieRepositoryMongo = movieRepositoryMongo;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_MOVIE)
    public void receiveMessage(NewMovieEventConsumer.Data data){
        MovieEntity movieNew = new MovieEntity();
        BeanUtils.copyProperties(data.getMovie(),movieNew);
        movieRepositoryMongo.deleteById(data.getMovie().getId());
        movieRepositoryMongo.save(movieNew);

    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Data implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        private MovieDto movie = new MovieDto();

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public class MovieDto implements Serializable {
            private Long id;
            private String name;
            private String genre;
            private String urlStream;

        }
    }
}
