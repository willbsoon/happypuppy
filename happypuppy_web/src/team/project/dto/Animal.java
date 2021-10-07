package team.project.dto;

import java.io.Serializable;

//Animal DTO
public class Animal implements Serializable{
	private String noticeEdt;//공고종료일
	private String popfile;//이미지
	private String processState;//상태
	private String sexCd;//성별
	private String neuterYn;//중성화 여부
	
	private String specialMark;//특징
	private String careNm;//보호소이름
	private String careTel;//보호소전화번호
	private String careAddr;//보호장소
	private String orgNm;//관할기관
	
	private String chargeNm;//담당자
	private String officetel;//담당자연락처
	private String desertionNo;//유기번호
	private String filename;//썸네일 이미지
	private String happenDt;//접수일
	
	private String happenPlace;//발견장소
	private String kindCd;//품종
	private String colorCd;//색상
	private String age;//나이
	private String weight;//체중
	
	private String noticeNo;//공고번호
	private String noticeSdt;//공고시작일
	private Float death;//종료(자연사)
	private Float killed;//종료(안락사)
	private Float returned;//종료(반환)
	
	private Float bring;//종료(입양)

	public String getNoticeEdt() {
		return noticeEdt;
	}

	public void setNoticeEdt(String noticeEdt) {
		this.noticeEdt = noticeEdt;
	}

	public String getPopfile() {
		return popfile;
	}

	public void setPopfile(String popfile) {
		this.popfile = popfile;
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public String getNeuterYn() {
		return neuterYn;
	}

	public void setNeuterYn(String neuterYn) {
		this.neuterYn = neuterYn;
	}

	public String getSpecialMark() {
		return specialMark;
	}

	public void setSpecialMark(String specialMark) {
		this.specialMark = specialMark;
	}

	public String getCareNm() {
		return careNm;
	}

	public void setCareNm(String careNm) {
		this.careNm = careNm;
	}

	public String getCareTel() {
		return careTel;
	}

	public void setCareTel(String careTel) {
		this.careTel = careTel;
	}

	public String getCareAddr() {
		return careAddr;
	}

	public void setCareAddr(String careAddr) {
		this.careAddr = careAddr;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getChargeNm() {
		return chargeNm;
	}

	public void setChargeNm(String chargeNm) {
		this.chargeNm = chargeNm;
	}

	public String getOfficetel() {
		return officetel;
	}

	public void setOfficetel(String officetel) {
		this.officetel = officetel;
	}

	public String getDesertionNo() {
		return desertionNo;
	}

	public void setDesertionNo(String desertionNo) {
		this.desertionNo = desertionNo;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getHappenDt() {
		return happenDt;
	}

	public void setHappenDt(String happenDt) {
		this.happenDt = happenDt;
	}

	public String getHappenPlace() {
		return happenPlace;
	}

	public void setHappenPlace(String happenPlace) {
		this.happenPlace = happenPlace;
	}

	public String getKindCd() {
		return kindCd;
	}

	public void setKindCd(String kindCd) {
		this.kindCd = kindCd;
	}

	public String getColorCd() {
		return colorCd;
	}

	public void setColorCd(String colorCd) {
		this.colorCd = colorCd;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeSdt() {
		return noticeSdt;
	}

	public void setNoticeSdt(String noticeSdt) {
		this.noticeSdt = noticeSdt;
	}

	public Float getDeath() {
		return death;
	}

	public void setDeath(Float death) {
		this.death = death;
	}

	public Float getKilled() {
		return killed;
	}

	public void setKilled(Float killed) {
		this.killed = killed;
	}

	public Float getReturned() {
		return returned;
	}

	public void setReturned(Float returned) {
		this.returned = returned;
	}

	public Float getBring() {
		return bring;
	}

	public void setBring(Float bring) {
		this.bring = bring;
	}

	@Override
	public String toString() {
		return "Animal [noticeEdt=" + noticeEdt + ", popfile=" + popfile + ", processState=" + processState + ", sexCd="
				+ sexCd + ", neuterYn=" + neuterYn + ", specialMark=" + specialMark + ", careNm=" + careNm
				+ ", careTel=" + careTel + ", careAddr=" + careAddr + ", orgNm=" + orgNm + ", chargeNm=" + chargeNm
				+ ", officetel=" + officetel + ", desertionNo=" + desertionNo + ", filename=" + filename + ", happenDt="
				+ happenDt + ", happenPlace=" + happenPlace + ", kindCd=" + kindCd + ", colorCd=" + colorCd + ", age="
				+ age + ", weight=" + weight + ", noticeNo=" + noticeNo + ", noticeSdt=" + noticeSdt + ", death="
				+ death + ", killed=" + killed + ", returned=" + returned + ", bring=" + bring + "]";
	}
	
	
}
