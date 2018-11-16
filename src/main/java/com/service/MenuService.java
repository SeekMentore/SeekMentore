package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.MenuConstants;
import com.exception.ApplicationException;
import com.model.bouipath.BOUIPath;
import com.model.bouipath.MenuItem;
import com.model.bouipath.UIMenu;
import com.model.bouipath.UIPage;
import com.model.bouipath.UISubMenu;
import com.model.bouipath.UserType;
import com.model.bouipath.ViewType;
import com.model.menu.Menu;
import com.model.menu.Page;
import com.model.menu.PageList;
import com.model.menu.Webservice;
import com.utils.ApplicationUtils;

@Service(BeanConstants.BEAN_NAME_MENU_SERVICE)
public class MenuService implements MenuConstants {
	
	private Menu applicationMenu;
	private BOUIPath applicationBOUIPath;
	private Map<String, MenuAttributes> menuUrlToPageAccessTypeMap;
	private Map<String, MenuAttributes> boUIPathUrlToPageAccessTypeMap;
	private UIMenu[] boUIMenuArray; 
	
	class MenuAttributes {
		private String pageAccessType;
		private String additionalAccessFunction;
		
		MenuAttributes(String pageAccessType, String additionalAccessFunction) {
			this.pageAccessType = pageAccessType;
			this.additionalAccessFunction = additionalAccessFunction;
		}
		
		public boolean hasAccessToURL(final String userId, final List<String> providedAccessRoles, final Map<String, Object> extraParams) {
			return PAGE_UNSECURED.equals(this.pageAccessType) 
					|| (checkPageAccessTypeWithProvidedRoles(providedAccessRoles) && checkAdditionalAccess(userId, extraParams));
		}
		
		private boolean checkAdditionalAccess(final String empId, final Map<String, Object> extraParams) {
			if (null == this.additionalAccessFunction || EMPTY_STRING.equals(this.additionalAccessFunction.trim()))
				return true;
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
		this.applicationBOUIPath = ApplicationUtils.parseXML(MENU_BO_UI_PATH, BOUIPath.class);
		this.menuUrlToPageAccessTypeMap = new HashMap<String, MenuAttributes>();
		this.boUIPathUrlToPageAccessTypeMap = new HashMap<String, MenuAttributes>();
		final List<UIMenu> boUIMenuList = new LinkedList<UIMenu>();
		createMenuUrlToPageAccessTypeMap();
		createBOUIPathUrlToPageAccessTypeMap(boUIMenuList);
		reorderBOUIMenuListAsPerOrderingInXML(boUIMenuList);
	}
	
