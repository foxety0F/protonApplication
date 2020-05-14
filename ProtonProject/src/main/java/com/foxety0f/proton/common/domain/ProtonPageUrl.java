package com.foxety0f.proton.common.domain;

public class ProtonPageUrl implements Comparable<ProtonPageUrl>{

	private String url;
	private String urlName;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	@Override
	public String toString() {
		return "ProtonPageUrl [url=" + url + ", urlName=" + urlName + "]";
	}

	@Override
	public int compareTo(ProtonPageUrl o) {
		return this.urlName.length() - o.getUrlName().length();
	}

}
