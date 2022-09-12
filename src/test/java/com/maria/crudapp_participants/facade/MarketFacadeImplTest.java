package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.MarketDTO;
import com.maria.crudapp_participants.entity.Market;
import com.maria.crudapp_participants.service.MarketService;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarketFacadeImplTest {


    private final MarketService marketService;
    private final Converter<Market, MarketDTO> marketToMarketDTOConverter;
    private final Converter<MarketDTO, Market> marketDTOToMarketConverter;
    private final MarketFacadeImpl marketFacade;

    public MarketFacadeImplTest() {
        this.marketService = mock(MarketService.class);
        this.marketToMarketDTOConverter = mock(Converter.class);
        this.marketDTOToMarketConverter = mock(Converter.class);
        this.marketFacade = new MarketFacadeImpl(marketService, marketToMarketDTOConverter, marketDTOToMarketConverter);
    }


    @Test
    void getMarketList() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Market> markets = mock(Page.class);
        List<MarketDTO> marketDTOList = new ArrayList<>();

        when(marketService.getAllMarketList(pageable)).thenReturn(markets);
        when(markets.getContent().stream().map(marketToMarketDTOConverter::convert).toList()).thenReturn(marketDTOList);
        when(markets.getPageable()).thenReturn(pageable);
        when(markets.getTotalElements()).thenReturn(3L);


        Page<MarketDTO> expected = new PageImpl<>(marketDTOList, pageable, 3L);
        Page<MarketDTO> actual = marketFacade.getMarketList(pageable);

        assertThat(actual).isEqualTo(expected);
        verify(marketService, times(1)).getAllMarketList(pageable);


    }

    @Test
    void saveMarket() {
        MarketDTO marketDTO=mock(MarketDTO.class);
        Market market=mock(Market.class);
        when(marketDTOToMarketConverter.convert(marketDTO)).thenReturn(market);
        when(marketService.saveMarket(market)).thenReturn(market);
        when(marketToMarketDTOConverter.convert(market)).thenReturn(marketDTO);
        MarketDTO actual=marketFacade.saveMarket(marketDTO);
        assertThat(actual).isEqualTo(marketDTO);
        verify(marketService,times(1)).saveMarket(market);
    }

    @Test
    void updateMarket() {
        MarketDTO marketDTO=mock(MarketDTO.class);
        Market market=mock(Market.class);
        when(marketDTOToMarketConverter.convert(marketDTO)).thenReturn(market);
        when(marketToMarketDTOConverter.convert(market)).thenReturn(marketDTO);
        when(marketService.updateMarket(market, market.getId())).thenReturn(market);
        MarketDTO actual=marketFacade.updateMarket(marketDTO, marketDTO.getId());

        assertThat(actual).isEqualTo(actual);
        verify(marketService, times(1)).updateMarket(market, market.getId());
    }
}