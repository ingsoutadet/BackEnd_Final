package com.dh.serie.event;


import com.dh.serie.config.RabbitMQConfig;
import com.dh.serie.model.Serie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewSerieProducer {
    private static Logger log = LoggerFactory.getLogger(NewSerieProducer.class);
    private final RabbitTemplate rabbitTemplate;


    public NewSerieProducer(RabbitTemplate rabbitTemplate){this.rabbitTemplate = rabbitTemplate;}

    public void sendMessage(Serie serie){
        NewSerieProducer.Data data = new NewSerieProducer.Data();
        BeanUtils.copyProperties(serie,data.getSerie());
        if (data.getSerie() != null && serie != null){
            BeanUtils.copyProperties(serie,data.getSerie());
        }
        log.info("Sending message serie...");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.ROUTING_KEY_NEW_SERIE,data);
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Data implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        private SerieDto serie = new SerieDto();

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class SerieDto implements Serializable{
            @Id
            private Long serieId;
            private String serieName;
            private String serieGenre;
            private List<com.dh.serie.model.Serie.Season> seasons = new ArrayList<>();

            @Getter
            @Setter
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Season{
                private Long seasonId;
                private Integer seasonNumber;
                private List<com.dh.serie.model.Serie.Chapter> chapters = new ArrayList<>();
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


}

