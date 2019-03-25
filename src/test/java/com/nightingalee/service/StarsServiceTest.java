package com.nightingalee.service;

import com.nightingalee.exception.NewException;
import com.nightingalee.model.Stars;
import com.nightingalee.repository.StarsRepo;
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
    private StarsRepo starsRepo;

    @Test
    public void addSta() {
        Stars e = new Stars();
        Mockito.when(starsRepo.save(e)).thenReturn(e);
        assertEquals(starsService.addSta(e), e);
    }

    @Test
    public void removeCon() {
        starsRepo.deleteById(18L);
        Mockito.verify(starsRepo,
                Mockito.times(1)).deleteById(18L);
    }

    @Test
    public void updateCon() throws NewException {
        assertThrows(NewException.class, () -> {
            starsService.updateSta(2L, "pustyName");
        });
    }

    @Test
    public void fidSta() {
        Stars e = new Stars();
        List<Stars> c = new ArrayList<>();
        c.add(e);
        Mockito.when(starsRepo.findAll()).thenReturn(c);
        assertEquals(starsService.findSta(), c);

    }

}