package com.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.exception.ApplicationException;

@Repository("applicationDao")
@EnableTransactionManagement
public class ApplicationDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private transient JdbcTemplate jdbcTemplate;
	
	/**
	 * HibernateTemplate DAO calls
	 */
	public <T extends Object> T read(final Object obj, final Class<T> type) {
		final List<Object> list = hibernateTemplate.findByExample(obj);
		if (list != null) {
			final T object = type.cast(type.cast(list.get(0)));
			return object;
		}
		return null;
	}
	
	public <T extends Object> T find(final Serializable id, final Class<T> type) {
		final T object = type.cast(hibernateTemplate.get(type, id));
		return object;
	}
	
	public void save(final Object obj) {
		hibernateTemplate.save(obj);
	}
	
	public void saveOrUpdate(final Object obj) {
		hibernateTemplate.saveOrUpdate(obj);
	}
	
	public void update(final Object obj) {
		hibernateTemplate.update(obj);
	}
	
	public void delete(final Object obj) {
		hibernateTemplate.delete(obj);
	}
	
	/**
	 * JdbcTemplate DAO calls
	 */
	
	/*
	 * Use the below query for 
	 * INSERT, UPDATE, DELETE
	 */
	public void updateWithPreparedQueryAndIndividualOrderedParams(final String query, final Object... params) {
        jdbcTemplate.update(query, params);
    }
	
	public void updateWithPreparedQueryWithoutParams(final String query) {
        jdbcTemplate.update(query);
    }
	
	/*
	 * Use the below query for
	 * SELECT single record
	 */
	public <T extends Object> T find(final String query, final Object[] params, final Class<T> type) {
		final List<T> list = jdbcTemplate.query(query, params, new BeanPropertyRowMapper<T>(type));
		if (list != null) {
			if (list.isEmpty()) {
				return null;
			}
			if (list.size() > 1) {
				throw new ApplicationException("Single record query returns more than one record <" + query + ">");
			}
			final T object = list.get(0);
			return object;
		}
		return null;
    }
	
	public <T extends Object> T findWithoutParams(final String query, final Class<T> type) {
		final List<T> list = jdbcTemplate.query(query, new BeanPropertyRowMapper<T>(type));
		if (list != null) {
			if (list.isEmpty()) {
				return null;
			}
			if (list.size() > 1) {
				throw new ApplicationException("Single record query returns more than one record <" + query + ">");
			}
			final T object = list.get(0);
			return object;
		}
		return null;
    }
	
	/*
	 * Use the below query for
	 * SELECT multiple records
	 */
	public < T extends Object > List<T> findAll(final String query, final Object[] params, final Class<T> type) {
		return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<T>(type));
    }
	
	public < T extends Object > List<T> findAllWithoutParams(final String query, final Class<T> type) {
		return jdbcTemplate.query(query, new BeanPropertyRowMapper<T>(type));
    }
}