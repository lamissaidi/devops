package tn.esprit.rh.achat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;
import tn.esprit.rh.achat.entities.Fournisseur; // Change the import to Fournisseur
import tn.esprit.rh.achat.repositories.FournisseurRepository; // Change the import to FournisseurRepository
import tn.esprit.rh.achat.services.FournisseurServiceImpl; // Change the import to FournisseurServiceImpl

public class FournisseurTest {

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Mock
    private FournisseurRepository fournisseurRepository; // Change the repository to FournisseurRepository

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addFournisseurTest() {
        Fournisseur newFournisseur = new Fournisseur();
        newFournisseur.setIdFournisseur(1L);
        newFournisseur.setCode("Sample Code");
        newFournisseur.setLibelle("Sample Libelle");

        when(fournisseurRepository.save(eq(newFournisseur))).thenReturn(newFournisseur);

        Fournisseur result = fournisseurService.addFournisseur(newFournisseur);

        assertThat(result).isNotNull();
        assertThat(result.getIdFournisseur()).isNotNull();
        assertThat(result.getCode()).isEqualTo("Sample Code");
        assertThat(result.getLibelle()).isEqualTo("Sample Libelle");

        verify(fournisseurRepository, times(1)).save(eq(newFournisseur));
    }

    @Test
    public void getAllFournisseursTest() {
        List<Fournisseur> fournisseurList = new ArrayList<>();
        fournisseurList.add(new Fournisseur());
        Mockito.when(fournisseurRepository.findAll()).thenReturn(fournisseurList);

        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        // Add additional assertions as needed for the Fournisseur attributes

        verify(fournisseurRepository, times(1)).findAll();
    }

    @Test
    public void getFournisseurTest() {
        Long fournisseurIdToRetrieve = 1L;
        Fournisseur expectedFournisseur = new Fournisseur();
        expectedFournisseur.setIdFournisseur(fournisseurIdToRetrieve);

        when(fournisseurRepository.findById(fournisseurIdToRetrieve)).thenReturn(Optional.of(expectedFournisseur));

        Fournisseur result = fournisseurService.retrieveFournisseur(fournisseurIdToRetrieve);

        verify(fournisseurRepository, times(1)).findById(fournisseurIdToRetrieve);
        assertEquals(expectedFournisseur, result);
    }
}
