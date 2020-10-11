package com.wonder4work.shop.config;

import com.wonder4work.shop.controller.seller.SellerOrderController;
import com.wonder4work.shop.service.OrderService;
import com.wonder4work.shop.service.ProductSellDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/*
 * 定时框架
 * */
@Configuration
public class QuartzConfiguration {


    @Autowired
    private ProductSellDailyService productSellDailyService;

    @Autowired
    private OrderService orderService;

    @Qualifier("jobDetailFacotryFirst")
    @Autowired
    private MethodInvokingJobDetailFactoryBean jobDetailFacotryFirst;

    @Qualifier("productSellDailyTriggerFactoryFirst")
    @Autowired
    private CronTriggerFactoryBean productSellDailyTriggerFactoryFirst;

    @Qualifier("jobDetailFacotrySecond")
    @Autowired
    private MethodInvokingJobDetailFactoryBean jobDetailFacotrySecond;

    @Qualifier("orderDailyTriggerFactorySecond")
    @Autowired
    private CronTriggerFactoryBean orderDailyTriggerFactorySecond;


    @Bean(name = "jobDetailFacotryFirst")
    public MethodInvokingJobDetailFactoryBean jobDetailFacotryFirst() {


        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();

        jobDetailFactoryBean.setName("product_sell_daily_job");

        jobDetailFactoryBean.setGroup("job_product_sell_daily_group");

        jobDetailFactoryBean.setConcurrent(false);


        jobDetailFactoryBean.setTargetObject(productSellDailyService);

        jobDetailFactoryBean.setTargetMethod("dailyCalculate");

        return jobDetailFactoryBean;

    }


    @Bean(name = "productSellDailyTriggerFactoryFirst")
    public CronTriggerFactoryBean productSellDailyTriggerFactoryFirst() {

        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();

        triggerFactoryBean.setBeanName("product_sell_daily_trigger");

        triggerFactoryBean.setGroup("job_product_sell_daily_group");

        triggerFactoryBean.setJobDetail(jobDetailFacotryFirst.getObject());
        //每天1点执行一次
        triggerFactoryBean.setCronExpression("0 0 23 * * ? *");
        return triggerFactoryBean;
    }


    @Bean(name = "jobDetailFacotrySecond")
    public MethodInvokingJobDetailFactoryBean jobDetailFacotrySecond() {


        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();

        jobDetailFactoryBean.setName("order_daily_job");

        jobDetailFactoryBean.setGroup("job_order_daily_group");

        jobDetailFactoryBean.setConcurrent(false);


        jobDetailFactoryBean.setTargetObject(orderService);

        jobDetailFactoryBean.setTargetMethod("finishOrderAuto");

        return jobDetailFactoryBean;

    }


    @Bean(name = "orderDailyTriggerFactorySecond")
    public CronTriggerFactoryBean orderDailyTriggerFactorySecond() {

        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();

        triggerFactoryBean.setBeanName("order_daily_trigger");

        triggerFactoryBean.setGroup("job_order_daily_group");

        triggerFactoryBean.setJobDetail(jobDetailFacotrySecond.getObject());
        //每天1点执行一次
        triggerFactoryBean.setCronExpression("0 0 22 * * ? *");
        return triggerFactoryBean;
    }


    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactory() {

        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(productSellDailyTriggerFactoryFirst.getObject()
                ,orderDailyTriggerFactorySecond.getObject());
        return schedulerFactoryBean;

    }


}
