package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CountService {
	private JdbcTemplate jdbcTemplate;

	public CountService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Transactional
	public void incrementSecurity(Integer ... i) {
		int current = jdbcTemplate.queryForObject("select valor from tb_count_security limit 1", Integer.class);

		int j = jdbcTemplate.update("update tb_count_security set valor=valor+1");

		int newValue = jdbcTemplate.queryForObject("select valor from tb_count_security limit 1", Integer.class);
	}
	
	@Transactional
	public void incrementInsecurityParallel(Integer ... i) {
		int current = jdbcTemplate.queryForObject("select valor from tb_count_insecurity_parallel limit 1", Integer.class);
		
		int j = jdbcTemplate.update("update tb_count_insecurity_parallel set valor=?", ++current);
		
		int newValue = jdbcTemplate.queryForObject("select valor from tb_count_insecurity_parallel limit 1", Integer.class);
		
	}
	@Transactional
	public void incrementInsecurityNotParallel(Integer ... i) {
		int current = jdbcTemplate.queryForObject("select valor from tb_count_insecurity_notparallel limit 1", Integer.class);
		
		int j = jdbcTemplate.update("update tb_count_insecurity_notparallel set valor=?", ++current);
		
		int newValue = jdbcTemplate.queryForObject("select valor from tb_count_insecurity_notparallel limit 1", Integer.class);
		
	}
}
