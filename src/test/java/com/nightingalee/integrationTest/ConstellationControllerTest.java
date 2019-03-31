package com.nightingalee.integrationTest;

import com.nightingalee.TestContext;
import com.nightingalee.controller.ConstellationController;
import com.nightingalee.exception.NewException;
import com.nightingalee.model.Constellations;
import com.nightingalee.repository.ConstellationsRepository;
import org.apache.tomcat.util.bcel.Const;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/insert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/delete.sql")})
public class ConstellationControllerTest {

    @Autowired
    ConstellationController constellationController;

    @Autowired
    ConstellationsRepository constellationsRepository;


    @Test
    public void add_constellation_success() {

        Constellations constellation = new Constellations("Alpha Centauri", 2.5, 3.5);
        Constellations c = constellationController.add(constellation);
        Assert.assertEquals(constellation, c);
    }

    @Test(expected = NewException.class)
    public void delete_successTest() throws NewException {
        constellationController.remove("Sombrero");
        findById("Sombrero");
    }


    @Test
    public void constellationName_Test() {
        String longest = constellationController.longest();
        Assert.assertEquals("PillarsOfCreation", longest);
    }




    // public Constellations update(@PathVariable String id, @PathVariable double dec, @PathVariable double ra) throws NewException {


    public Constellations findById(String name) throws NewException {
        List<Constellations> c = constellationsRepository.findAll();
        Constellations constellations = new Constellations();
        for (Constellations o : c) {
            if (c.contains(o.getName())) {
                constellations = o;
            } else throw new NewException("not found");
        }
        return constellations;
    }


}
