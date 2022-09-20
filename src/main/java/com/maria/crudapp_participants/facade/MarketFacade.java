package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.MarketDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MarketFacade {
    Page<MarketDTO> getMarketList(Pageable pageable);
    MarketDTO saveMarket(MarketDTO newMarket);
    MarketDTO updateMarket(MarketDTO newMarket, UUID marketId);
}
