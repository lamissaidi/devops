package tn.esprit.rh.achat.spring.service;

import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

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
@ContextConfiguration(classes = {FournisseurServiceImpl.class})
public class ReglementTest {

    private Reglement reglement;

    private ReglementServiceImpl service;
    private ReglementRepository repository;

    @Test
    public void getReglementTest(){
        System.out.println(" get test reglement");
        long id = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        long id2 = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        repository = mock(ReglementRepository.class);
        service = new ReglementServiceImpl(repository);

        List<Reglement> reglementList = new ArrayList<>();
        reglementList.add(new Reglement(id,23,210));
        reglementList.add(new Reglement(id2,23,220));
        when(repository.findAll()).thenReturn(reglementList);

    }}
