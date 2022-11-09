package tv.mangrana.content.sonarr.jobs;

import tv.mangrana.exception.IncorrectWorkingReferencesException;
import tv.mangrana.jobs.JobFile;
import tv.mangrana.utils.yml.FakeYmlLoader;

import java.io.File;
import java.util.EnumMap;
import java.util.Objects;

public class SonarrJobFile extends JobFile<SonarrJobFile.UpdateInfo> {

    public enum UpdateInfo {
        SONARR_SERIES_ID,
        SONARR_SERIES_TITLE,
        SONARR_SERIES_PATH
    }

    private final EnumMap<UpdateInfo, String> infoMap;

    public SonarrJobFile(File jobFile) throws IncorrectWorkingReferencesException {
        super(jobFile);
        infoMap = FakeYmlLoader.getEnumMapFromFile(jobFile, UpdateInfo.class, false);
    }

    @Override
    public String getInfo(UpdateInfo key) {
        return infoMap.get(key);
    }

    public boolean hasNoInfo() {
        return !Objects.nonNull(infoMap);
    }

}
