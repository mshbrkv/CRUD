package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class ParticipantFacadeImpl implements ParticipantFacade {

    private final ParticipantService participantService;
    private final Converter<Participant, ParticipantDTO> participantToParticipantDTOConverter;
    private final Converter<ParticipantDTO, Participant> participantDTOToParticipantConverter;

    @Override
    public ParticipantDTO saveParticipant(ParticipantDTO participant) {
        Participant convert = participantDTOToParticipantConverter.convert(participant);
        Participant participant1 = participantService.saveParticipant(convert);
        return participantToParticipantDTOConverter.convert(participant1);
    }



    @Override
    public Page<ParticipantDTO> fetchParticipantsList(String searchString, Pageable pageable) {
       Page <Participant> allParticipants=participantService.fetchParticipantsList(searchString, pageable);
       List<ParticipantDTO> participantDTOS=allParticipants.getContent().stream().map(participantToParticipantDTOConverter::convert).toList();
       return new PageImpl<>(participantDTOS, allParticipants.getPageable(),allParticipants.getTotalElements());
    }


    @Override
    public ParticipantDTO updateParticipant(ParticipantDTO newParticipant, UUID participantId) {
        Participant convert=participantDTOToParticipantConverter.convert(newParticipant);
        Participant participant=participantService.updateParticipant(convert, newParticipant.getId());
        return participantToParticipantDTOConverter.convert(participant);

    }

    @Override
    public void deleteParticipantById(UUID participantId) {
        participantService.deleteParticipantById(participantId);
    }

    @Override
    public ParticipantDTO findParticipantById(UUID participantId) {
        Participant participant=participantService.findParticipantById(participantId);
       return participantToParticipantDTOConverter.convert(participant);

    }

}
