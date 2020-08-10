package com.zlp.travel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Author: zlp
 * Date: 2020-08-08 19:25
 * Description:张立朋，写点注释吧!!
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("t_province")
public class Province {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String name;
    private String tags;
    private Integer placecounts;
}
