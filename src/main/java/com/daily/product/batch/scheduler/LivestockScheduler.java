package com.daily.product.batch.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableScheduling
@Component
public class LivestockScheduler {
    private final Job job;
    private final JobLauncher jobLauncher;

    public LivestockScheduler(Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

    @Scheduled(fixedDelay = 30000)
    public void startJob() {
        try {
            Map<String, JobParameter> jobParametersMap = new HashMap<>();

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date time = new Date();

            String time1 = format1.format(time);

            jobParametersMap.put("requestDate", new JobParameter(time1));

            JobParameters parameters = new JobParameters(jobParametersMap);

            JobExecution jobExecution = jobLauncher.run(job, parameters);

            while (jobExecution.isRunning()) {
                log.info("isRunning...");
            }

        } catch (JobExecutionAlreadyRunningException | JobRestartException |
                 JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
