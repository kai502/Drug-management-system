package org.example.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName receipt
 */
@TableName(value ="receipt")
@Data
public class Receipt implements Serializable {
    @TableId
    private Integer rid;

    private Integer did;

    private Integer type;

    private Integer number;

    private String administrator;

    private Date time;

    @Version
    private Integer version;

    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}