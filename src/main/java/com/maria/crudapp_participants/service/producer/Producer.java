package com.maria.crudapp_participants.service.producer;

import com.maria.crudapp_participants.selections.Selection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    public void sendMessage(Selection selection) {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "localhost:9092");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        ProducerRecord<String, String> record = new ProducerRecord<>("t.trading.selection", "result of selection", selection.toString());
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProperties)) {
            producer.send(record, new DemoProducerCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class DemoProducerCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                e.printStackTrace();
            }
        }
    }
}


