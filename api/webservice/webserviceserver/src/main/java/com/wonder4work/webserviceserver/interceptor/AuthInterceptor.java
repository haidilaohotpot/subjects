package com.wonder4work.webserviceserver.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.phase.PhaseInterceptor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author xiezengcheng
 * @date 2020-10-10
 */
@Slf4j
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {


    private String username ;

    private String password;


    public AuthInterceptor(String username,String password) {
        super(Phase.PRE_PROTOCOL);
        this.username=username;
        this.password=password;

    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {

        log.info("do something...");
    }
}
