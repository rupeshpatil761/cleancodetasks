package com.epam.engx.cleancode.naming.task5;

import com.epam.engx.cleancode.naming.task5.predicates.CheckFileTypePredicate;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidDirectoryException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidFileTypeException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.PropertyUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class FileManager {

    private static final String[] IMAGE_TYPES = {"jpg", "png"};
    private static final String[] DOCUMENT_TYPES = {"pdf", "doc"};

    private String basePath = PropertyUtil.loadProperty("basePath");

    public File retrieveFile(String fileName) {
        validateFileType(fileName);
        final String dirPath = basePath + File.separator;
        return Paths.get(dirPath, fileName).toFile();
    }

    public List<String> listAllImages() {
        return getFilesFromDirectoryPathWithAllowedTypes(basePath, IMAGE_TYPES);
    }

    public List<String> listAllDocumentFiles() {
        return getFilesFromDirectoryPathWithAllowedTypes(basePath, DOCUMENT_TYPES);
    }

    private void validateFileType(String fileName) {
        if (isInvalidFileType(fileName)) {
            throw new InvalidFileTypeException("File type not Supported: " + fileName);
        }
    }

    private boolean isInvalidFileType(String fileName) {
        return isInvalidImage(fileName) && isInvalidDocument(fileName);
    }

    private boolean isInvalidImage(String fileName) {
        CheckFileTypePredicate fileTypePredicate = new CheckFileTypePredicate(IMAGE_TYPES);
        return !fileTypePredicate.test(fileName);
    }

    private boolean isInvalidDocument(String fileName) {
        CheckFileTypePredicate fileTypePredicate = new CheckFileTypePredicate(DOCUMENT_TYPES);
        return !fileTypePredicate.test(fileName);
    }

    private List<String> getFilesFromDirectoryPathWithAllowedTypes(String directoryPath, String[] allowedFileTypes) {
        final CheckFileTypePredicate pred = new CheckFileTypePredicate(allowedFileTypes);
        return Arrays.asList(getDirectoryFromPath(directoryPath).list(fileNameFilter(pred)));
    }

    private FilenameFilter fileNameFilter(final CheckFileTypePredicate predicate) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String str) {
                return predicate.test(str);
            }
        };
    }

    private File getDirectoryFromPath(String directoryPath) {
        File directory = new File(directoryPath);
        validateDirectory(directory);
        return directory;
    }

    private void validateDirectory(File directoryInstance) {
        if (isNotDirectory(directoryInstance)) {
            throw new InvalidDirectoryException("Invalid directory found: " + directoryInstance.getAbsolutePath());
        }
    }

    private boolean isNotDirectory(File file) {
        return !file.isDirectory();
    }

}