package tv.mangrana.content.radarr.jobs;

import tv.mangrana.config.ConfigFileLoader;
import tv.mangrana.content.UpdateHandler;
import tv.mangrana.exception.IncorrectWorkingReferencesException;
import tv.mangrana.jobs.JobFile;
import tv.mangrana.plex.url.PlexCommandLauncher;

import java.io.File;

import static tv.mangrana.content.radarr.jobs.RadarrJobFile.UpdateInfo.RADARR_MOVIE_PATH;

public class RadarrUpdateHandler implements UpdateHandler {

    private final ConfigFileLoader configFileLoader;

    public RadarrUpdateHandler(ConfigFileLoader configFileLoader) {
        this.configFileLoader = configFileLoader;
    }

    public static RadarrUpdateHandler getInstance(ConfigFileLoader configFileLoader) {
        return new RadarrUpdateHandler(configFileLoader);
    }

    public void handle(File jobFile) throws IncorrectWorkingReferencesException {
        JobFile<RadarrJobFile.UpdateInfo> sonarrJobFile = new RadarrJobFile(jobFile);
        String path = sonarrJobFile.getInfo(RADARR_MOVIE_PATH);
        PlexCommandLauncher plexRefresher = new PlexCommandLauncher(configFileLoader);
        plexRefresher.scanByPath(path);
        sonarrJobFile.forceMarkDone();
    }

}
