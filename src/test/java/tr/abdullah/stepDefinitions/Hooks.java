package tr.abdullah.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void beforeMethod(Scenario scenario) {
        logger.info(scenario.getName() + " isimli test senaryosu kosumu basladi.");
    }

    @After
    public void afterMethod(Scenario scenario) {

        logger.info(scenario.getName() + " isimli test senaryosu kosumu bitti.");
        logger.info(scenario.getName() + " senaryosu test sonucu : " + scenario.getStatus());
    }
}