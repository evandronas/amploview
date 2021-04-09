package com.amplo.view;

import org.springframework.data.annotation.Id;

/**
 * @author Evandro
 */
public class AmploView {

	@Id private String id;

	private String serverid;
	private String sponsor;
	private String servername;
	private String timestamp;
	private String distance;
	private String ping;
	private String download;
	private String upload;
	private String share;
	private String ipaddress;

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getPing() {
		return ping;
	}

	public void setPing(String ping) {
		this.ping = ping;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}
	
	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}
	
	public String getIPAddress() {
		return ipaddress;
	}

	public void setIPAddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
}
