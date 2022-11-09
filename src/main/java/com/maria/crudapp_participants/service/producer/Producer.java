package com.maria.crudapp_participants.service.producer;

import com.maria.crudapp_participants.selections.Selection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, GenericRecord> kafkaTemplate;

    String selectionSchema = "{\"type\":\"record\",\"" +
            "                    \"\"name\":\"record\",\"" +
            "                    \"\"fields\":[{\"name:\":\"id\", \"type\":\"UUID\"},{\"name:\":\"name\", \"type\":\"String\"},{\"name:\":\"price\", \"type\":\"BigDecimal\"},{\"name:\":\"market\", \"type\":\"Market\"},{\"name:\":\"result\", \"type\":\"String\"}]}";
    Schema.Parser parser = new Schema.Parser();
    Schema schema = parser.parse(selectionSchema);
    GenericRecord avroRecord = new GenericData.Record(schema);
    ProducerRecord<Object, Object> record = new ProducerRecord<>("t.trading.selection", null, avroRecord);

    public void sendMessage(Selection selection) {
        kafkaTemplate.send("t.trading.selection", avroRecord);
    }

}


