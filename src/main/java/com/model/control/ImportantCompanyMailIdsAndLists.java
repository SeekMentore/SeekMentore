package com.model.control;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class ImportantCompanyMailIdsAndLists implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private String prm;
	private String managingPartner;
	private String techlead;
	private String managingDirector;
	private String founder;
	private String officeAdminId;
	private String supportMailList;
	private String errorHandlingMailList;
	private String salesMailList;
	private String financeMailList;
	private String hrMailList;
	
	
	public String getPrm() {
		return prm;
	}
	
	@XmlElement
	public void setPrm(String prm) {
		this.prm = prm;
	}
	
	public String getManagingPartner() {
		return managingPartner;
	}
	
	@XmlElement
	public void setManagingPartner(String managingPartner) {
		this.managingPartner = managingPartner;
	}
	
	public String getTechlead() {
		return techlead;
	}
	
	@XmlElement
	public void setTechlead(String techlead) {
		this.techlead = techlead;
	}
	
	public String getManagingDirector() {
		return managingDirector;
	}
	
	@XmlElement
	public void setManagingDirector(String managingDirector) {
		this.managingDirector = managingDirector;
	}
	
	public String getFounder() {
		return founder;
	}
	
	@XmlElement
	public void setFounder(String founder) {
		this.founder = founder;
	}
	
	public String getOfficeAdminId() {
		return officeAdminId;
	}
	
	@XmlElement
	public void setOfficeAdminId(String officeAdminId) {
		this.officeAdminId = officeAdminId;
	}
	
	public String getSupportMailList() {
		return supportMailList;
	}
	
	@XmlElement
	public void setSupportMailList(String supportMailList) {
		this.supportMailList = supportMailList;
	}
	
	public String getErrorHandlingMailList() {
		return errorHandlingMailList;
	}
	
	@XmlElement
	public void setErrorHandlingMailList(String errorHandlingMailList) {
		this.errorHandlingMailList = errorHandlingMailList;
	}
	
	public String getSalesMailList() {
		return salesMailList;
	}
	
	@XmlElement
	public void setSalesMailList(String salesMailList) {
		this.salesMailList = salesMailList;
	}
	
	public String getFinanceMailList() {
		return financeMailList;
	}
	
	@XmlElement
	public void setFinanceMailList(String financeMailList) {
		this.financeMailList = financeMailList;
	}
	
	public String getHrMailList() {
		return hrMailList;
	}
	
	@XmlElement
	public void setHrMailList(String hrMailList) {
		this.hrMailList = hrMailList;
	}
	
}
