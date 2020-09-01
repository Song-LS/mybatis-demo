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
@Data // 该注解会提供getter、setter、equals、canEqual、hashCode、toString方法。
@Entity // 标注这是一个实体类
@Table(name = "product", indexes = {@Index(name = "shop_index", columnList = "shop_id")}) // 指定数据库对应的表
public class Product {

    @Id // 说明是主键（@Id此注解不能省略，不然Hibernate在调用po进行ORM操作的时候，会出现错误，千万不要认为主键不是自增，就不用指定主键了）
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键的生成方式
    private Long id;

    @Column(name = "shop_id", columnDefinition = "bigint comment'门店Id'") // 据库字段和类属性对应关系
    private Long shopId;

    @Column(name = "product_name", columnDefinition = "varchar(30) comment'产品名'")
    private String productName;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT)
    private Date updateTime;
}
