package com.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.constants.ApplicationConstants;
import com.exception.ApplicationException;
import com.utils.LoggerUtils;

@Repository("applicationDao")
@EnableTransactionManagement
public class ApplicationDao implements ApplicationConstants {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private transient NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
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
	public void executeUpdate(final String query, final Map<String, Object> params) {
		LoggerUtils.logOnConsole(query);
		final StringBuilder paramsString =  new StringBuilder(EMPTY_STRING);
		final SqlParameterSource parameters = getSqlParameterSource(params, paramsString);
		LoggerUtils.logOnConsole(paramsString.toString());
		namedParameterJdbcTemplate.update(query, parameters);
    }
	
	public Long insertAndReturnGeneratedKey(final String query, final Map<String, Object> params) {
		LoggerUtils.logOnConsole(query);
		final StringBuilder paramsString =  new StringBuilder(EMPTY_STRING);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final SqlParameterSource parameters = getSqlParameterSource(params, paramsString);
		LoggerUtils.logOnConsole(paramsString.toString());
		namedParameterJdbcTemplate.update(query, parameters, keyHolder);
		if (null != keyHolder.getKey())
			return keyHolder.getKey().longValue();
		return null;
	}
	
	private SqlParameterSource getSqlParameterSource(final Map<String, Object> params, final StringBuilder paramsString) {
		MapSqlParameterSource mapSqlParameterSource = null;
		if (null != params) {
			mapSqlParameterSource = new MapSqlParameterSource();
			for(Map.Entry<String, Object> entry : params.entrySet()) {
				mapSqlParameterSource.addValue(entry.getKey(), entry.getValue());
				paramsString.append(entry.getKey()).append(ASSIGNMENT_OPERATOR).append(entry.getValue()).append(SEMICOLON).append(WHITESPACE);
			}
		}
		if (null == mapSqlParameterSource)
			throw new ApplicationException("Parameters cannot be NULL when using Paramterized Query.");
		return mapSqlParameterSource;
	}
	
	/*
	 * Use the below query for
	 * SELECT single record
	 */
	public <T extends Object> T find(final String query, final Map<String, Object> params, final RowMapper<T> rowmapper) throws DataAccessException, InstantiationException, IllegalAccessException {
		LoggerUtils.logOnConsole(query);
		final StringBuilder paramsString =  new StringBuilder(EMPTY_STRING);
		final SqlParameterSource parameters = getSqlParameterSource(params, paramsString);
		LoggerUtils.logOnConsole(paramsString.toString());
		final List<T> list = namedParameterJdbcTemplate.query(query, parameters, rowmapper);
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
	
	public <T extends Object> T findWithoutParams(final String query, final RowMapper<T> rowmapper) {
		LoggerUtils.logOnConsole(query);
		final List<T> list = namedParameterJdbcTemplate.query(query, rowmapper);
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
	public < T extends Object > List<T> findAll(final String query, final Map<String, Object> params, final RowMapper<T> rowmapper) {
		LoggerUtils.logOnConsole(query);
		final StringBuilder paramsString =  new StringBuilder(EMPTY_STRING);
		final SqlParameterSource parameters = getSqlParameterSource(params, paramsString);
		LoggerUtils.logOnConsole(paramsString.toString());
		return namedParameterJdbcTemplate.query(query, parameters, rowmapper);
    }
	
	public < T extends Object > List<T> findAllWithoutParams(final String query, final RowMapper<T> rowmapper) {
		LoggerUtils.logOnConsole(query);
		return namedParameterJdbcTemplate.query(query, rowmapper);
    }
}