package team.project.dto;

public class Recommend {
	private String bgnde;//시작날짜
	private String endde;//끝나는날짜
	private String upkind;//개 고양이 특수
	private String upr_cd;//도시번호
	private String state;//상태
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBgnde() {
		return bgnde;
	}
	public void setBgnde(String bgnde) {
		this.bgnde = bgnde;
	}
	public String getEndde() {
		return endde;
	}
	public void setEndde(String endde) {
		this.endde = endde;
	}
	public String getUpkind() {
		return upkind;
	}
	public void setUpkind(String upkind) {
		this.upkind = upkind;
	}
	public String getUpr_cd() {
		return upr_cd;
	}
	public void setUpr_cd(String upr_cd) {
		this.upr_cd = upr_cd;
	}
}
