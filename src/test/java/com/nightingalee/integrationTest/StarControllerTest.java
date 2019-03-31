package com.nightingalee.integrationTest;

import com.nightingalee.TestContext;

import com.nightingalee.controller.StarController;
import com.nightingalee.exception.NewException;
import com.nightingalee.model.Stars;
import com.nightingalee.repository.StarsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;


@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/insert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/delete.sql")})
public class StarControllerTest {

    @Autowired
    private StarController starController;

    @Autowired
    private StarsRepository starsRepository;


    @Test
    public void find_notEqual() {
        List<Stars> starsFromDb = starController.find();
        Assert.assertNotEquals(84, starsFromDb.size());
    }


    @Test
    public void find_Equal() {
        List<Stars> starsFromDb = starController.find();
        Assert.assertEquals(3, starsFromDb.size());
    }

    @Test
    public void add_star_success() throws NewException {

        Stars star = new Stars("Alpha Centauri", 2.5);
        star.setStarId(500L);
        Stars starResult = starController.add(star);

        Assert.assertEquals(starResult, star);
    }

    @Test(expected = NewException.class)
    public void add_star_failure() throws NewException {

        Stars star2 = new Stars("Betlgeza", 2.07);
        star2.setStarId(100L);
        starController.add(star2);
    }

    @Test(expected = NewException.class)
    public void delete_success() throws NewException {
        starController.remove(200L);
        findById(200L);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void delete_failed() throws NewException {
        starController.remove(900L);
    }


    @Test
    public void starsContainX_Test() {

        List<String> xStars = starController.namesContains();
        Assert.assertEquals(0, xStars.size());
    }

    @Test
    public void changeBrightness_success() throws NewException {
        Stars star = starController.changeBrightness(100L, 5.0);
        Assert.assertEquals(5.0, star.getBrightness(), 0.1);
    }


    @Test(expected = NewException.class)
    public void polarisPosition_fail() throws NewException {
        String position = "Twoje położenie to 50.0 szerokości geograficznej";
        Assert.assertEquals(position, starController.currentPosition(50));
    }


    @Test
    public void polarisPosition_success() throws NewException {
        String position = "Twoje położenie to 120.0 szerokości geograficznej";
        Assert.assertEquals(position, starController.currentPosition(120));
    }


    @Test
    public void ConstellationNameStarsList() {

        List<Stars> stars = starController.longestNameStars();
        Assert.assertEquals(2, stars.size());

    }


    public Stars findById(Long id) throws NewException {
        Optional<Stars> result = starsRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else throw new NewException("Star not found");
    }


}
