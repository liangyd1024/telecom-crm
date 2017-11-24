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
public enum FileTypeEnum {

    DEVICE_ID_NUMBER("DEVICE_ID_NUMBER","设备证件号文件"),

    DEVICE_ICCID_NUMBER("DEVICE_ICCID_NUMBER","设备ICCID号文件"),

    ;

    private String type;

    private String desc;

}
