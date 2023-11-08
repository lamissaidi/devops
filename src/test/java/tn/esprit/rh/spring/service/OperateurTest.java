package tn.esprit.rh.spring.service;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.services.IOperateurService;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OperateurTest {

    @Autowired
    IOperateurService ios;

    @Test
    @Order(1)
    public void testRetreiveAllOp(){
        List<Operateur> frn = ios.retrieveAllReglements();
        Assertions.assertEquals(0, frn.size());
    }


}