package serveses;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;


public class AppLogger {
    private AppLogger() {
        throw new IllegalStateException("Utility class");
    }


    public static void setLevel(Logger logger)
    {
        setlevelLogger(logger);
    }
    private static void setlevelLogger(Logger logger) {
        logger.setLevel(INFO);
    }

}

