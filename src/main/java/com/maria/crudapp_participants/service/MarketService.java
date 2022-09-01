package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Market;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MarketService {

    Page<Market> getAllMarketList(Pageable pageable);

//    Market saveMarket(Market Market);

//    Market updateMarket(Market newMarket, UUID MarketId);

//    Page<Market> searchFlexible(String searchString, Pageable pageable);
//
//    Page<Market> fetchMarketsList(String searchString, Pageable pageable);

//    Market findMarketById(final UUID MarketId);
}
