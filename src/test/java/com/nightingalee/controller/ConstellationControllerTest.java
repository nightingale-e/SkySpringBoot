package com.nightingalee.controller;

//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
import com.nightingalee.exception.NewException;
import com.nightingalee.model.Constellations;
import com.nightingalee.model.Stars;
import com.nightingalee.service.ConstellationService;
import com.nightingalee.service.StarsService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @Mock
    private StarsService starService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(starControllerMock).build();
    }

    @Test
    public void addStar() throws Exception {

        Constellations constellations = new Constellations("Abc",2,3);
        Mockito.when(constellationServiceMock.changeStarConstellation(18L,"Abc"))
                .thenReturn(constellations);
        mockMvc.perform(MockMvcRequestBuilders.post("/constellations/addStar/18/Abc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].declination").value(3));

    }

    @Test
    public void longest() throws Exception {
        Constellations constellations = new Constellations("Qwerty",2,3);
        Constellations constellations1 = new Constellations("Qwe",3,2);
        Mockito.when(constellationServiceMock.longestNazwaOfConstellation()).thenReturn(constellations.getName());
        mockMvc.perform(MockMvcRequestBuilders.get(("/constellations/longestNazwa")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("Qwerty"));
    }

    @Test
    public void highest() throws Exception {
        Constellations constellations = new Constellations("Qwerty",2,3);
        Constellations constellations1 = new Constellations("Qwe",3,2);
        Mockito.when(constellationServiceMock.longestNazwaOfConstellation()).thenReturn(constellations.getName());
        mockMvc.perform(MockMvcRequestBuilders.get(("/constellations/highestStar")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("nazwa").value("Qwerty"));
    }

    @Test
    public void brighest() throws Exception {
        Constellations constellations = new Constellations("Qwerty",2,3);
        List<Stars> conList = new ArrayList<>();
        conList.add(new Stars("a",-1));
        constellations.setStars(conList);
        Constellations constellations1 = new Constellations("Qwe",3,2);
        List<Stars> conList1 = new ArrayList<>();
        conList.add(new Stars("a",2));
        constellations.setStars(conList1);
        Mockito.when(constellationServiceMock.longestNazwaOfConstellation()).thenReturn(constellations.getName());
        mockMvc.perform(MockMvcRequestBuilders.get(("/constellations/brighestStar")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Qwerty"));
    }
}