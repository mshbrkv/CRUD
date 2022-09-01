package com.maria.crudapp_participants.controller;


import com.maria.crudapp_participants.dto.EventDTO;
import com.maria.crudapp_participants.dto.MarketDTO;
import com.maria.crudapp_participants.facade.EventFacade;
import com.maria.crudapp_participants.facade.MarketFacade;
import com.maria.crudapp_participants.facade.MarketFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/markets")
public class MarketController {
    private final MarketFacade marketFacade;

    @GetMapping
    public Page<MarketDTO> getEventList(Pageable pageable) {
        return marketFacade.getEventList(pageable);
    }

}
