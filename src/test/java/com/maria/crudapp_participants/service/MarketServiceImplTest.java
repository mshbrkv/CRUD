package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Market;
import com.maria.crudapp_participants.repository.MarketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarketServiceImplTest {

    @Mock
    MarketRepository marketRepository;
    @InjectMocks
    MarketServiceImpl marketService;


    private static Stream<Arguments> updateMarketsProvider() {
        Market fromBD = new Market();
        Market edit = fromBD;
        edit.setName("Test");
        return Stream.of(
                Arguments.of(fromBD, edit),
                Arguments.of(null, edit)
        );
    }

    private static Stream<Arguments> findMarketByIdProvider(){
        Market fromBD=new Market(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        return  Stream.of(
                Arguments.of(fromBD),
                Arguments.of((Object) null)
        );
    }


    @Test
    void getAllMarketList() {
        Pageable pageable = PageRequest.of(0, 3);
        Market market1 = new Market();
        Market market2 = new Market();
        List<Market> marketList = Arrays.asList(market1, market2);
        Page<Market> marketPage = new PageImpl<>(marketList);
        when(marketRepository.findAll(pageable)).thenReturn(marketPage);
        Page<Market> actual = marketService.getAllMarketList(pageable);
        assertThat(actual).isEqualTo(marketPage);
    }

    @Test
    void saveMarket() {
        Market expected = new Market();
        when(marketRepository.save(expected)).thenReturn(expected);
        Market actual = marketService.saveMarket(expected);
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("updateMarketsProvider")
    void updateMarket(Market market, Market edit) {
        when(marketRepository.findById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"))).thenReturn(Optional.ofNullable(market));
        Market actual=marketService.updateMarket(edit,UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        assertThat(actual).isNotEqualTo(market);
    }

    @ParameterizedTest
    @MethodSource("findMarketByIdProvider")
    void findMarketById(Market market) {
when(marketRepository.findById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"))).thenReturn(Optional.ofNullable(market));
        Market actual=marketService.findMarketById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        assertThat(actual).isEqualTo(market);

    }
}