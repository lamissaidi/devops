package tn.esprit.rh.achat.spring.service;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@Configuration
@EnableAutoConfiguration
@ComponentScan
@RunWith( SpringRunner.class)
@ContextConfiguration(classes = {StockServiceImpl.class})
public class StockTest {

    private Stock stock;


    @Test
    public void testStock() {
        stock.setIdStock(26L);
        stock.setQte(26);
        stock.setLibelleStock("Hamza");
        assertEquals(26L,stock.getIdStock().longValue());
        assertEquals("Belaid", stock.getQte());
        assertEquals("Hamza", stock.getLibelleStock());
    }

    @Test
    public void testStockNotNull() {
        stock.setIdStock(26L);
        stock.setQte(26);
        stock.setLibelleStock("hamza");

        assertNotNull(stock.getIdStock());
        assertNotNull(stock.getQte());
        assertNotNull(stock.getLibelleStock());


    }

    private StockServiceImpl service;
    private StockRepository repository;

    @Test
    public void getStockTest(){
        System.out.println(" get test stock");
        long id = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        long id2 = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        repository = mock(StockRepository.class);
        service = new StockServiceImpl(repository);


        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock(id,"Belaid",100,5));
        stockList.add(new Stock(id2,"vest",50,10));
        when(repository.findAll()).thenReturn(stockList);

    }


}