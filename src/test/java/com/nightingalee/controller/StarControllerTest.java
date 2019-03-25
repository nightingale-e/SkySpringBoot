package com.nightingalee.controller;

import com.nightingalee.model.Constellations;
import com.nightingalee.model.Stars;
import com.nightingalee.repository.StarsRepo;
import com.nightingalee.service.StarsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.plugin2.util.PojoUtil.toJson;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StarControllerTest {

    @InjectMocks
    private StarController starControllerMock;

    @Mock
    private StarsService starService;

    @Mock
    private StarsRepo starsRepo;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(starControllerMock).build();
    }

    @Test
    public void add() throws Exception {

        Stars star = new Stars("a", 2);
        star.setConstellation(new Constellations());
        Mockito.when(starService.addSta(star))
                .thenReturn(star);
        mockMvc.perform(MockMvcRequestBuilders.post("/stars/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(star)))
                .andExpect(status().isOk());

        //.andExpect(MockMvcResultMatchers.status().isOk());
        // .andExpect(MockMvcResultMatchers.jsonPath("$[0].brightness").value(3));

    }


    @Test
    void remove() throws Exception {
        Stars star = new Stars("a", 2);
        star.setStarId(2L);
//        Mockito.verify(starService, Mockito.times(1)).removeSta(2L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/stars/remove/2")
                .param("id", String.valueOf(2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void find() throws Exception {
        Stars stars1 = new Stars("Qwerty", 2);
        Stars stars = new Stars("abc", 5);
        List<Stars> starsList = new LinkedList<>();
        starsList.add(stars1);
        starsList.add(stars);
        Mockito.when(starService.findSta()).thenReturn(starsList);
        mockMvc.perform(MockMvcRequestBuilders.get(("/stars/find")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nazwa").value("Qwerty"));
//        assertEquals(starsList.size(),2);
    }

    @Test
    void update() throws Exception {
        Stars star = new Stars("a", 2);
        star.setStarId(2L);
        star.setConstellation(new Constellations("Pies"));
        Mockito.when(starService.updateSta(2L, "Pies"))
                .thenReturn(star);
        mockMvc.perform(MockMvcRequestBuilders.post("/stars/update/2/Pies"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        //.andExpect(MockMvcResultMatchers.jsonPath("$.constellation.nazwa").value("Pies"));
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(toJson(star)))

    }

    @Test
    void changeBrightness() throws Exception {
        Stars star = new Stars("a", 2.0);
        star.setConstellation(new Constellations());
        Mockito.when(starService.changeBrightness(4L, 2)).thenReturn(star);

        mockMvc.perform(MockMvcRequestBuilders.post("/stars/changeBrightness/2/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(star)))
                .andExpect(status().isOk());
    }

    @Test
    void namesContain() throws Exception {
        Stars star = new Stars("xyz", 2.4);
        Stars star1 = new Stars("abc", 4);
        List<String> strings = new ArrayList<>();
        strings.add(star.getNazwa());
        Mockito.when(starService.nameContainingX()).thenReturn(strings);
        mockMvc.perform(MockMvcRequestBuilders.get(("/stars/namesContaingX")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("xyz"));
        //assertEquals(strings.size(),1);

    }

    @Test
    void currentPosition() throws Exception {

        Mockito.when(starService.changePolarisPosition(40)).thenReturn("Twoje położenie to 40 szeroości geograficznej");
//        Mockito.verify(starService, Mockito.times(1)).removeSta(2L);
        mockMvc.perform(MockMvcRequestBuilders.post("/stars/currentPosition/40")
                .param("dec", String.valueOf(40))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    void longestNameStars() throws Exception {
        Constellations constellations = new Constellations("a");
        Constellations constellations1 = new Constellations("ab");
        Stars stars = new Stars("abc", 2);
        List<Stars> starsList = new LinkedList<>();
        starsList.add(stars);
        constellations1.setStars(starsList);
        Mockito.when(starService.longestConstellationNameStars()).thenReturn(starsList);
        mockMvc.perform(MockMvcRequestBuilders.get(("/stars/starList")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nazwa").value("abc"));


        // assertEquals(starControllerMock.longestNameStars(),starsList);
    }
}