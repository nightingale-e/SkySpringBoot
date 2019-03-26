package com.nightingalee.service;

import com.nightingalee.exception.NewException;
import com.nightingalee.model.Stars;
import com.nightingalee.repository.StarsRepository;
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

@ExtendWith(MockitoExtension.class)
class StarsServiceTest {

    @InjectMocks
    private StarsService starsService;

    @Mock
    private StarsRepository starsRepository;

    @Test
    public void addSta() {
        Stars e = new Stars();
        Mockito.when(starsRepository.save(e)).thenReturn(e);
        assertEquals(starsService.addSta(e), e);
    }

    @Test
    public void removeCon() {
        starsRepository.deleteById(18L);
        Mockito.verify(starsRepository,
                Mockito.times(1)).deleteById(18L);
    }

    @Test
    public void updateCon() throws NewException {
        assertThrows(NewException.class, () -> {
            starsService.updateStarName(2L, "pustyName");
        });
    }

    @Test
    public void fidSta() {
        Stars e = new Stars();
        List<Stars> c = new ArrayList<>();
        c.add(e);
        Mockito.when(starsRepository.findAll()).thenReturn(c);
        assertEquals(starsService.findSta(), c);

    }

}