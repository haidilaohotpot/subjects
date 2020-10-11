package com.wonder4work.webserviceserver.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.message.Message;

/**
 * @author xiezengcheng
 * @date 2020-10-10
 */
public class LimitInterceptor extends LoggingInInterceptor {


    @Override
    public void handleMessage(Message message) throws Fault {
        super.handleMessage(message);
    }
}
