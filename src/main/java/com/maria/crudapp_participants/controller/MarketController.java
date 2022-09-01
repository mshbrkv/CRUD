package com.maria.crudapp_participants.controller;


import com.maria.crudapp_participants.dto.EventDTO;
import com.maria.crudapp_participants.dto.MarketDTO;
import com.maria.crudapp_participants.entity.Market;
import com.maria.crudapp_participants.facade.EventFacade;
import com.maria.crudapp_participants.facade.MarketFacade;
import com.maria.crudapp_participants.facade.MarketFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.Mark;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/markets")
public class MarketController {
    private final MarketFacade marketFacade;

    @GetMapping
    public Page<MarketDTO> getEventList(Pageable pageable) {
        return marketFacade.getMarketList(pageable);
    }
    @PostMapping
    public MarketDTO createMarket(@RequestBody MarketDTO market ){
        return marketFacade.saveMarket(market);
    }
    @PutMapping("{id}")
    public MarketDTO updateMarket(@RequestBody MarketDTO newMarket, @PathVariable UUID id){
        return marketFacade.updateMarket(newMarket,id);
    }
}
