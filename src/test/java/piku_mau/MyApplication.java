package piku_mau;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class MyApplication {
    private static final Logger logger = LogManager.getLogger(MyApplication.class);

  @Test
 public  void log() {
        logger.info("Hello, world!");
    }
}