package com.tengol.spring.boot.schedule.task;

import com.tengol.spring.boot.schedule.dao.TbCronMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * DynamicScheduleTask
 *
 * @author dongrui
 * @date 2019/11/18 17:52
 */
@Slf4j
@ComponentScan(basePackages = "com.tengol.spring.boot.schedule")
//@Configuration
public class DynamicScheduleTask implements SchedulingConfigurer {
    private static final String CRON_ID = "cron_1";

    private TbCronMapper tbCronMapper;

    public DynamicScheduleTask(TbCronMapper tbCronMapper) {
        this.tbCronMapper = tbCronMapper;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(this::printInfo,
                triggerContext -> {
                    String cron = tbCronMapper.getCron(CRON_ID);
                    if (StringUtils.isEmpty(cron)) {
                        log.error("cron express is null");
                        throw new RuntimeException("cron express is null");
                    }
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                });
    }

    private void printInfo() {
        log.info("Hello world on {}", System.currentTimeMillis());
    }
}
