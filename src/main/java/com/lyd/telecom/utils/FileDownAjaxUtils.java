package com.lyd.telecom.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * UserReqDto: lyd Date: 2017/11/24 ProjectName:crm Version: 1.0
 */
@Slf4j
public class FileDownAjaxUtils {


    public static File touch(String path,String fileName) throws IOException {
        File tempFile = new File(path + fileName);
        FileUtils.touch(tempFile);
        return tempFile;
    }

    private static HttpHeaders getHttpHeaders(File file) throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        headers.set("x-filename", URLEncoder.encode(file.getName(), "UTF-8"));
        return headers;
    }

    public static ResponseEntity<byte[]> getResponseEntity(File file) throws IOException {
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), getHttpHeaders(file), HttpStatus.OK);
    }

    public static ResponseEntity<byte[]> getResponseEntityFail() {
        return new ResponseEntity<byte[]>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    public static ResponseEntity<byte[]> getResponseEntityNotFound() {
        return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
    }

    public static void forceDelete(File file) throws IOException {
        FileUtils.forceDelete(file);

    }
}