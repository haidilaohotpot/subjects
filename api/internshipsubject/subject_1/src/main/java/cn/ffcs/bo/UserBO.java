package cn.ffcs.bo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @author xiezengcheng
 * @date 2020-08-17
 */
@Data
public class UserBO {

    private Integer id;

    @NotBlank(message = "员工代码不能为空")
    private String code;

    @NotBlank(message = "用户名不能为空")
    private String name;

    @Min(value = 0, message = "性别选择不正确")
    @Max(value = 1, message = "性别选择不正确")
    private Integer sex;

    private Integer age;

    @Min(value = 1, message = "职位选择不正确")
    @Max(value = 2, message = "职位选择不正确")
    private Integer position;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }


}
