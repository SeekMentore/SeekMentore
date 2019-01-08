package com.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.QueryMapperConstants;
import com.exception.ApplicationException;
import com.model.query.Namespace;
import com.model.query.Query;
import com.model.query.QueryMapper;
import com.model.query.QueryNamespace;
import com.utils.ApplicationUtils;
import com.utils.ExceptionUtils;
import com.utils.ValidationUtils;

@Service(BeanConstants.BEAN_NAME_QUERY_MAPPER_SERVICE)
public class QueryMapperService implements QueryMapperConstants {
	
	private Map<String, Map<String, Query>> namespaceQueryListMap = new HashMap<String, Map<String, Query>>();
	
	@Autowired
	public JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@PostConstruct
	public void init() throws JAXBException {
		try {
			final QueryNamespace queryNamespace = ApplicationUtils.parseXML(QUERY_NAMESPACE_XML_PATH, QueryNamespace.class);
			if (ValidationUtils.checkObjectAvailability(queryNamespace)) {
				for (final Namespace namespace : queryNamespace.getNamespace()) {
					if (!ValidationUtils.checkStringAvailability(namespace.getName())) {
						throw new ApplicationException("Namespace 'name' cannot pe blank in QueryNamespace");
					}
					if (!ValidationUtils.checkStringAvailability(namespace.getMapperFilePath())) {
						throw new ApplicationException("Namespace 'mapperFilePath' cannot pe blank in QueryNamespace");
					}
					QueryMapper queryMapper;
					try {
						queryMapper = ApplicationUtils.parseXML(namespace.getMapperFilePath(), QueryMapper.class);
						if (ValidationUtils.checkObjectAvailability(queryMapper)) {
							if (!ValidationUtils.checkStringAvailability(queryMapper.getNamespace())) {
								throw new ApplicationException("QueryMapper 'namespace' cannot pe blank @ Filepath = " + namespace.getMapperFilePath());
							}
							if (!namespace.getName().equals(queryMapper.getNamespace())) {
								throw new ApplicationException("QueryMapper 'namespace' mismatch from Namespace 'name' in QueryNamespace @ Filepath = " + namespace.getMapperFilePath());
							}
							final Map<String, Query> queryMap = new HashMap<String, Query>();
							for (final Query query : queryMapper.getQuery()) {
								if (!ValidationUtils.checkStringAvailability(query.getId())) {
									throw new ApplicationException("Query 'id' cannot pe blank @ Filepath = " + namespace.getMapperFilePath());
								}
								if (!ValidationUtils.checkStringAvailability(query.getType())) {
									throw new ApplicationException("Query 'type' cannot pe blank @ Filepath = " + namespace.getMapperFilePath() + "; id = " + query.getId());
								}
								if (!validateQueryType(query.getType())) {
									throw new ApplicationException("Query 'type' is not valid @ Filepath = " + namespace.getMapperFilePath() + "; id = " + query.getId());
								}
								if (ValidationUtils.checkStringAvailability(query.getParamClass())) {
									try {
										Class.forName(query.getParamClass());
									} catch (Exception e) {
										throw new ApplicationException("Query 'paramClass' is not valid @ Filepath = " + namespace.getMapperFilePath() + "; id = " + query.getId() + "; paramClass = " + query.getParamClass());
									}
								}
								if (ValidationUtils.checkObjectAvailability(queryMap.get(query.getId()))) {
									throw new ApplicationException("Cannot defined multiple queries with same 'id'; Namespace = " + namespace.getName() + "; Query Id = " + query.getId());
								}
								queryMap.put(query.getId(), query);
							}
							if (ValidationUtils.checkObjectAvailability(namespaceQueryListMap.get(namespace.getName()))) {
								throw new ApplicationException("Cannot defined multiple mappers with same Namespace name; Namespace = " + namespace.getName());
							}
							namespaceQueryListMap.put(namespace.getName(), queryMap);
						} else {
							throw new ApplicationException("QueryMapper is NULL; Filepath = " + namespace.getMapperFilePath());
						}
					} catch (Exception e) {
						throw new ApplicationException(ExceptionUtils.generateErrorLog(e) + "QueryMapper file is not valid; Filepath = " + namespace.getMapperFilePath());
					}
				}
			} else {
				throw new ApplicationException("QueryNamespace is NULL");
			}
		} catch (Exception e) {
			throw new ApplicationException(ExceptionUtils.generateErrorLog(e) + "QueryNamespace file is not valid");
		}
	}
	
