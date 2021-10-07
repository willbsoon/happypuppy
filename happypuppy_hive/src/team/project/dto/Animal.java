package team.project.dto;

import java.io.Serializable;

public class Animal implements Serializable{
	private String sexCd;
	private String kindCd;
	private String noticeNo;
	private String careAddr;
	private String processState;
	private int noticeSdt;
	private String weight;
	private String chargeNm;
	private int desertionNo;
	private String careTel;
	private String happenPlace;
	private String officetel;
	private String orgNm;
	private String filename;
	private String careNm;
	private int noticeEdt;
	private String specialMark;
	private String colorCd;
	private int happenDt;
	private String age;
	private String popfile;
	private int totalCount;
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public String getKindCd() {
		return kindCd;
	}
	public void setKindCd(String kindCd) {
		this.kindCd = kindCd;
	}
	public String getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getCareAddr() {
		return careAddr;
	}
	public void setCareAddr(String careAddr) {
		this.careAddr = careAddr;
	}
	public String getProcessState() {
		return processState;
	}
	public void setProcessState(String processState) {
		this.processState = processState;
	}
	public int getNoticeSdt() {
		return noticeSdt;
	}
	public void setNoticeSdt(int noticeSdt) {
		this.noticeSdt = noticeSdt;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getChargeNm() {
		return chargeNm;
	}
	public void setChargeNm(String chargeNm) {
		this.chargeNm = chargeNm;
	}
	public int getDesertionNo() {
		return desertionNo;
	}
	public void setDesertionNo(int desertionNo) {
		this.desertionNo = desertionNo;
	}
	public String getCareTel() {
		return careTel;
	}
	public void setCareTel(String careTel) {
		this.careTel = careTel;
	}
	public String getHappenPlace() {
		return happenPlace;
	}
	public void setHappenPlace(String happenPlace) {
		this.happenPlace = happenPlace;
	}
	public String getOfficetel() {
		return officetel;
	}
	public void setOfficetel(String officetel) {
		this.officetel = officetel;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCareNm() {
		return careNm;
	}
	public void setCareNm(String careNm) {
		this.careNm = careNm;
	}
	public int getNoticeEdt() {
		return noticeEdt;
	}
	public void setNoticeEdt(int noticeEdt) {
		this.noticeEdt = noticeEdt;
	}
	public String getSpecialMark() {
		return specialMark;
	}
	public void setSpecialMark(String specialMark) {
		this.specialMark = specialMark;
	}
	public String getColorCd() {
		return colorCd;
	}
	public void setColorCd(String colorCd) {
		this.colorCd = colorCd;
	}
	public int getHappenDt() {
		return happenDt;
	}
	public void setHappenDt(int happenDt) {
		this.happenDt = happenDt;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPopfile() {
		return popfile;
	}
	public void setPopfile(String popfile) {
		this.popfile = popfile;
	}
	@Override
	public String toString() {
		return "Animal [sexCd=" + sexCd + ", kindCd=" + kindCd + ", noticeNo=" + noticeNo + ", careAddr=" + careAddr
				+ ", processState=" + processState + ", noticeSdt=" + noticeSdt + ", weight=" + weight + ", chargeNm="
				+ chargeNm + ", desertionNo=" + desertionNo + ", careTel=" + careTel + ", happenPlace=" + happenPlace
				+ ", officetel=" + officetel + ", orgNm=" + orgNm + ", filename=" + filename + ", careNm=" + careNm
				+ ", noticeEdt=" + noticeEdt + ", specialMark=" + specialMark + ", colorCd=" + colorCd + ", happenDt="
				+ happenDt + ", age=" + age + ", popfile=" + popfile + "]";
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	

}
