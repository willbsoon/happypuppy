package team.project.service;
//이동 하려는 다음페이지 정보를 저장하는 클래스
public class NextPage {
	//이동하려는 페이지 이름
	private String pageName;
	//이동 방식
	private boolean isRedirect;
	
	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
}
