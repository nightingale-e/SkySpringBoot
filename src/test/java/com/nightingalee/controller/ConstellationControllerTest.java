package com.nightingalee.controller;

//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;

import com.nightingalee.model.Constellations;
import com.nightingalee.service.ConstellationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ConstellationControllerTest {
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(ConstellationController.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }

    @InjectMocks
    private ConstellationController starControllerMock;

    @Mock
    private ConstellationService constellationServiceMock;


    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(starControllerMock).build();
    }

    @Test
    public void addStar() throws Exception {

        Constellations constellations = new Constellations("Abc", 2, 3);
        Mockito.when(constellationServiceMock.changeStarConstellation(18L, "Abc"))
                .thenReturn(constellations);
        mockMvc.perform(MockMvcRequestBuilders.post("/constellations/addStar/18/Abc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.declination").value(3));

    }

    @Test
    public void longest() throws Exception {
        Constellations constellations = new Constellations("Qwerty", 2, 3);
//        Constellations constellations1 = new Constellations("Qwertyui", 3, 2);
//        List<Constellations> constellationsList = new ArrayList<>();
//        constellationsList.add(constellations);
//        constellationsList.add(constellations1);
        Mockito.when(constellationServiceMock.longestNameOfConstellation()).thenReturn(constellations.getName());
        mockMvc.perform(MockMvcRequestBuilders.get(("/constellations/longestName")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Qwerty"));
    }

    @Test
    public void highest() throws Exception {
        Constellations constellations = new Constellations("Qwerty", 2, 3);
        Constellations constellations1 = new Constellations("Qwe", 3, 2);
        List<Constellations> constellationsList = new ArrayList<>();
        constellationsList.add(constellations);
        constellationsList.add(constellations1);
        Mockito.when(constellationServiceMock.nameOfHighestConstellation()).thenReturn(constellations.getName());
        mockMvc.perform(MockMvcRequestBuilders.get(("/constellations/highestStar")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Qwerty"));
    }

    @Test
    public void brightest() throws Exception {
        Constellations constellations = new Constellations("Qwerty", 2, 3);
//        List<Stars> conList = new ArrayList<>();
//        conList.add(new Stars("a", -1));
//        constellations.setStars(conList);
//        Constellations constellations1 = new Constellations("Qwe", 3, 2);
//        List<Stars> conList1 = new ArrayList<>();
//        conList1.add(new Stars("a", 2));
//        constellations1.setStars(conList1);
        Mockito.when(constellationServiceMock.nameOfConstellationHasBrightestStar()).thenReturn(constellations.getName());
        mockMvc.perform(MockMvcRequestBuilders.get(("/constellations/brightestStar")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Qwerty"));
    }
}
