package com.lyd.telecom.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/11/24 ProjectName:crm Version: 1.0
 */
@AllArgsConstructor
@Getter
public enum FileStatusEnum {

    UPLOAD_SUCCESS("UPLOAD_SUCCESS","上传成功"),

    PROCESS_ING("PROCESS_ING","处理中"),

    PROCESS_SUCCESS("PROCESS_SUCCESS","处理成功"),

    DOWNLOAD_SUCCESS("DOWNLOAD_SUCCESS","下载成功"),

    ;

    private String status;

    private String desc;

}
