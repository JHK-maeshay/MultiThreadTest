package utils;

import java.util.logging.*;

public class LogConfig {
    public static Logger createLogger(String name) {
        Logger logger = Logger.getLogger(name);
        logger.setLevel(Level.ALL);

        // 기존 핸들러 제거 (중복 방지)
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }

        try {
            // 파일 핸들러
            Handler fileHandler = new FileHandler("log.txt");
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);

            // 경고 이상은 콘솔 출력
            Handler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.WARNING);
            logger.addHandler(consoleHandler);

        } catch (Exception e) {
            System.err.println("Logger setup failed: " + e.getMessage());
        }

        return logger;
    }
}
