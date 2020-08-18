package cn.ffcs.exception;

import lombok.Data;

/**
 * @author xiezengcheng
 * @date 2020-08-17
 */
@Data
public class BaseException extends RuntimeException {

    private String message;

    public BaseException(String message) {
        this.message = message;
    }

}
