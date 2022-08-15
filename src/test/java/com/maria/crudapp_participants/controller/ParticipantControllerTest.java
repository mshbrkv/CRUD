//package com.maria.crudapp_participants.controller;
//
//import com.maria.crudapp_participants.dto.PaginatedParticipantDTO;
//import com.maria.crudapp_participants.entity.Participant;
//import com.maria.crudapp_participants.service.ParticipantService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.ui.ExtendedModelMap;
//import org.springframework.ui.Model;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//class ParticipantControllerTest {
//
//    private final ParticipantService participantService = mock(ParticipantService.class);
//
//    private final ParticipantController controller = new ParticipantController(participantService);
//
//    @Test
//    public void fetchParticipantsWhenQueryIsNull() {
//        Participant participant1 = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
//        Participant participant2 = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
//        List<Participant> participantList = Arrays.asList(participant1, participant2);
//        Page<Participant> participantPage = new PageImpl<>(participantList);
//        when(participantService.fetchParticipantList(1, 3))
//                .thenReturn(PaginatedParticipantDTO.builder()
//                        .participants(participantPage.toList())
//                        .availablePages(participantPage.getTotalPages())
//                        .build());
//
//        Model model = new ExtendedModelMap();
//        String res = controller.fetchParticipantList(null, 1, model);
//        Object expectedAllParticipants = model.getAttribute("allParticipants");
//        List<Participant> expectedAllParticipantsList = (List<Participant>) expectedAllParticipants;
//
//        Assertions.assertNotNull(expectedAllParticipantsList);
//        Assertions.assertEquals(2
//                , expectedAllParticipantsList.size());
//        Assertions.assertEquals(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), participantList.get(0).getId());
//        Assertions.assertEquals("main_page", res);
//    }
//
//    @Test
//    public void fetchParticipantsWhenQueryNotNull() {
//        Participant participant1 = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
//        Participant participant2 = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
//        List<Participant> participantList = Arrays.asList(participant1, participant2);
//        Page<Participant> participantPage = new PageImpl<>(participantList);
//        when(participantService.searchFlexible("Sheriff", 1, 3))
//                .thenReturn(Optional.of(PaginatedParticipantDTO.builder()
//                        .participants(participantPage.toList())
//                        .availablePages(participantPage.getTotalPages())
//                        .build()));
//
//        Model model = new ExtendedModelMap();
//        String res1 = controller.fetchParticipantList("Sheriff", 1, model);
//        String res2 = controller.fetchParticipantList("rr", 1, model);
//        Object expectedAllParticipants = model.getAttribute("allParticipants");
//        Object expectedQuery = model.getAttribute("query");
//
//        Assertions.assertTrue(expectedAllParticipants instanceof List<?>);
//        Assertions.assertEquals(expectedQuery, "Sheriff");
//        Assertions.assertEquals("main_page", res1);
//        Assertions.assertEquals("not_found", res2);
//
//    }
//
//
//    @Test
//    void newParticipantPage() {
//        Model model = new ExtendedModelMap();
//        String res = controller.newParticipantPage(model);
//
//        Assertions.assertEquals("new_participant", res);
//    }
//
//    @Test
//    void updateParticipantPage() {
//        Model model = new ExtendedModelMap();
//        String res = controller.updateParticipantPage(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), model);
//
//        Assertions.assertEquals("update_participant", res);
//    }
//
//    @Test
//    void saveParticipant() {
//        Participant participant = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
//
//        when(participantService.saveParticipant(participant)).thenReturn(participant);
//
//        String res = controller.saveParticipant(participant);
//
//        Assertions.assertEquals("redirect:/participants", res);
//    }
//
//    @Test
//    void updateParticipant() {
//        Participant participant = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "GGGGG", "kkkkkk", "Moldova", 342);
//
//        when(participantService.updateParticipant(participant, UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"))).thenReturn(participant);
//
//        String res = controller.updateParticipant(participant);
//
//        Assertions.assertEquals("redirect:/participants", res);
//    }
//
//    @Test
//    void deleteParticipantById() {
//        String res = controller.deleteParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
//
//        Assertions.assertEquals("redirect:/participants", res);
//    }
//}