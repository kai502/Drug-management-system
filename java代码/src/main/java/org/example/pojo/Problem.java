package org.example.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName problem
 */
@TableName(value ="problem")
@Data
public class Problem implements Serializable {
    @TableId
    private Integer pid;

    private Integer did;

    private String article;

    private Date time;

    @Version
    private Integer version;

    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}