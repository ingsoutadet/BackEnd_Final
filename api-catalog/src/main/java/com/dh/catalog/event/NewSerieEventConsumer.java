package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.SerieEntity;
import com.dh.catalog.repository.SerieRepositoryMongo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Component
public class NewSerieEventConsumer {
    private final SerieRepositoryMongo serieRepositoryMongo;


    public NewSerieEventConsumer(SerieRepositoryMongo serieRepositoryMongo) {
        this.serieRepositoryMongo = serieRepositoryMongo;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_SERIE)
    public void receiveMessage(NewSerieEventConsumer.Data data){
        SerieEntity serieNew = new SerieEntity();
        BeanUtils.copyProperties(data.getSerie(),serieNew);
        serieRepositoryMongo.deleteById(data.getSerie().getSerieId());
        serieRepositoryMongo.save(serieNew);

    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        private SerieDto serie = new SerieDto();

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class SerieDto {
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
    }
}
