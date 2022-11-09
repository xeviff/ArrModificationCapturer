package tv.mangrana.content;

import tv.mangrana.exception.IncorrectWorkingReferencesException;

import java.io.File;

public interface UpdateHandler {
    void handle(File file) throws IncorrectWorkingReferencesException;
}
