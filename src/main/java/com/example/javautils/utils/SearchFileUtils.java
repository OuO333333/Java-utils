package com.example.javautils.utils;

import java.io.File;

// return seachThisFile under searchUnderThisfile folder if file exist
public class SearchFileUtils {
    public static File searchFile(File searchUnderThisfile, String seachThisFile) {
        if (searchUnderThisfile.isDirectory()) {
            File[] fileArray = searchUnderThisfile.listFiles();
            for (File fileItem : fileArray) {
                File found = searchFile(fileItem, seachThisFile);
                if (found != null) {
                    return found;
                }
            }
        } else {
            if (searchUnderThisfile.getName().compareTo(seachThisFile) == 0) {
                return searchUnderThisfile;
            }
        }
        return null;
    }

}
