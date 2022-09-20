package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Market;
import com.maria.crudapp_participants.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MarketServiceImpl implements MarketService {
    private final MarketRepository marketRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Market> getAllMarketList(Pageable pageable) {
        return marketRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Market saveMarket(Market market) {
        return marketRepository.save(market);
    }

    @Override
    @Transactional
    public Market updateMarket(Market newMarket, UUID marketId) {
        Optional<Market> optionalMarket = marketRepository.findById(marketId);
        if (optionalMarket.isPresent()) {
            Market editMarket = optionalMarket.get();
            editMarket.setId(newMarket.getId());
            editMarket.setName(newMarket.getName());
            editMarket.setMarketTemplateName(newMarket.getMarketTemplateName());
            editMarket.setEvent(newMarket.getEvent());
            editMarket.setSelections(newMarket.getSelections());
            return marketRepository.save(editMarket);
        } else {
            return newMarket;
        }
    }

    @Override
    @Transactional
    public Market findMarketById(UUID marketId) {
        Optional<Market> marketOptional=marketRepository.findById(marketId);
        Market market=null;
        if (marketOptional.isPresent()){
            market=marketOptional.get();
        }
        return market;
    }
}
