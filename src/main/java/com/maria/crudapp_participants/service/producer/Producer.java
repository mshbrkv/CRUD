package com.maria.crudapp_participants.service.producer;

import com.maria.crudapp_participants.selections.Selection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Selection selection) {
        kafkaTemplate.send("t.trading.selection", selection.toString());
    }

}


