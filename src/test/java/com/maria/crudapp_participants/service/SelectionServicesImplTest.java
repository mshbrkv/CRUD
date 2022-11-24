package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Event;
import com.maria.crudapp_participants.entity.Market;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.repository.SelectionRepository;
import com.maria.crudapp_participants.selections.Selection;
import com.maria.crudapp_participants.service.producer.Producer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SelectionServicesImplTest {

    @Mock
    SelectionRepository selectionRepository;
    @Mock
    Producer producer;

    @InjectMocks
    SelectionServicesImpl selectionServices;

    @Test
    void updateSelectionWithResult() {
        Participant participant = new Participant(UUID.fromString("cc6af87c-21ab-4008-bef2-3276a29994ea"), "aaa", "dance", "aaa", "123");
        Market market = new Market(UUID.fromString("cc6af87c-21ab-4008-bef2-3276a29994ea"), "aaa", "aaa", new Event(UUID.fromString("cc6af87c-21ab-4008-bef2-3276a29994ea"), "aaa", new Date(), true, List.of(participant), List.of(new Market())), List.of(new Selection()));
        Selection expected = new Selection(UUID.fromString("cc6af87c-21ab-4008-bef2-3276a29994ea"), "aaaa", BigDecimal.valueOf(100), market, "");
        String result = "lost";
        when(selectionRepository.findById(UUID.fromString("cc6af87c-21ab-4008-bef2-3276a29994ea"))).thenReturn(Optional.of(expected));
        org.example.messaging.Selection avroSelection=org.example.messaging.Selection.newBuilder()
                .setId(expected.getId().toString())
                .setName(expected.getName())
                .setPrice(expected.getPrice().doubleValue())
                .setSport(expected.getMarket().getEvent().getParticipants().get(0).getSport())
                .setResult(result)
                .setMarket(expected.getMarket().toString())
                .build();
        producer.sendMessage(avroSelection);

        Optional<Selection> actual = selectionServices.updateSelectionWithResult(UUID.fromString("cc6af87c-21ab-4008-bef2-3276a29994ea"), result);
        assertThat(actual).isNotEqualTo(expected);
    }
}