package com.wonder4work.shop.config;

import com.wonder4work.shop.service.ProductSellDailyService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MethodInvokingJobDetailFactoryBean jobDetailFactory;

    @Autowired
    private CronTriggerFactoryBean productSellDailyTriggerFactory;


    @Bean(name = "jobDetailFacotry")
    public MethodInvokingJobDetailFactoryBean jobDetailFactory() {


        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();

        jobDetailFactoryBean.setName("product_sell_daily_job");

        jobDetailFactoryBean.setGroup("job_product_sell_daily_group");

        jobDetailFactoryBean.setConcurrent(false);

        jobDetailFactoryBean.setTargetObject(productSellDailyService);

        jobDetailFactoryBean.setTargetMethod("dailyCalculate");

        return jobDetailFactoryBean;

    }


    @Bean(name = "productSellDailyTriggerFactory")
    public CronTriggerFactoryBean productSellDailyTriggerFactory() {

        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();

        triggerFactoryBean.setBeanName("product_sell_daily_trigger");

        triggerFactoryBean.setGroup("job_product_sell_daily_group");

        triggerFactoryBean.setJobDetail(jobDetailFactory.getObject());
        //每天1点执行一次
        triggerFactoryBean.setCronExpression("0 0 23 * * ? *");
        return triggerFactoryBean;
    }


    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactory() {

        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(productSellDailyTriggerFactory.getObject());
        return schedulerFactoryBean;

    }


}
