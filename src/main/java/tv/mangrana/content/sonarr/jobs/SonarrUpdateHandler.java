package tv.mangrana.content.sonarr.jobs;

import tv.mangrana.config.ConfigFileLoader;
import tv.mangrana.content.UpdateHandler;
import tv.mangrana.exception.IncorrectWorkingReferencesException;
import tv.mangrana.plex.url.PlexCommandLauncher;

import java.io.File;

import static tv.mangrana.content.sonarr.jobs.SonarrJobFile.UpdateInfo.SONARR_SERIES_PATH;

public class SonarrUpdateHandler implements UpdateHandler {

    private final ConfigFileLoader configFileLoader;

    private SonarrUpdateHandler(ConfigFileLoader configFileLoader) {
        this.configFileLoader = configFileLoader;
    }

    public static SonarrUpdateHandler getInstance(ConfigFileLoader configFileLoader) {
        return new SonarrUpdateHandler(configFileLoader);
    }

    public void handle(File jobFile) throws IncorrectWorkingReferencesException {
        SonarrJobFile sonarrJobFile = new SonarrJobFile(jobFile);
        String path = sonarrJobFile.getInfo(SONARR_SERIES_PATH);
        PlexCommandLauncher plexRefresher = new PlexCommandLauncher(configFileLoader);
        plexRefresher.scanByPath(path);
        sonarrJobFile.markDone();
    }

}
