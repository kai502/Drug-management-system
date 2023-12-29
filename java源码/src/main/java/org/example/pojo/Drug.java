package org.example.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName drug
 */
@TableName(value ="drug")
@Data
public class Drug implements Serializable {

    @TableId
    private Integer did;

    private Integer sid;

    private String dname;

    private String type;

    private String size;

    private Integer number;

    private Integer price;

    private Date valid;

    private String details;

    @Version
    private Integer version;

    @TableLogic
    private Integer isDeleted;
}