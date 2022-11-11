package tv.mangrana.content.radarr.jobs;

import tv.mangrana.exception.IncorrectWorkingReferencesException;
import tv.mangrana.jobs.JobFile;
import tv.mangrana.utils.yml.FakeYmlLoader;

import java.io.File;
import java.util.EnumMap;
import java.util.Objects;

public class RadarrJobFile extends JobFile<RadarrJobFile.UpdateInfo> {

    public enum UpdateInfo {
        RADARR_MOVIE_ID,
        RADARR_MOVIE_PATH
    }

    private final EnumMap<UpdateInfo, String> infoMap;

    public RadarrJobFile(File jobFile) throws IncorrectWorkingReferencesException {
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
