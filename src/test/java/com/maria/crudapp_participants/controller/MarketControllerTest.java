package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.dto.MarketDTO;
import com.maria.crudapp_participants.facade.MarketFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarketControllerTest {

    @InjectMocks
    MarketController marketController;
    @Mock
    private final MarketFacade marketFacade = mock(MarketFacade.class);


    @Test
    void getMarketList() {
        Pageable pageable= PageRequest.of(0,3);
        MarketDTO market1=new MarketDTO();
        MarketDTO market2=new MarketDTO();
        List<MarketDTO> marketDTOList= Arrays.asList(market1,market2);
        Page<MarketDTO> marketDTOPage=new PageImpl<>(marketDTOList);
        when(marketFacade.getMarketList(pageable)).thenReturn(marketDTOPage);
        Page<MarketDTO> actual=marketController.getMarketList(pageable);
        assertThat(actual.getTotalElements()).isEqualTo(2);
    }

    @Test
    void createMarket() {
        MarketDTO marketDTO=new MarketDTO();
        when(marketFacade.saveMarket(marketDTO)).thenReturn(marketDTO);
        MarketDTO actual=marketController.createMarket(marketDTO);
        assertThat(actual).isEqualTo(marketDTO);
    }

    @Test
    void updateMarket() {
        MarketDTO marketDTO=new MarketDTO(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        when(marketFacade.updateMarket(marketDTO,marketDTO.getId())).thenReturn(marketDTO);
        MarketDTO actual=marketController.updateMarket(marketDTO, marketDTO.getId());
        assertThat(actual).isEqualTo(marketDTO);
    }
}