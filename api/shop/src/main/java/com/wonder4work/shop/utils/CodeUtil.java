package com.wonder4work.shop.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiezengcheng
 * @date 2020-09-08
 */
public class CodeUtil {

    /*
     * 校验验证码
     * */
    public static boolean checkVerifyCode(HttpServletRequest request,String verifyCodeActual) {
        String verifyCodeExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (null == verifyCodeActual
                || !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)) {
            return false;
        }
        return true;
    }

}
