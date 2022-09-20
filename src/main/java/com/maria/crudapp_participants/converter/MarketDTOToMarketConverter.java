package com.maria.crudapp_participants.converter;

import com.maria.crudapp_participants.dto.MarketDTO;
import com.maria.crudapp_participants.entity.Market;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MarketDTOToMarketConverter implements Converter<MarketDTO, Market> {
    @Override
    public Market convert(MarketDTO source) {
        return Market.builder()
                .id(source.getId())
                .name(source.getName())
                .marketTemplateName(source.getMarketTemplateName())
                .event(source.getEvent())
                .selections(source.getSelections())
                .build();
    }
}
