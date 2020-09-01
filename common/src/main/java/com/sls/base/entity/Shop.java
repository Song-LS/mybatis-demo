package com.sls.base.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author SLS
 * @data 2019/3/1 13:24
 **/
@Data
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shop_name", columnDefinition = "varchar(20) comment'门店名'")
    private String shopName;

    @Column(name = "shop_no", columnDefinition = "varchar(20) comment'门店编号'")
    private String shopNo;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT)
    private Date updateTime;
}
