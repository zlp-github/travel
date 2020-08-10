package com.zlp.travel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Author: zlp
 * Date: 2020-08-07 15:46
 * Description:张立朋，写点注释吧!!
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("t_user")
public class User {
    @TableId(value = "id" ,type = IdType.AUTO)
    private Long id;
    @TableField(value = "user_name")
    private String username;
    private String password;
    private String email;
    private Date createTime;
    @TableField(value = "update_time")
    private Date updateTime;

}
