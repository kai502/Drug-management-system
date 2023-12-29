package org.example.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName supplier
 */
@TableName(value ="supplier")
@Data
public class Supplier implements Serializable {
    @TableId
    private Integer sid;

    private String sname;

    private Long phoneNumber;

    @Version
    private Integer version;

    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}