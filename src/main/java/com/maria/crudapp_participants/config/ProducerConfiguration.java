package com.maria.crudapp_participants.config;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.example.messaging.Selection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ProducerConfiguration {
    @Value(value = "${spring.kafka.bootstrapServers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.producer.key-serializer}")
    private String keySerializer;
    @Value(value = "${spring.kafka.producer.value-serializer}")
    private String valueSerializer;
    @Value(value = "${spring.kafka.producer.properties.schema.reqistry.url}")
    private String schemaRegistry;


    @Bean
    public Map<String, Object> configs() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        configs.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistry);
        return configs;
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name("t.trading.selection")
                .partitions(1)
                .replicas(1)
                .build();
    }

    //    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(configs());
//    }
    @Bean
    public ProducerFactory<String, Selection> producerFactory() {
        return new DefaultKafkaProducerFactory<>(configs());
    }

    @Bean
    public KafkaTemplate<String, Selection> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
