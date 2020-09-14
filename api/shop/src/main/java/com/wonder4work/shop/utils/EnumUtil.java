package com.wonder4work.shop.utils;


import com.wonder4work.shop.enums.CodeEnum;

/**
 * 枚举工具类
 * @author xiezengcheng
 */

public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
