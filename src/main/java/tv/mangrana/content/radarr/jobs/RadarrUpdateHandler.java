package tv.mangrana.content.radarr.jobs;

import tv.mangrana.config.ConfigFileLoader;
import tv.mangrana.content.UpdateHandler;
import tv.mangrana.exception.IncorrectWorkingReferencesException;

import java.io.File;

public class RadarrUpdateHandler implements UpdateHandler {

    public static RadarrUpdateHandler getInstance(ConfigFileLoader configFileLoader) {
        return new RadarrUpdateHandler();
    }

    public void handle(File jobFile) throws IncorrectWorkingReferencesException {

    }

}
