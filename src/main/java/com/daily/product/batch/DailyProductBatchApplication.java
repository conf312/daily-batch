package com.daily.product.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class DailyProductBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyProductBatchApplication.class, args);
	}

}
