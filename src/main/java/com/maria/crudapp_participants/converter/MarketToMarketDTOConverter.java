package com.maria.crudapp_participants.converter;

import com.maria.crudapp_participants.dto.MarketDTO;
import com.maria.crudapp_participants.entity.Market;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MarketToMarketDTOConverter implements Converter<Market, MarketDTO> {
    @Override
    public MarketDTO convert(Market source) {
        return MarketDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .marketTemplateName(source.getMarketTemplateName())
                .event(source.getEvent())
                .selections(source.getSelections())
                .build();
    }
}
