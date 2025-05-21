package tr.abdullah.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void beforeMethod() {
        logger.info("Test senaryosu kosumu basladi.");
    }

    @After
    public void afterMethod() {
        logger.info("Test senaryosu kosumu bitti.");
    }
}