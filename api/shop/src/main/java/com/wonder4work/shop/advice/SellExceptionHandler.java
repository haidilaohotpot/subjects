package com.wonder4work.shop.advice;

import com.wonder4work.shop.exception.ResponseBankException;
import com.wonder4work.shop.exception.SellException;
import com.wonder4work.shop.exception.SellerAuthorizeException;
import com.wonder4work.shop.utils.ResultVOUtil;
import com.wonder4work.shop.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class SellExceptionHandler {


    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

}
