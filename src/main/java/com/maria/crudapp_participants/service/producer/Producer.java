package com.maria.crudapp_participants.service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        ListenableFuture<SendResult<String, org.example.messaging.Selection>> future = kafkaTemplate.send("t.trading.selection", selection.getId().toString(), selection);
        System.out.println(selection);
        future.addCallback(new ListenableFutureCallback<SendResult<String, org.example.messaging.Selection>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Message failed to produce");
            }

            @Override
            public void onSuccess(SendResult<String, org.example.messaging.Selection> result) {
                System.out.println("avro massage successfully produce");
            }
        });

    }
}


