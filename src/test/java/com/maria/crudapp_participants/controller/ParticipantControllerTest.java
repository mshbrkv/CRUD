package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.service.ParticipantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ParticipantControllerTest {

    private final ParticipantService participantService = mock(ParticipantService.class);

    private final ParticipantController controller = new ParticipantController(participantService);

//    @Test
//    public void fetchParticipantsWhenQueryIsNull() {
//        when(participantService.fetchParticipantList(1,5))
//                .thenReturn(List.of(Participant.builder().id(100L).build()));
//
//        Model model = new ExtendedModelMap();
//        String rez = controller.fetchParticipantList(null,1, model);
//        Object expectedAllParticipants = model.getAttribute("allParticipants");
//        List<Participant> participantList = (List<Participant>) expectedAllParticipants;
//
//        Assertions.assertNotNull(participantList);
//        Assertions.assertEquals(1, participantList.size());
//        Assertions.assertEquals(100L, participantList.get(0).getId());
//        Assertions.assertEquals("main_page", rez);
//    }

//    @Test
//    public void fetchParticipantsWhenQueryNotNull() {
//        when(participantService.searchFlexible("Sheriff",1,5))
//                .thenReturn(List.of(Participant.builder().name("Sheriff").build()));
//
//        Model model = new ExtendedModelMap();
//        String res1 = controller.fetchParticipantList("Sheriff", 1,model);
//        String res2 = controller.fetchParticipantList("rr",1, model);
//        Object expectedAllParticipants = model.getAttribute("allParticipants");
//        Object expectedQuery = model.getAttribute("query");
//
//        Assertions.assertTrue(expectedAllParticipants instanceof List<?>);
//        Assertions.assertEquals(expectedQuery, "Sheriff");
//        Assertions.assertEquals("main_page", res1);
//        Assertions.assertEquals("not_found", res2);
//
//    }


    @Test
    void newParticipantPage() {
        Model model = new ExtendedModelMap();
        String res = controller.newParticipantPage(model);

        Assertions.assertEquals("new_participant", res);
    }

    @Test
    void updateParticipantPage() {
        Model model = new ExtendedModelMap();
        String res = controller.updateParticipantPage(1L, model);

        Assertions.assertEquals("update_participant", res);
    }

    @Test
    void saveParticipant() {
        Participant participant = new Participant(1L, "Sferiff", "football", "Moldova", 342);

        when(participantService.saveParticipant(participant)).thenReturn(participant);

        String res = controller.saveParticipant(participant);

        Assertions.assertEquals("redirect:/participants", res);
    }

    @Test
    void updateParticipant() {
        Participant participant = new Participant(1L, "GGGGG", "kkkkkk", "Moldova", 342);

        when(participantService.updateParticipant(participant, 1L)).thenReturn(participant);

        String res = controller.updateParticipant(participant);

        Assertions.assertEquals("redirect:/participants", res);
    }

    @Test
    void deleteParticipantById() {
        String res = controller.deleteParticipantById(1L);

        Assertions.assertEquals("redirect:/participants", res);
    }
}