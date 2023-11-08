package tn.esprit.rh.achat.spring.service;


import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;

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
@ContextConfiguration(classes = {FactureServiceImpl.class})
public class FactureTest {

    private Facture facture;


    @Test
    public void testFacture() {
        facture.setIdFacture(26L);
        facture.setMontantRemise(26);
        facture.setMontantFacture(50);
        assertEquals(26L,facture.getIdFacture().longValue());
        assertEquals("saidi", facture.getMontantFacture());
        assertEquals("Lamis", facture.getMontantRemise());
    }

    @Test
    public void testFactureNotNull() {
        facture.setIdFacture(26L);
        facture.setMontantRemise(26);
        facture.setMontantFacture(50);

        assertNotNull(facture.getIdFacture());
        assertNotNull(facture.getMontantFacture());
        assertNotNull(facture.getMontantRemise());


    }

    private FactureServiceImpl service;
    private FactureRepository repository;

    @Test
    public void getFactureTest(){
        System.out.println(" get test facture");
        long id = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        long id2 = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        repository = mock(FactureRepository.class);
        service = new FactureServiceImpl(repository);

        List<Facture> factureList = new ArrayList<>();
        factureList.add(new Facture(id,26,50));
        factureList.add(new Facture(id2,50,26));
        when(repository.findAll()).thenReturn(factureList);

    }


}
