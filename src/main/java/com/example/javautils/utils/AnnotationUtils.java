package com.example.javautils.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.javautils.core.exception.AnnotatioinNotExistException;
import com.example.javautils.core.exception.MultiAnnotationException;

public class AnnotationUtils {
    // give class path and annotation name, return all args in a map
    // throw exception if annotation not exist or multi annotation in this class
    public static Map<String, String> getArgs(Path classPath, String annotationName)
            throws MultiAnnotationException, AnnotatioinNotExistException {
        Map<String, String> resultMap = new HashMap<String, String>();
        // read file
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(classPath.toString()), "UTF-8"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
        // MY_PATTERN equals to BRACKETS_PATTERN
        // Pattern MY_PATTERN = Pattern.compile("\\((.*?)\\)");
        // create pattern
        // BRACKETS_PATTERN: '\' is a special character for java, should add one '\'
        // before '\'
        // '(' is also a escaping parentheses for regular expression, should add one '\'
        // before '\'
        // () for group
        // group[0] for whole matching
        // group[n], n > 0, for things inside nth ()
        Pattern BRACKETS_PATTERN = Pattern.compile("(\\()" + "(.*)" + "(\\))");
        Pattern ANNOTATION_PATTERN = Pattern.compile("@ *" + annotationName);
        // [^\"]: any character but not '"'
        Pattern ARGS_PATTERN = Pattern.compile("([^\" ]*)" + " *= *" + "\"" + "([^\"]*)" + "\"");
        // check by line
        boolean findAnnotation = false;
        String str;
        ArrayList<String> strArr = new ArrayList<String>();
        try {
            while ((str = br.readLine()) != null)
                strArr.add(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < strArr.size(); i++) {
            Matcher bracketsMatcher = BRACKETS_PATTERN.matcher(strArr.get(i));
            Matcher annotationMatcher = ANNOTATION_PATTERN.matcher(strArr.get(i));
            // match brackets amd annotation
            if (annotationMatcher.find()) {
                if (findAnnotation)
                    throw new MultiAnnotationException(
                            "Multi annotation @" + annotationName + " in " + classPath.getFileName().toString());
                findAnnotation = true;
                if (bracketsMatcher.find()) {
                    // remove brackets and put into ARGS_PATTERN
                    Matcher argsMatcher = ARGS_PATTERN.matcher(bracketsMatcher.group(2));
                    while (argsMatcher.find()) {
                        // put into Map<str1, str2>
                        // str1 = annotation name, str2 = corresponding arg
                        resultMap.put(argsMatcher.group(1), argsMatcher.group(2));
                    }
                }
            }
        }
        if (findAnnotation == false) {
            throw new AnnotatioinNotExistException(
                    "No annotation @" + annotationName + " in " + classPath.getFileName().toString());
        }
        return resultMap;
    }

    // give class name and annotation name, return args in a map
    // throw exception if annotation not exist or multi annotation in this class
    public static Map<String, String> getArgs(String className, String annotationName)
            throws AnnotatioinNotExistException, MultiAnnotationException {
        Map<String, String> resultMap = new HashMap<String, String>();
        // search class under current workspace
        File searchUnderThisFile = new File(System.getProperty("user.dir"));
        File serrchFileResult = SearchFileUtils.searchFile(searchUnderThisFile, className + ".java");
        // read file
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(serrchFileResult.toString()), "UTF-8"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
        // MY_PATTERN equals to BRACKETS_PATTERN
        // Pattern MY_PATTERN = Pattern.compile("\\((.*?)\\)");
        // create pattern
        // BRACKETS_PATTERN: '\' is a special character for java, should add one '\'
        // before '\'
        // '(' is also a escaping parentheses for regular expression, should add one '\'
        // before '\'
        // () for group
        // group[0] for whole matching
        // group[n], n > 0, for things inside nth ()
        Pattern BRACKETS_PATTERN = Pattern.compile("(\\()" + "(.*)" + "(\\))");
        Pattern ANNOTATION_PATTERN = Pattern.compile("@ *" + annotationName);
        // [^\"]: any character but not '"'
        Pattern ARGS_PATTERN = Pattern.compile("([^\" ]*)" + " *= *" + "\"" + "([^\"]*)" + "\"");
        // check by line
        boolean findAnnotation = false;
        String str;
        ArrayList<String> strArr = new ArrayList<String>();
        try {
            while ((str = br.readLine()) != null)
                strArr.add(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < strArr.size(); i++) {
            Matcher bracketsMatcher = BRACKETS_PATTERN.matcher(strArr.get(i));
            Matcher annotationMatcher = ANNOTATION_PATTERN.matcher(strArr.get(i));
            // match brackets amd annotation
            if (annotationMatcher.find()) {
                if (findAnnotation)
                    throw new MultiAnnotationException(
                            "Multi annotation @" + annotationName + " in " + className + ".java");
                findAnnotation = true;
                if (bracketsMatcher.find()) {
                    // remove brackets and put into ARGS_PATTERN
                    Matcher argsMatcher = ARGS_PATTERN.matcher(bracketsMatcher.group(2));
                    while (argsMatcher.find()) {
                        // put into Map<str1, str2>
                        // str1 = annotation name, str2 = corresponding arg
                        resultMap.put(argsMatcher.group(1), argsMatcher.group(2));
                    }
                }
            }
        }
        if (findAnnotation == false) {
            throw new AnnotatioinNotExistException("No annotation @" + annotationName + " in " + className + ".java");
        }
        return resultMap;
    }
}