	private Boolean validateQueryType(final String type) {
		switch(type) {
			case "insert":
			case "select":
			case "filter":
			case "sorter":
			case "delete":
			case "update": {
				return true;
			}
		}
		return false;
	}
	
	public String getQuerySQL(final String namespaceName, final String queryId) {
		final String querySQL = namespaceQueryListMap.get(namespaceName).get(queryId).getSQL(jndiAndControlConfigurationLoadService.getControlConfiguration().getDefaultDatabaseEngine());
		if (ValidationUtils.checkStringAvailability(querySQL)) {
			return querySQL;
		}
		throw new ApplicationException("No data found for >> Namespace = " + namespaceName + "; Query Id = " + queryId);
	}
	
	public String getQuerySQL(final String namespaceName, final String queryId, final Map<String, Object> dynamicQueryReplacements) {
		final String querySQL = namespaceQueryListMap.get(namespaceName).get(queryId).getSQL(jndiAndControlConfigurationLoadService.getControlConfiguration().getDefaultDatabaseEngine());
		if (ValidationUtils.checkStringAvailability(querySQL)) {
			
			return querySQL;
		}
		throw new ApplicationException("No data found for >> Namespace = " + namespaceName + "; Query Id = " + queryId);
	}
	
	private String replaceDynamicQueryString(final String querySQL) {
		
	}
	
	public List<Map<String, Object>> getQueryParamsList(final String namespaceName, final String queryId, final List<?> paramObjectList) throws Exception {
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		if (!ValidationUtils.checkNonEmptyList(paramObjectList)) 
			throw new ApplicationException("Param Object List cannot be null/empty");
		final Query query = namespaceQueryListMap.get(namespaceName).get(queryId);
		final Class<?> paramClass = Class.forName(query.getParamClass());
		if (!ValidationUtils.checkStringAvailability(query.getParamClass())) 
			throw new ApplicationException("Cannot fetch param map for Query with no 'paramClass'");
		final List<String> attributeNames = readAllAttributeNamesFromQuery(query.getSQL(jndiAndControlConfigurationLoadService.getControlConfiguration().getDefaultDatabaseEngine()));
		for (final Object paramObject : paramObjectList) {
			if (!ValidationUtils.checkObjectAvailability(paramObject))
				throw new ApplicationException("Param Object cannot be null");
			if (!paramObject.getClass().equals(paramClass)) 
				throw new ApplicationException("Param Object class is different from Query 'paramClass'");
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			if (ValidationUtils.checkNonEmptyList(attributeNames)) {
				for (final String attributeName : attributeNames) {
					paramsMap.put(attributeName, getValueForAttributeNameFromParamObject(paramClass, attributeName, paramObject));
				}
			}
			paramsList.add(paramsMap);
		}
		return paramsList;
	}
	
