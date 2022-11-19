package com.maria.crudapp_participants.service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.messaging.Selection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {
//    @Value("${avro.topic.name}")
//    String topicName;

    private final KafkaTemplate<String, org.example.messaging.Selection> kafkaTemplate;



//    public void sendMessage(com.maria.crudapp_participants.selections.Selection selection) {
//        ListenableFuture<SendResult<String, com.maria.crudapp_participants.selections.Selection>> future = kafkaTemplate.send("t.trading.selection", String.valueOf(selection.getId()), selection);

    //        future.addCallback(new ListenableFutureCallback<>() {
//                               @Override
//                               public void onFailure(Throwable ex) {
//                                   System.out.println("Message failed to produce");
//                               }
//
//                               @Override
//                               public void onSuccess(SendResult<String, Selection> result) {
//                                   System.out.println("avro massage successfully produce");
//                               }
//                           }
//        );
//    }
    public void sendMessage(Selection selection) {
        ListenableFuture<SendResult<String, org.example.messaging.Selection>> future = kafkaTemplate.send("t.trading.selection" ,selection.getId().toString(), selection);
//        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("t.trading.selection", selection.toString());
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


