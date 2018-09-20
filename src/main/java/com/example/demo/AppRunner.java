package com.example.demo;

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
    	
    	
    	IntStream.range(0, numberIncrement).parallel().forEach(serv::incrementSecurity);
    	
    	IntStream.range(0, numberIncrement).parallel().forEach(serv::incrementInsecurityParallel);
    	
    	IntStream.range(0, numberIncrement).forEach(serv::incrementInsecurityNotParallel);
    }
}