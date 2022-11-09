package tv.mangrana.config;

import tv.mangrana.exception.IncorrectWorkingReferencesException;

public class ConfigFileLoader extends CommonConfigFileLoader<ConfigFileLoader.ProjectConfiguration> {

    private static final String CONFIG_FILE = "ArrModificationCapturerConfig.yml";

    public ConfigFileLoader() throws IncorrectWorkingReferencesException {
        super(ProjectConfiguration.class);
    }

    public enum ProjectConfiguration {
        JOB_SONARR_FILE_IDENTIFIER_REGEX,
        JOB_RADARR_FILE_IDENTIFIER_REGEX
    }

    @Override
    protected String getConfigFileName() {
        return CONFIG_FILE;
    }
}
