package com.maria.crudapp_participants.service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.messaging.Selection;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, org.example.messaging.Selection> kafkaTemplate;


    public void sendMessage(Selection selection) {
        ListenableFuture<SendResult<String, Selection>> future = kafkaTemplate.send("t.trading.selection", String.valueOf(selection.getId()), selection);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Selection>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Message failed to produce");
            }

            @Override
            public void onSuccess(SendResult<String, Selection> result) {
                System.out.println("avro massage successfully produce");
            }
        });
    }

}


