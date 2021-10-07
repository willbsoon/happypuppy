<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@page import="team.project.dto.User"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>마이페이지</title>
<!-- Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- Custom style -->
<link rel="stylesheet" href="../css/custom.css" media="screen"
	title="no title" charset="utf-8">
<script src="../js/bootstrap.min.js"></script>
<!-- jquery -->
<script src="../js/jquery.min.js"></script>
<script type="text/javascript">
	// 필수 입력정보가 입력되었는지 확인하는 함수들
	function checkValue() {
		if (!document.user.password.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
		if (document.user.password.value != document.user.passwordcheck.value) {
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}
		if (!(document.user.birth.value.length==8)) {
			alert("생년월일을 입력하세요.");
			return false;
		}
	}
</script>
</head>
<body>
<%
User loginedUser = (User) request.getSession().getAttribute("loginedUser");
%>
<br/>
	<form action="userDelete.puppy" method="post" name="user"
		onsubmit="return checkValue()" ><!-- enctype="multipart/form-data" -->
		<article class="container">
			<div class="page-header">
				<h1>정말 탈퇴하시겠습니까?</h1>
			</div>
			<div class="col-md-6 col-md-offset-3">
				<form role="form">
				<!-- method="post" action="surveytest2.jsp"    -->
					<div class="form-group">
						<label for="InputEmail">이메일 주소를 입력해주세요</label> <input type="email"
							class="form-control" id="email" name="email"
							maxlength="50" >

					</div>
					<div class="form-group">
						<label for="InputPassword1">*비밀번호를 다시한번 입력해주세요</label> <input type="password"
							class="form-control" id="password" name="password" placeholder="비밀번호"
							maxlength="50">
					</div>
					<div class="form-group">
						<label for="InputPassword2"></label> <input
							type="password" class="form-control" id="passwordcheck"
							placeholder="비밀번호 확인" maxlength="50">
						<p class="help-block">비밀번호 확인을 위해 다시한번 입력 해 주세요</p>
					</div>
					<div class="form-group">
						<label for="username">이름</label> <input type="text"
							class="form-control" name="name" value=<%=loginedUser.getName()%>
							maxlength="50" disabled>
					</div>
					
					<div class="form-group">
						<label for="username">*생년월일을 입력해주세요</label> <input type="text"
							class="form-control" id="birth" name="born" placeholder="예)20101213"
							maxlength="8">
					</div>
									
					<div class="form-group text-center">				
						<a href="userDelete.puppy"><button type="submmit" class="btn" id="delete">
							회원탈퇴<i class="fa fa-times spaceLeft"></i>
						</button></a>
					</div>
				</form>
			</div>
		</article>
	</form>
</body>
</html>