package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.MarketDTO;
import com.maria.crudapp_participants.entity.Market;
import com.maria.crudapp_participants.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class MarketFacadeImpl implements MarketFacade {

    private final MarketService marketService;
    private final Converter<Market, MarketDTO> marketToMarketDTOConverter;
    private final Converter<MarketDTO, Market> marketDTOToMarketConverter;

    @Override
    public Page<MarketDTO> getMarketList(Pageable pageable) {
        Page<Market> allMarket = marketService.getAllMarketList(pageable);
        List<MarketDTO> eventDTO = allMarket.getContent().stream().map(marketToMarketDTOConverter::convert).toList();
        return new PageImpl<>(eventDTO, allMarket.getPageable(), allMarket.getTotalElements());

    }

    @Override
    public MarketDTO saveMarket(MarketDTO newMarket) {
        Market marketConvert = marketDTOToMarketConverter.convert(newMarket);
        Market market=marketService.saveMarket(marketConvert);
        return marketToMarketDTOConverter.convert(market);
    }

    @Override
    public MarketDTO updateMarket(MarketDTO newMarket, UUID marketId) {
        Market marketConvert = marketDTOToMarketConverter.convert(newMarket);
        Market market=marketService.updateMarket(marketConvert, newMarket.getId());
        return marketToMarketDTOConverter.convert(market);
    }
}
