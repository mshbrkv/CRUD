package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.MarketDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MarketFacade {
    Page<MarketDTO> getEventList(Pageable pageable);
}
