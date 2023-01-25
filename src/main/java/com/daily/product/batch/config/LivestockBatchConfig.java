package com.daily.product.batch.config;

import com.daily.product.batch.domain.Livestock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManagerFactory;

@Slf4j
@Configuration
public class LivestockBatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    public LivestockBatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, EntityManagerFactory entityManagerFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    // exampleJob 생성
    @Bean
    public Job exampleJob() throws Exception {
        return jobBuilderFactory.get("exampleJob")
            .start(exampleStep()).build();
    }

    // exampleStep 생성
    @Bean
    @JobScope
    public Step exampleStep() throws Exception {
        return stepBuilderFactory.get("exampleStep")
            .<Livestock, Livestock> chunk(10)
            .reader(reader(null))
            .processor(processor(null))
            .writer(writer(null))
            .build();
    }

    @Bean
    @StepScope
    public ItemReader<? extends Livestock> reader(@Value("#{jobParameters[requestDate]}") String requestDate) throws Exception {
        log.info("==> 111 reader value : " + requestDate);

//        Map<String, Object> parameterValues = new HashMap<>();
//        parameterValues.put("price", 1000);

        return new JpaPagingItemReaderBuilder<Livestock>()
            .name("JpaPagingItemReader")
            .entityManagerFactory(entityManagerFactory)
            .queryString("select id from livestock where id = 22")
            .pageSize(10)
            //.parameterValues(parameterValues)
            .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<? super Livestock, ? extends Livestock> processor(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return new ItemProcessor <Livestock, Livestock> () {
            @Override
            public Livestock process(Livestock livestock) throws Exception {
                log.info("==> processor Livestock : " + livestock);
                log.info("==> processor value : " + requestDate);

                // 100원 추가
                //market.setPrice(market.getPrice() + 100);
                return livestock;
            }
        };
    }

    @Bean
    @StepScope
    public ItemWriter<? super Livestock> writer(@Value("#{jobParameters[requestDate]}") String requestDate) {
        log.info("==> writer value : " + requestDate);
        return new JpaItemWriterBuilder<Livestock>()
            .entityManagerFactory(entityManagerFactory)
            .build();
    }
}
