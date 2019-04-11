package com.model.bouipath;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class UIMenu implements Serializable, Cloneable {
	
	private static final long serialVersionUID = -2691803338338336066L;
	
	private String name;
	private Boolean submenu;
	private String url;
	private List<UISubMenu> subMenuItems;
	private String pageAccessType;
	private Integer order;
	private Boolean hidden;
	private UISubMenu[] subMenuArray;
	
	public UIMenu() {}
	
	public UIMenu(final String name) {
		this.name = name;
		this.submenu = false;
		this.url = null;
	}
	
	public UIMenu(final String name, final String url) {
		this.name = name;
		this.submenu = false;
		this.url = url;
	}
	
	public UIMenu(final String name, final Boolean submenu, final String url) {
		this.name = name;
		this.submenu = submenu;
		this.url = url;
		if (this.submenu) {
			subMenuItems = new LinkedList<UISubMenu>();
		}
	}
	
	public UIMenu cloneForSendingToBOUI() throws CloneNotSupportedException {
		final UIMenu newInstance = clone();
		if (newInstance.submenu) {
			newInstance.subMenuItems = new LinkedList<UISubMenu>();
		}
		newInstance.pageAccessType = null;
		newInstance.order = null;
		newInstance.hidden = null;
		newInstance.subMenuArray = null;
		return newInstance;
	}
	
	public void addUISubMenu(UISubMenu subMenu) {
		if (this.submenu) {
			subMenuItems.add(subMenu);
		}
	}
	
	public void addUISubMenu(final String subMenuName, final String subMenuUrl, final String pageAccessType, final Integer order, final Boolean hidden) {
		addUISubMenu(new UISubMenu(subMenuName, subMenuUrl,pageAccessType, order, hidden));
	}
	
	public void computeSubMenuArrayAsPerOrderingInXML() {
		if (this.submenu) {
			final UISubMenu[] subMenuIntermittentArray = new UISubMenu[subMenuItems.size()];
			for (int index = 0; index < subMenuItems.size(); index++) {
				final UISubMenu uiSubMenu = subMenuItems.get(index);
				subMenuIntermittentArray[uiSubMenu.getOrder() - 1] = uiSubMenu;
			}
			final List<UISubMenu> uiSubMenuListFinalized = new LinkedList<UISubMenu>();
			for (int index = 0; index < subMenuIntermittentArray.length; index++) {
				if (!subMenuIntermittentArray[index].getHidden()) {
					uiSubMenuListFinalized.add(subMenuIntermittentArray[index]);
				}
			}
			subMenuArray = uiSubMenuListFinalized.toArray(new UISubMenu[0]);
		} else {
			subMenuArray = new UISubMenu[0];
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSubmenu() {
		return submenu;
	}

	public void setSubmenu(Boolean submenu) {
		this.submenu = submenu;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<UISubMenu> getSubMenuItems() {
		return subMenuItems;
	}

	public void setSubMenuItems(List<UISubMenu> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}

	public String getPageAccessType() {
		return pageAccessType;
	}

	public void setPageAccessType(String pageAccessType) {
		this.pageAccessType = pageAccessType;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public UISubMenu[] getSubMenuArray() {
		return subMenuArray;
	}

	public void setSubMenuArray(UISubMenu[] subMenuArray) {
		this.subMenuArray = subMenuArray;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	
	@Override
	public UIMenu clone() throws CloneNotSupportedException {  
		return (UIMenu)super.clone();
	}
}