	public Map<String, Object> getQueryParams(final String namespaceName, final String queryId, final Object paramObject) throws Exception {
		if (!ValidationUtils.checkObjectAvailability(paramObject))
			throw new ApplicationException("Param Object cannot be null");
		final Query query = namespaceQueryListMap.get(namespaceName).get(queryId);
		if (!ValidationUtils.checkStringAvailability(query.getParamClass())) 
			throw new ApplicationException("Cannot fetch param map for Query with no 'paramClass'");
		final Class<?> paramClass = Class.forName(query.getParamClass());
		if (!paramObject.getClass().equals(paramClass)) 
			throw new ApplicationException("Param Object class is different from Query 'paramClass'");
		final List<String> attributeNames = readAllAttributeNamesFromQuery(query.getSQL(jndiAndControlConfigurationLoadService.getControlConfiguration().getDefaultDatabaseEngine()));
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(attributeNames)) {
			for (final String attributeName : attributeNames) {
				paramsMap.put(attributeName, getValueForAttributeNameFromParamObject(paramClass, attributeName, paramObject));
			}
		}
		return paramsMap;
	}
	
	@SuppressWarnings("unused")
	private String getIteratedListQuery(final String querySQL, final Map<String, Object> paramsMap) {
		String query = querySQL;
		int counterForMaxIteratedList = 0; 
		while (query.indexOf("#ITERATE-LIST") != -1) {
			final String queryPart = query.substring(query.indexOf("#ITERATE-LIST") + "#ITERATE-LIST".length() + 2);
			final String attributeName = queryPart.substring(0, getAttributeNameCompletionIndex(queryPart));
			final String toBeRemovedString = "#ITERATE-LIST :" + attributeName;
			final List<?> attributeValue = (List<?>) paramsMap.get(attributeName);
			final List<String> paramHolderCollection = new LinkedList<String>();
			int counter = 0;
			for (final Object object : attributeValue) {
				paramHolderCollection.add(":"+attributeName+"IteratedListIndex"+counter);
				paramsMap.put(attributeName+"IteratedListIndex"+counter, object);
				counter++;
			}
			final String replacedParamHolderString = ValidationUtils.checkNonEmptyList(paramHolderCollection) ? String.join(COMMA, paramHolderCollection) : EMPTY_STRING;
			query = query.replaceAll(toBeRemovedString, replacedParamHolderString);
			if (counterForMaxIteratedList > 500)// this is to avoid infinite loop
				break;
			counterForMaxIteratedList++;
		}
		return query;
	}
	
	private Object getValueForAttributeNameFromParamObject(final Class<?> paramClass, final String attributeName, final Object paramObject) 
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		return paramClass.getMethod(getterMethodNameForAttribute(attributeName)).invoke(paramObject);
	}
	
	private String getterMethodNameForAttribute(final String attributeName) {
		return "get" + String.valueOf(attributeName.charAt(0)).toUpperCase() + (attributeName.length() > 1 ? attributeName.substring(1) : EMPTY_STRING);
	}
	
	private List<String> readAllAttributeNamesFromQuery(final String querySQL) {
		final List<String> attributeNames = new ArrayList<String>();
		String query = querySQL;
		int counterForMaxAttributes = 0; 
		while (query.indexOf(COLON) != -1) {
			final String queryPart = query.substring(query.indexOf(COLON) + 1);
			final String attributeName = queryPart.substring(0, getAttributeNameCompletionIndex(queryPart));
			if (ValidationUtils.checkStringAvailability(attributeName)) {
				attributeNames.add(attributeName.trim());
			}
			query = queryPart;
			if (counterForMaxAttributes > 500)// this is to avoid infinite loop
				break;
			counterForMaxAttributes++;
		}
		return attributeNames;
	}
	
	private int getAttributeNameCompletionIndex(final String queryPart) {
		int indexToSearch = queryPart.length();
		
		int spaceindex = queryPart.indexOf(WHITESPACE);
		if (spaceindex != -1 && indexToSearch > spaceindex)
			indexToSearch = spaceindex;
		
		int commaindex = queryPart.indexOf(COMMA);
		if (commaindex != -1 && indexToSearch > commaindex)
			indexToSearch = commaindex;
		
		int lbracketindex = queryPart.indexOf(LEFT_ROUND_BRACKET);
		if (lbracketindex != -1 && indexToSearch > lbracketindex)
			indexToSearch = lbracketindex;
		
		int rbracketindex = queryPart.indexOf(RIGHT_ROUND_BRACKET);
		if (rbracketindex != -1 && indexToSearch > rbracketindex)
			indexToSearch = rbracketindex;
		
		return indexToSearch;
	}
}
