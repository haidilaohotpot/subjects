package com.wonder4work.epsm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author wonder4work
 * @since 2020-08-12
 */
@Data
public class UserBO {

    private Integer userId;

    @NotBlank(message = "用户名不能为空")
    @Length(max = 12, message = "用户名不能超过12位")
    private String name;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$", message = "手机号格式不正确")
    private String phone;

    @NotBlank(message = "登录名不能为空")
    @Length(max = 12, message = "登录名不能超过12位")
    private String loginName;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[1-9])(?=.*[\\W]).{8,}$", message = "密码格式不正确")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Min(value = 0, message = "账户类型选择不正确")
    @Max(value = 1, message = "账户类型选择不正确")
    private Integer accountType;

    @Min(value = 0, message = "用户类型选择不正确")
    @Max(value = 2, message = "用户类型选择不正确")
    private Integer userType;


}
