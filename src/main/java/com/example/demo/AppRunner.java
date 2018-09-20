package com.example.demo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Autowired private CountService serv;
    
    @Value("${number}")
    private Integer numberIncrement;
    
    private static final int defaultIncrement=1000;

    @Override
    public void run(String... args) throws Exception {
    	if (numberIncrement == null) {
    		logger.info(String.format("Environment numberIncrement null. Using default [%d]", defaultIncrement));
    	} else {
    		logger.info(String.format("Starting stream size [%d]", numberIncrement));
    	}
    	
    	numberIncrement = numberIncrement == null ? defaultIncrement : numberIncrement;
    	
    	logger.info("Running incrementSecurity...");
    	ForkJoinPool pool1 = new ForkJoinPool(8);
		ForkJoinTask<?> td = pool1.submit(()-> IntStream.range(0, numberIncrement).parallel().forEach(serv::incrementSecurity));
		
		logger.info("Running incrementInsecurityParallel...");
		ForkJoinPool pool2 = new ForkJoinPool(8);
		ForkJoinTask<?> td2 = pool2.submit(()->IntStream.range(0, numberIncrement).parallel().forEach(serv::incrementInsecurityParallel));
		
		logger.info("Running incrementInsecurityNotParallel...");
		ForkJoinPool pool3 = new ForkJoinPool(8);
		ForkJoinTask<?> td3 = pool3.submit(()->IntStream.range(0, numberIncrement).forEach(serv::incrementInsecurityNotParallel));
		
    	while(true) {
    		if (td.isDone() && td2.isDone() && td3.isDone()) break;
    		try {
    			Thread.sleep(5000);
    			logger.info("Running test...");
			} catch (Exception e) {
			}
    	}
    }
}