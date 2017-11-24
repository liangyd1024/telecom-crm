package com.lyd.telecom.model.req;

import lombok.Data;

import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/11/24 ProjectName:crm Version: 1.0
 */
@Data
public class FileReqDto {

    /**
     * 文件类型
     * @see com.lyd.telecom.enums.FileTypeEnum
     */
    private String type;

    /**
     * 文件大小
     */
    private long size;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件后缀名
     */
    private String suffixName;

    /**
     * 上传前路径
     */
    private String uploadBeforePath;

    /**
     * 本地存储路径
     */
    private String localPath;

    /**
     * 文件内容行数
     */
    private Integer line;

    /**
     * 状态
     * @see com.lyd.telecom.enums.FileStatusEnum
     */
    private String status;

    /**
     * 文件行内容
     */
    private List<String> lineList;
}
