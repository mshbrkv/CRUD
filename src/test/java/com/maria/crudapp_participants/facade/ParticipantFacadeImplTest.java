package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.service.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParticipantFacadeImplTest {

    private final ParticipantService participantService;
    private final Converter<Participant, ParticipantDTO> participantToParticipantDTOConverter;
    private final Converter<ParticipantDTO, Participant> participantDTOToParticipantConverter;
    private final ParticipantFacadeImpl participantFacade;

    public ParticipantFacadeImplTest() {
        this.participantService = mock(ParticipantService.class);
        this.participantToParticipantDTOConverter = mock(Converter.class);
        this.participantDTOToParticipantConverter = mock(Converter.class);
        this.participantFacade = new ParticipantFacadeImpl(participantService, participantToParticipantDTOConverter, participantDTOToParticipantConverter);
    }

    @Test
    void saveParticipant() {
        ParticipantDTO participantDto = mock(ParticipantDTO.class);
        Participant participant = mock(Participant.class);

        when(participantDTOToParticipantConverter.convert(participantDto)).thenReturn(participant);
        when(participantService.saveParticipant(participant)).thenReturn(participant);
        when(participantToParticipantDTOConverter.convert(participant)).thenReturn(participantDto);

        ParticipantDTO participantDTO = participantFacade.saveParticipant(participantDto);

        assertThat(participantDTO).isEqualTo(participantDto);
        verify(participantService, times(1)).saveParticipant(participant);
    }

    @Test
    void fetchParticipantsList() {
        Pageable pageable = PageRequest.of(0, 3);
        String searchString = "Sh";
        Page<Participant> participants = mock(Page.class);
        List<ParticipantDTO> participantDto = new ArrayList<>();


        when(participantService.fetchParticipantsList(searchString, pageable)).thenReturn(participants);
        when(participants.getContent().stream().map(participantToParticipantDTOConverter::convert).toList()).thenReturn(participantDto);
        when(participants.getPageable()).thenReturn(pageable);
        when(participants.getTotalElements()).thenReturn(3L);

        Page<ParticipantDTO> expected = new PageImpl<>(participantDto, pageable, 3L);
        Page<ParticipantDTO> actual = participantFacade.fetchParticipantsList(searchString, pageable);

        assertThat(actual).isEqualTo(expected);
        verify(participantService, times(1)).fetchParticipantsList(searchString, pageable);
    }

    @Test
    void updateParticipant() {
        ParticipantDTO participantDto = mock(ParticipantDTO.class);
        Participant participant = mock(Participant.class);

        when(participantDTOToParticipantConverter.convert(participantDto)).thenReturn(participant);
        when(participantService.updateParticipant(participant, participant.getId())).thenReturn(participant);
        when(participantToParticipantDTOConverter.convert(participant)).thenReturn(participantDto);

        ParticipantDTO participantDTO = participantFacade.updateParticipant(participantDto, participantDto.getId());

        assertThat(participantDTO).isEqualTo(participantDto);
        verify(participantService, times(1)).updateParticipant(participant, participant.getId());
    }

    @Test
    void deleteParticipantById() {
        participantFacade.deleteParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        verify(participantService, times(1)).deleteParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
    }

    @Test
    void findParticipantById() {
        ParticipantDTO participantDto = mock(ParticipantDTO.class);
        Participant participant = mock(Participant.class);

        when(participantService.findParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"))).thenReturn(participant);
        when(participantToParticipantDTOConverter.convert(participant)).thenReturn(participantDto);

        ParticipantDTO participantDTO = participantFacade.findParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));

        assertThat(participantDTO).isEqualTo(participantDto);
        verify(participantService, times(1)).findParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
    }
}