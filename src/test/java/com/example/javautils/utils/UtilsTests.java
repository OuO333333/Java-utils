package com.example.javautils.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.javautils.core.exception.AnnotatioinNotExistException;
import com.example.javautils.core.exception.MultiAnnotationException;
import com.example.javautils.model.ConnectionInf;

public class UtilsTests {
    @Test
    void contextLoads() {
        System.out.println("test contextLoads");
    }

    @Test
    public void getFieldNames() {
        ArrayList<String> resultArr = com.example.javautils.utils.FieldNamesUtils.getFieldNames(ConnectionInf.class, "Indexed");
        System.out.println(resultArr);
    }

    @Test
	public void getArgs1() {
		File searchUnderThisFile = new File("D:/timgit/Java-utils");
		File serrchFileResult = SearchFileUtils.searchFile(searchUnderThisFile, "ConnectionInf.java");
		Path path = Paths.get(serrchFileResult.toString());
		try {
			Map<String, String> resultMap = AnnotationUtils.getArgs(path, "Document");
			System.out.println(resultMap);
		} catch (MultiAnnotationException | AnnotatioinNotExistException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getArgs2() throws IOException {
		try {
			Map<String, String> resultMap = AnnotationUtils.getArgs("ConnectionInf", "Document");
			System.out.println(resultMap);
		} catch (AnnotatioinNotExistException | MultiAnnotationException e) {
			e.printStackTrace();
		}
	}

}