	private void reorderBOUIMenuListAsPerOrderingInXML(final List<UIMenu> boUIMenuList) {
		boUIMenuArray = new UIMenu[boUIMenuList.size()];
		for (int index = 0; index < boUIMenuList.size(); index++) {
			final UIMenu uiMenu = boUIMenuList.get(index);
			uiMenu.computeSubMenuArrayAsPerOrderingInXML();
			boUIMenuArray[uiMenu.getOrder() - 1] = uiMenu;
		}
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
							final String overridenPageAccessType = (!PAGE_UNSECURED.equals(pageAccessType)) ? ((null != webservice.getPageAccessType()) ? webservice.getPageAccessType() : pageAccessType) : PAGE_UNSECURED;
							menuUrlToPageAccessTypeMap.put(url.toUpperCase(), new MenuAttributes(overridenPageAccessType, webservice.getAdditionalAccessFunction()));
						}
					}
				}
			}
		}
	}
	
	private void createBOUIPathUrlToPageAccessTypeMap(final List<UIMenu> boUIMenuList) {
		for (final ViewType viewtype : applicationBOUIPath.getViewtype()) {
			if (null == viewtype.getPageAccessType()) {
				// Set it unsecured
				viewtype.setPageAccessType(PAGE_UNSECURED);
			}
			if (null != viewtype.getUrlActive() && viewtype.getUrlActive()) {
				final String url = viewtype.getUrl();
				final String pageAccessType = viewtype.getPageAccessType();
				boUIPathUrlToPageAccessTypeMap.put(url.toUpperCase(), new MenuAttributes(pageAccessType, viewtype.getAdditionalAccessFunction()));
			}
			createBOUIPathUrlToPageAccessTypeMapForUserType(boUIMenuList, viewtype.getUsertype(), viewtype.getPageAccessType(), viewtype.getUrl());
			createBOUIPathUrlToPageAccessTypeMapForMenuItem(boUIMenuList, viewtype.getMenuitem(), viewtype.getPageAccessType(), viewtype.getUrl());
			createBOUIPathUrlToPageAccessTypeMapForUIPage(boUIMenuList, viewtype.getUipage(), viewtype.getPageAccessType(), viewtype.getUrl(), null);
		}
	}
	
	private void createBOUIPathUrlToPageAccessTypeMapForUserType(final List<UIMenu> boUIMenuList, final ArrayList<UserType> usertypes, final String parentPageAccessType, final String parentURL) {
		if (null != usertypes && !usertypes.isEmpty()) {
			for (final UserType usertype : usertypes) {
				if (null == usertype.getPageAccessType()) {
					usertype.setPageAccessType(parentPageAccessType);
				} 
				if (null != usertype.getUrlActive() && usertype.getUrlActive()) {
					final String url = parentURL + usertype.getUrl();
					final String pageAccessType = usertype.getPageAccessType();
					boUIPathUrlToPageAccessTypeMap.put(url.toUpperCase(), new MenuAttributes(pageAccessType, usertype.getAdditionalAccessFunction()));
				}
				createBOUIPathUrlToPageAccessTypeMapForMenuItem(boUIMenuList, usertype.getMenuitem(), usertype.getPageAccessType(), parentURL + usertype.getUrl());
				createBOUIPathUrlToPageAccessTypeMapForUIPage(boUIMenuList, usertype.getUipage(), usertype.getPageAccessType(), parentURL + usertype.getUrl(), null);
			}
		}
	}
	
	private void createBOUIPathUrlToPageAccessTypeMapForMenuItem(final List<UIMenu> boUIMenuList, final ArrayList<MenuItem> menuitems, final String parentPageAccessType, final String parentURL) {
		if (null != menuitems && !menuitems.isEmpty()) {
			for (final MenuItem menuitem : menuitems) {
				final UIMenu uiMenu =  new UIMenu(menuitem.getItemHeader());
				uiMenu.setOrder(menuitem.getOrder());
				if (null == menuitem.getPageAccessType()) {
					menuitem.setPageAccessType(parentPageAccessType);
				} 
				final String pageAccessType = menuitem.getPageAccessType();
				uiMenu.setPageAccessType(pageAccessType);
				if (null != menuitem.getUrlActive() && menuitem.getUrlActive()) {
					final String url = parentURL + menuitem.getUrl();
					uiMenu.setUrl(url);
					boUIPathUrlToPageAccessTypeMap.put(url.toUpperCase(), new MenuAttributes(pageAccessType, menuitem.getAdditionalAccessFunction()));
				}
				createBOUIPathUrlToPageAccessTypeMapForUIPage(boUIMenuList, menuitem.getUipage(), menuitem.getPageAccessType(), parentURL + menuitem.getUrl(), uiMenu);
				boUIMenuList.add(uiMenu);
			}
		}
	}
	
	private void createBOUIPathUrlToPageAccessTypeMapForUIPage(final List<UIMenu> boUIMenuList, final ArrayList<UIPage> uipages, final String parentPageAccessType, final String parentURL, final UIMenu uiMenu) {
		if (null != uipages && !uipages.isEmpty()) {
			if (null != uiMenu) {
				uiMenu.setSubmenu(true);
				uiMenu.setSubMenuItems(new LinkedList<UISubMenu>());
			}
			for (final UIPage uipage : uipages) {
				if (null == uipage.getPageAccessType()) {
					uipage.setPageAccessType(parentPageAccessType);
				} 
				if (null != uipage.getUrlActive() && uipage.getUrlActive()) {
					final String url = parentURL + uipage.getUrl();
					final String pageAccessType = uipage.getPageAccessType();
					if (null != uiMenu) {
						uiMenu.addUISubMenu(uipage.getItemHeader(), url, pageAccessType, uipage.getOrder());
					}
					boUIPathUrlToPageAccessTypeMap.put(url.toUpperCase(), new MenuAttributes(pageAccessType, uipage.getAdditionalAccessFunction()));
				}
			}
		}
	}

	public Menu getApplicationMenu() {
		return applicationMenu;
	}
	
	public UIMenu[] getBOUIMenuArray() {
		return boUIMenuArray;
	}
	
	public boolean hasAccessToBOUIMenuItem(final List<String> providedAccessRoles, final UIMenu uiMenuItem) {
		final String[] pageAccessTypeList = uiMenuItem.getPageAccessType().split(COMMA);
		for (final String pageAccessType : pageAccessTypeList) {
			for (final String role : providedAccessRoles) {
				if (role.equals(pageAccessType)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasAccessToBOUISubMenuItem(final List<String> providedAccessRoles, final UISubMenu uisubMenuItem) {
		final String[] pageAccessTypeList = uisubMenuItem.getPageAccessType().split(COMMA);
		for (final String pageAccessType : pageAccessTypeList) {
			for (final String role : providedAccessRoles) {
				if (role.equals(pageAccessType)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasAccessToURL(final String userId, final String url, final List<String> providedAccessRoles, final Map<String, Object> extraParams) {
		return (null != url && !url.equals(EMPTY_STRING) && null != menuUrlToPageAccessTypeMap.get(url.toUpperCase())) 
				&& (menuUrlToPageAccessTypeMap.get(url.toUpperCase()).hasAccessToURL(userId, providedAccessRoles, extraParams));
	}
	
	public boolean hasAccessToUIURL(final String userId, final String url, final List<String> providedAccessRoles, final Map<String, Object> extraParams) {
		return (null != url && !url.equals(EMPTY_STRING) && null != boUIPathUrlToPageAccessTypeMap.get(url.toUpperCase())) 
				&& (boUIPathUrlToPageAccessTypeMap.get(url.toUpperCase()).hasAccessToURL(userId, providedAccessRoles, extraParams));
	}
	
	public boolean isPageSecured(final String url) {
		return !menuUrlToPageAccessTypeMap.get(url.toUpperCase()).pageAccessType.equals(PAGE_UNSECURED);
	}
	
	public boolean pageExists(final String url) {
		return null != menuUrlToPageAccessTypeMap.get(url.toUpperCase());
	}
}
