package com.dl.officialsite.hiring;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "hiring")
@EntityListeners(AuditingEntityListener.class)
public class Hiring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 岗位名称
     */
    private String position;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String  location;

    private String  email;

    private String company;

    /**
     * 工作年限
     */
    private String yearlySalary;

    /**
     * 岗位优势
     */
    private String benefits;

    private String twitter;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 创建岗位人地址
     */
    private String address;

    /**
     * 0:jd 招聘中
     * 1:jd 删除
     * 2:jd 过期
     * 设置默认值,默认值为招聘中
     */
    private int status = 0;

}
