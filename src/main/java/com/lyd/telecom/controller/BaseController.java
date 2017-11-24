package com.lyd.telecom.controller;

import com.lyd.telecom.cache.SessionCache;
import com.lyd.telecom.enums.FileStatusEnum;
import com.lyd.telecom.model.req.FileReqDto;
import com.lyd.telecom.model.resp.CertNoRespDto;
import com.lyd.telecom.utils.FileDownAjaxUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by aaa on 2017/11/22.
 */
@Controller
@Slf4j
public class BaseController {

    public static String SMS_CODE;

    @RequestMapping("/welcome")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("name", 123);
        // return模板文件的名称，对应src/main/resources/templates/welcome.html
        return "welcome";
    }


    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(MultipartHttpServletRequest request,
                             HttpSession httpSession){
        try {
            Map<String,MultipartFile> multipartFileMap = request.getFileMap();
            log.info("call uploadFile multipartFileMap:{}",multipartFileMap);

            if(SessionCache.UPLOAD_FILE_MAP.containsKey(httpSession.getId())){
                log.info("call uploadFile processing...");
                return "processing";
            }

            for (Map.Entry<String,MultipartFile> entry :multipartFileMap.entrySet()){
                FileReqDto fileReqDto = new FileReqDto();

                File tempFile = File.createTempFile("temp",null);
                entry.getValue().transferTo(tempFile);

                FileReader fileReader = new FileReader(tempFile);
                BufferedReader bufferedReader = new BufferedReader (fileReader);
                String result;
                int line = 0;
                List<String> lineList = new ArrayList<>();
                while (null != (result = bufferedReader.readLine()) ){
                    if(!StringUtils.isEmpty(result)){
                        line++;
                        lineList.add(result);
                        log.info("line:{}",result);
                    }
                }
                fileReqDto.setLine(line);
                fileReqDto.setSize(entry.getValue().getSize());
                String originalFilename = entry.getValue().getOriginalFilename();
                int index = originalFilename.indexOf(".");
                fileReqDto.setName(originalFilename.substring(0,index));
                fileReqDto.setSuffixName(originalFilename.substring(index,originalFilename.length()));
                fileReqDto.setUploadBeforePath(tempFile.getPath());
                fileReqDto.setStatus(FileStatusEnum.UPLOAD_SUCCESS.getStatus());
                fileReqDto.setLineList(lineList);

                SessionCache.UPLOAD_FILE_MAP.put(httpSession.getId(),fileReqDto);

                new FileThread(httpSession.getId()).start();

                tempFile.deleteOnExit();
            }

        } catch (Exception e) {
            log.error("call uploadFile Exception:{}",e);
            return "fail";
        }

        return "success";
    }


    @ResponseBody
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpSession httpSession, String type){
        log.info("call download CERT_NO_FILE_RESULT_MAP:{},type:{}",SessionCache.CERT_NO_FILE_RESULT_MAP,type);
        ResponseEntity<byte[]> responseEntity;
        FileWriter fileWriter = null;
        try{
            CertNoRespDto certNoRespDto = SessionCache.CERT_NO_FILE_RESULT_MAP.get(httpSession.getId());
            if(null != certNoRespDto){
                File tempFile = File.createTempFile("download",
                        ".txt");

                fileWriter = new FileWriter(tempFile);
                for(String line : certNoRespDto.getCertNoList()){
                    log.info("call download line:{}",line);
                    fileWriter.write(line);
                    fileWriter.write("\r\n");
                }
                fileWriter.flush();

                responseEntity = FileDownAjaxUtils.getResponseEntity(tempFile);
                tempFile.deleteOnExit();
                SessionCache.CERT_NO_FILE_RESULT_MAP.remove(httpSession.getId());
                SessionCache.UPLOAD_FILE_MAP.remove(httpSession.getId());
            }else{
                responseEntity = FileDownAjaxUtils.getResponseEntityNotFound();
            }
        }catch (Exception e){
            log.error("call download Exception:{}",e);
            responseEntity = FileDownAjaxUtils.getResponseEntityFail();
        }finally {
            try {
                if(null != fileWriter){
                    fileWriter.close();
                }
            } catch (IOException e) {
                log.error("call download Exception:{}",e);
            }
        }
        return responseEntity;
    }


    @AllArgsConstructor
    public class FileThread extends Thread{

        private String sessionId;

        @Override
        public void run() {
            FileReqDto fileReqDto = SessionCache.UPLOAD_FILE_MAP.get(sessionId);

            List<String> respLineList = new ArrayList<>();

            for(String line : fileReqDto.getLineList()){
                respLineList.add(line+"|"+1234567890);
            }

            CertNoRespDto certNoRespDto = new CertNoRespDto();
            certNoRespDto.setFileName(fileReqDto.getName());
            certNoRespDto.setCertNoList(respLineList);
            SessionCache.CERT_NO_FILE_RESULT_MAP.put(sessionId,certNoRespDto);

            log.info("call run UPLOAD_FILE_MAP:{},CERT_NO_FILE_RESULT_MAP:{}",SessionCache.UPLOAD_FILE_MAP,SessionCache.CERT_NO_FILE_RESULT_MAP);
        }
    }

}
