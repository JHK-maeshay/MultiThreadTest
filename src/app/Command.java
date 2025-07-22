package app;

import java.util.logging.Logger;

public interface Command {
    void execute(float f, Logger logger);
}
