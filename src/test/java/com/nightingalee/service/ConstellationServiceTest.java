package com.nightingalee.service;

import com.nightingalee.exception.NewException;
import com.nightingalee.model.Constellations;
import com.nightingalee.repository.ConstellationsRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//@RunWith(MockitoJUnitRunner.class)

@ExtendWith(MockitoExtension.class)
public class ConstellationServiceTest {

    @InjectMocks
    private ConstellationService constellationServiceMock;


    @Mock
    private ConstellationsRepo constellationsRepoMock;


    @Test
    public void addCon() {
        Constellations e = new Constellations();
        Mockito.when(constellationsRepoMock.save(e)).thenReturn(e);
        assertEquals(constellationServiceMock.addCon(e), e);
    }

    @Test
    public void addConSize() {
        Constellations e = new Constellations("e", 3.2, 2.1);
        Mockito.when(constellationServiceMock.addCon(e))
                .thenReturn((e));
        List<Constellations> constellations = new ArrayList<>();
        constellations.add(constellationServiceMock.addCon(e));

        assertEquals(constellations.size(), 1);
        assertEquals(constellations.get(0), e);

    }


    @Test
    public void removeCon() {
        constellationsRepoMock.deleteById("Wielka Niedżwiedzica");
        Mockito.verify(constellationsRepoMock,
                Mockito.times(1)).deleteById("Wielka Niedżwiedzica");
    }

    @Test
    public void findCon() {
        Constellations e = new Constellations();
        List<Constellations> c = new ArrayList<>();
        c.add(e);
        Mockito.when(constellationsRepoMock.findAll()).thenReturn(c);
        assertEquals(constellationServiceMock.findCon(), c);

    }

    @Test
    public void updateCon() throws NewException {

//        Mockito.when(constellationServiceMock.updateCon("abc",2,3))
//                .thenThrow(new NewException("Constellation does not exist"));
        assertThrows(NewException.class, () -> {
            constellationServiceMock.updateCon("a", 2, 3);
        });
    }

    @Test
    public void changeStarConst() {
        assertThrows(NewException.class, () -> {
            constellationServiceMock.changeStarConstellation(90L, "k");
        });
    }

}

