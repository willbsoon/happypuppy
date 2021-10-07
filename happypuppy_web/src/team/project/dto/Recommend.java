package team.project.dto;

//RecommendDTO
public class Recommend {
	private String bgnde;// 시작날짜
	private String endde;// 종료날짜
	private String upkind;// 동물종류
	private String upr_cd;// 지역코드
	private String numOfRows; //검색건수
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
	public String getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(String numOfRows) {
		this.numOfRows = numOfRows;
	}
	@Override
	public String toString() {
		return "Recommend [bgnde=" + bgnde + ", endde=" + endde + ", upkind=" + upkind + ", upr_cd=" + upr_cd
				+ ", numOfRows=" + numOfRows + "]";
	}
	
	

}
