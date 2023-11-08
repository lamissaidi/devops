package tn.esprit.rh.spring.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.services.IReglementService;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReglementTest {

    @Autowired
    IReglementService irs;

    @Test
    @Order(1)
    public void testRetreiveAllOp(){
        List<Reglement> frn = irs.retrieveAllReglements();
        Assertions.assertEquals(0, frn.size());
    }


}