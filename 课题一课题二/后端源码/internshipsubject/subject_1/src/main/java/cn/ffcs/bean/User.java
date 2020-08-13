package cn.ffcs.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sun.xml.internal.txw2.annotation.XmlElement;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xiezengcheng
 * @date 2020-08-06
 */
@Data
@XmlRootElement
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private String name;

    private Integer sex;

    private Integer age;

    private Integer position;



}
