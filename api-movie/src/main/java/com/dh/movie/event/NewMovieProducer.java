package com.dh.movie.event;

import com.dh.movie.config.RabbitMQConfig;
import com.dh.movie.model.Movie;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Component
public class NewMovieProducer {
    private static Logger log = LoggerFactory.getLogger(NewMovieProducer.class);
    private final RabbitTemplate rabbitTemplate;


    public NewMovieProducer(RabbitTemplate rabbitTemplate){this.rabbitTemplate = rabbitTemplate;}


    public void sendMessage(Movie movie){
        NewMovieProducer.Data data = new NewMovieProducer.Data();
        BeanUtils.copyProperties(movie,data.getMovie());
        if (data.getMovie() != null && movie != null){
            BeanUtils.copyProperties(movie,data.getMovie());
        }
        log.info("Sending message movie...");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.ROUTING_KEY_NEW_MOVIE,data);
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
        @Entity
        public class MovieDto implements Serializable {

            private static final long serialVersionUID = 1L;

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String name;
            private String genre;
            private String urlStream;

        }
    }
}
