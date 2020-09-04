package com.wonder4work.kafka.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author xiezengcheng
 * @date 2020-08-25
 */
@Entity
@Table(name = "log")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Log {

    @Id
    @GeneratedValue(generator = "idGenerator",strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "status")
    private String status;

    @Column(name = "msg")
    private String msg;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private Date updateTime;


}
