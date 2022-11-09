package tv.mangrana.content;

import tv.mangrana.config.ConfigFileLoader;
import tv.mangrana.content.radarr.jobs.RadarrUpdateHandler;
import tv.mangrana.content.sonarr.jobs.SonarrUpdateHandler;
import tv.mangrana.exception.IncorrectWorkingReferencesException;
import tv.mangrana.jobs.JobFileManager;
import tv.mangrana.utils.Output;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tv.mangrana.config.ConfigFileLoader.ProjectConfiguration.JOB_RADARR_FILE_IDENTIFIER_REGEX;
import static tv.mangrana.config.ConfigFileLoader.ProjectConfiguration.JOB_SONARR_FILE_IDENTIFIER_REGEX;
import static tv.mangrana.utils.Output.log;

public class UpdatedItemsHandler {

    ConfigFileLoader configFileLoader;
    Map<JobFileManager.JobFileType, UpdateHandler> handlersMap = new HashMap<>();

    private UpdatedItemsHandler() throws IncorrectWorkingReferencesException {
        log("") ;log("");
        log("*******************************************************************************");
        log("********* Hi my friends, here the updated elements handler. *******************");
        log("********* For deletion / path relocation / etc in Radarr, Sonarr... ***********");
        log("*******************************************************************************");
        configFileLoader = new ConfigFileLoader();
        handlersMap.put(JobFileManager.JobFileType.SONARR_JOBS, SonarrUpdateHandler.getInstance(configFileLoader));
        handlersMap.put(JobFileManager.JobFileType.RADARR_JOBS, RadarrUpdateHandler.getInstance(configFileLoader));
    }

    public static void main(String[] args) throws IncorrectWorkingReferencesException {
        new UpdatedItemsHandler().process();
    }

    private void process() {
        processType(JOB_SONARR_FILE_IDENTIFIER_REGEX, JobFileManager.JobFileType.SONARR_JOBS);
        processType(JOB_RADARR_FILE_IDENTIFIER_REGEX, JobFileManager.JobFileType.RADARR_JOBS);
    }

    private void processType(ConfigFileLoader.ProjectConfiguration fileIdentifierRegex, JobFileManager.JobFileType appType) {
        List<File> jobFiles = JobFileManager.retrieveJobFiles(configFileLoader.getConfig(fileIdentifierRegex), appType);
        if (!jobFiles.isEmpty()) {
            for (File jobFile : jobFiles) {
                try {
                    UpdateHandler handler = handlersMap.get(appType);
                    handler.handle(jobFile);
                } catch (Exception e) {
                    Output.log("error while handling update to {1}: {0}", e.getMessage(), jobFile.getName());
                    e.printStackTrace();
                }
            }
        }
    }

}
