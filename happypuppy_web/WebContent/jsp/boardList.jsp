<%@page import="com.sun.org.apache.bcel.internal.generic.CPInstruction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Core Tag Library, prefix(접두사), jstl jar를 추가해줘야 한다. -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BBS List</title>
</head>
<body>
<%@page import="java.util.ArrayList"%>
<%@page import="team.project.dto.Comment"%>
<%@page import="team.project.dto.User"%>

<%-- <%String totalCount = (String)request.getAttribute("totalCount");
out.print(totalCount); %> --%>
  <div align="right">
    <!-- Login 검증 -->
    <!-- jstl의 if문은 else가 없어서 따로 검증해야함. -->
    <c:if test="${sessionScope.id != null}">
      <%-- <%@include file="loginOk.jsp" %> --%>
    </c:if>
    <c:if test="${sessionScope.id == null}">
      <%-- <%@include file="login.jsp" %> --%>
    </c:if>
  </div>
 
  <div align="center">
    <b>글목록(전체 글:${totalCount})</b>
    <table width="700">
      <tr>
        <td align="right" >
       <%--  <%=request.getParameter("pageNum") --%>
          <a href="boardWriteForm.puppy?pageNum=${pageNum}&paceCode=${pageCode}&commentList=${commentList}&totalCount=${totalCount}&loginedUser=${loginedUser}">글쓰기</a>
        </td>
      </tr>
    </table>
   
    <table border="2" width="700">
      <tr>
        <th width="50">번호</th>
        <th width="250">제목</th>
        <th width="100">작성자</th>
        <th width="150">작성일</th>
        <th width="50">조회</th>
      </tr>
<%--       <%ArrayList<Comment> commentList = (ArrayList<Comment>)request.getAttribute("commentList");%> 
       <%for(int i=0; i<commentList.size(); i++){ %><tr>
       <td width="50"><%=commentList.get(i).getCommentId() %>
       <td width="250"><a href="boardList.puppy?commentID=<%=commentList.get(i).getCommentId()%>"><%=commentList.get(i).getTitle() %></a>
       <td width="100"><%=commentList.get(i).getUserId() %>
       <td width="150"><%=commentList.get(i).getCommntDt() %>
       <td width="50"><%=commentList.get(i).getLikeCt() %><%} %> --%>

       <c:forEach var="comment" items="${commentList}" varStatus="status">
        <tr align="center" height="30">
        <%ArrayList<Comment> commentList = (ArrayList<Comment>)request.getSession().getAttribute("commentList"); 
        User loginedUser = (User)request.getSession().getAttribute("loginedUser");
        commentList.get(1).getCommentId();%>
          <td>${comment.getCommentId()}</td>
          <td align="left">
            <c:if test="${comment.getDepth() > 0}">
              &nbsp;&nbsp;
              <!-- 공백 이미지 -->
              <img src="" width="${10 * comment.getDepth()}" height="16">
              <img src="">
            </c:if>
            <c:if test="${comment.getDepth() == 0}">
              <img src="" width="0" height="16">
            </c:if>
            <!-- URL query의 파라미터들은 request에 자동으로 심어지는 듯 하다. -->
            <a href="boardList.puppy?commentId=${comment.getCommentId()}&pageNum=${pageNum}">${comment.getTitle()}</a>
            <c:if test="${comment.getLikeCt() >= 20}">
            <span class="hit">hit!</span>
              <!-- 조회수 이미지 -->
              <!-- <img src="" border="0" height="16"> -->
            </c:if>
          </td>
          <td>${comment.getUserId()}</td>
          <td>${comment.getCommntDt()}</td>
          <td>${comment.getLikeCt()}</td>
        </tr>
      </c:forEach>
        
      <!-- Paging 처리 -->
      <tr>
        <td colspan="5" align="center" height="40">
          <%-- ${pageCode} --%>
          ${pageCode}
        </td>
      </tr>
    </table>
  </div>
</body>
</html>