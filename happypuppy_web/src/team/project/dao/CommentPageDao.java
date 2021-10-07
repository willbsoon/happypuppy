package team.project.dao;

public class CommentPageDao {
	private static CommentPageDao commentPageDao = new CommentPageDao();

	private int startRow, endRow;
	private StringBuffer sb;
	private CommentPageDao() {
		System.out.println("new CommentPageDao()");
	}

	public static CommentPageDao getInstance(){
		return commentPageDao;
	}
	//pageSize:리스트row개수, pageBlock:하단 페이지 개수
	public synchronized void paging(int pageNum, int totalCount, int pageSize, int pageBlock){
		System.out.println("2. paging Start");
		//totalPage : 총 페이지 수
		int totalPage =(int)Math.ceil((double)totalCount/pageSize);
		//startRow, endRow :한페이지의 시작, 끝 row 계산
		startRow = (pageNum-1) * pageSize +1;
		endRow = pageNum * pageSize;
		
		System.out.println("startRow : " +startRow);
		System.out.println("endRow : " +endRow);
		
		int startPage = (int)((pageNum-1)/pageBlock * pageBlock +1);
		int endPage = startPage + pageBlock -1;
		
		if(endPage > totalPage){
			endPage = totalPage;
		}
		
		sb = new StringBuffer();
		
		if(startPage < pageBlock){
			
		}else{
			sb.append("<span width='30' height='9' ");
			sb.append("onclick='location.href=\"boardList.puppy?pageNum=");
			sb.append(startPage - pageBlock);
			sb.append("\"' </span>");
			sb.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		}
		
		//pageBlock에 포함되는 페이지 숫자 출력하고, 링크를 달아준다.
		for(int i= startPage; i<=endPage; i++){
			if(i == pageNum){
				sb.append("&nbsp;&nbsp;<b>");
				sb.append(i);
				sb.append("</b>");
			}else{
				sb.append("&nbsp;&nbsp;<a href='boardList.puppy?pageNum=");
				sb.append(i);
				sb.append("'>");
				sb.append(i);
				sb.append("</a>");
			}
		}
		
		if(endPage < totalPage){
			sb.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
			sb.append("<span width='30' height='9' ");
			sb.append("onclick='location.href=\"boardList.puppy?pageNum=");
			sb.append(startPage+pageBlock);
			sb.append("\" </span>");
		}else{
			
		}
			
	}
	
	public StringBuffer getSb(){
		return sb;
	}
	
	public int getStartRow(){
		return startRow;
	}
	
	public int getEndRow(){
		return endRow;
	}
	
	
}
