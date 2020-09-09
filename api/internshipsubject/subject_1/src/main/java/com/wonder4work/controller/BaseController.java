package com.wonder4work.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiezengcheng
 * @date 2020-08-17
 */
public class BaseController {


    /**
     * 获取数据校验中的错误信息
     * @param result
     * @return
     */
    public Map<String, Object> getErrorsMap(BindingResult result) {

        List<FieldError> errorList = result.getFieldErrors();
        Map<String, Object> map = new HashMap<>();
        for (FieldError fieldError : errorList) {
            // 某一个属性
            String field = fieldError.getField();
            // 验证错误的信息
            String defaultMessage = fieldError.getDefaultMessage();

            map.put(field, defaultMessage);
        }
        return map;
    }


    /**
     * 获取数据校验中的错误信息
     * @param result
     * @return
     */
    public String getErrorsString(BindingResult result) {

        List<FieldError> errorList = result.getFieldErrors();
        StringBuffer sb = new StringBuffer();
        for (FieldError fieldError : errorList) {
            // 某一个属性
            String field = fieldError.getField();
            // 验证错误的信息
            String defaultMessage = fieldError.getDefaultMessage();

            sb.append(defaultMessage);
            sb.append(";");
        }
        return sb.toString();
    }

}
