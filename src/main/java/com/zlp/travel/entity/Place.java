package com.zlp.travel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Author: zlp
 * Date: 2020-08-08 19:25
 * Description:张立朋，写点注释吧!!
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("t_place")
public class Place {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String name;
    private String picpath;
    @JsonFormat(pattern = "yyy/MM/dd")
    private Date hottime;
    private Double hotticket;
    private Double dimticket;
    private String placedes;
    @TableField(value = "province_id")
    private Long provinceId;
}
