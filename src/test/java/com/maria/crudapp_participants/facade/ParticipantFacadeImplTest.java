package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.service.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
//        List<ParticipantDTO> participantDto = mock(List.class);
//        Page<Participant> participantPage = mock(Page.class);
//        Pageable pageable = mock(Pageable.class);
//        String searchString="Sheriff";
//        Page<ParticipantDTO> participantDTOPage=participantFacade.fetchParticipantsList(searchString,pageable);
//        when(participantService.fetchParticipantsList(searchString,pageable )).thenReturn(participantPage);
//        when(participantPage.getContent().stream().map(participantToParticipantDTOConverter::convert).toList()).thenReturn(participantDto);
//        assertThat(participantDTOPage).isEqualTo(participantDto);

    }

    @Test
    void updateParticipant() {

    }

    @Test
    void deleteParticipantById() {
    }
}