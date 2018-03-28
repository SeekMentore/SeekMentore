package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.MenuConstants;
import com.exception.ApplicationException;
import com.model.menu.Menu;
import com.model.menu.Page;
import com.model.menu.PageList;
import com.model.menu.Webservice;
import com.utils.AdditionalAccessFunctionUtils;
import com.utils.ApplicationUtils;

@Service(BeanConstants.BEAN_NAME_MENU_SERVICE)
public class MenuService implements MenuConstants {
	
	private Menu applicationMenu;
	private Map<String, MenuAttributes> menuUrlToPageAccessTypeMap;
	
	class MenuAttributes {
		private String pageAccessType;
		private String additionalAccessFunction;
		
		MenuAttributes(String pageAccessType, String additionalAccessFunction) {
			this.pageAccessType = pageAccessType;
			this.additionalAccessFunction = additionalAccessFunction;
		}
		
		public boolean hasAccessToURL(final String empId, final List<String> providedAccessRoles, final Map<String, Object> extraParams) {
			return PAGE_UNSECURED.equals(this.pageAccessType) 
					|| (checkPageAccessTypeWithProvidedRoles(providedAccessRoles) && checkAdditionalAccess(empId, extraParams));
		}
		
		private boolean checkAdditionalAccess(final String empId, final Map<String, Object> extraParams) {
			if (null == this.additionalAccessFunction || EMPTY_STRING.equals(this.additionalAccessFunction.trim()))
				return true;
			if (this.additionalAccessFunction.equals("saveAccessForForm11"))
				return AdditionalAccessFunctionUtils.saveAccessForForm11(empId, extraParams);
			if (this.additionalAccessFunction.equals("saveAccessForFormF"))
				return AdditionalAccessFunctionUtils.saveAccessForForm2(empId, extraParams);
			if (this.additionalAccessFunction.equals("saveAccessForForm2"))
				return AdditionalAccessFunctionUtils.saveAccessForFormF(empId, extraParams);
			throw new ApplicationException(EXCEPTION_UNDEFINED_ADDITIONAL_ACCESS_FUNCTION);
		}
		
		private boolean checkPageAccessTypeWithProvidedRoles(final List<String> providedAccessRoles) {
			final String[] pageAccessTypeList = this.pageAccessType.split(COMMA);
			for (final String pageAccessType : pageAccessTypeList) {
				for (final String role : providedAccessRoles) {
					if (role.equals(pageAccessType)) {
						return true;
					}
				}
			}
			return false;
		}
	}
	
	@PostConstruct
	public void parseMenuFromXML()  throws JAXBException {
		this.applicationMenu = ApplicationUtils.parseXML(MENU_XML_PATH, Menu.class);
		this.menuUrlToPageAccessTypeMap = new HashMap<String, MenuAttributes>();
		createMenuUrlToPageAccessTypeMap();
	}
	
	private void createMenuUrlToPageAccessTypeMap() {
		for (final PageList pageList : applicationMenu.getPageList()) {
			if (null != pageList.getPage() && !pageList.getPage().isEmpty()) {
				for (final Page page : pageList.getPage()) {
					final String pageAccessType = PAGE_SECURED.equals(pageList.getId()) ? page.getPageAccessType() : PAGE_UNSECURED;
					if (null == page.getUrl()) 
						throw new ApplicationException(EXCEPTION_PAGE_DOES_NOT_HAVE_A_VALID_URL);
					menuUrlToPageAccessTypeMap.put(page.getUrl().toUpperCase(), new MenuAttributes(pageAccessType, null));
					if (null != page.getWebservice() && !page.getWebservice().isEmpty()) {
						for (final Webservice webservice : page.getWebservice()) {
							final String url = page.getUrl() + webservice.getUrl();
							menuUrlToPageAccessTypeMap.put(url.toUpperCase(), new MenuAttributes(pageAccessType, webservice.getAdditionalAccessFunction()));
						}
					}
				}
			}
		}
	}

	public Menu getApplicationMenu() {
		return applicationMenu;
	}
	
	public boolean hasAccessToURL(final String empId, final String url, final List<String> providedAccessRoles, final Map<String, Object> extraParams) {
		return (null != url && !url.equals(EMPTY_STRING) && null != menuUrlToPageAccessTypeMap.get(url.toUpperCase())) 
				&& (menuUrlToPageAccessTypeMap.get(url.toUpperCase()).hasAccessToURL(empId, providedAccessRoles, extraParams));
	}
	
	public boolean isPageSecured(final String url) {
		return !menuUrlToPageAccessTypeMap.get(url.toUpperCase()).pageAccessType.equals(PAGE_UNSECURED);
	}
	
	public boolean pageExists(final String url) {
		return null != menuUrlToPageAccessTypeMap.get(url.toUpperCase());
	}
}
