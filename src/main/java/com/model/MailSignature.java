package com.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.constants.ApplicationConstants;

public class MailSignature implements Serializable, Cloneable, ApplicationConstants {

	private static final long serialVersionUID = -3129291165409101669L;
	
	private String from;
	private List<String> mobiles;
	private List<String> emails;
	private List<String> websites;
	
	public MailSignature(final String from) {
		this.from = from;
		this.mobiles = new LinkedList<String>();
		this.emails = new LinkedList<String>();
		this.websites = new LinkedList<String>();
	}
	
	public void addMobile(final String mobile) {
		this.mobiles.add(mobile);
	}
	
	public void addEmail(final String email) {
		this.emails.add(email);
	}
	
	public void addWebsite(final String website) {
		this.websites.add(website);
	}
	
	public void addAllMobile(final List<String> mobiles) {
		this.mobiles.addAll(mobiles);
	}
	
	public void addAllEmail(final List<String> emails) {
		this.emails.addAll(emails);
	}
	
	public List<String> getMobiles() {
		return mobiles;
	}

	public void setMobiles(List<String> mobiles) {
		this.mobiles = mobiles;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public List<String> getWebsites() {
		return websites;
	}

	public void setWebsites(List<String> websites) {
		this.websites = websites;
	}

	public void addAllWebsite(final List<String> websites) {
		this.websites.addAll(websites);
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	@Override
	public MailSignature clone() throws CloneNotSupportedException {  
		return (MailSignature)super.clone();
	}
}
