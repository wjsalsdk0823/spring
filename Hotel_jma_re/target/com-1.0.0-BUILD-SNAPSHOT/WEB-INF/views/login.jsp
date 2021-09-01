<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<form method="post" action="/check_user">
로그인 아이디:<input type=text name=userid><br>
비밀번호:<input type = text name=passcode ><br>
<input type=submit value="로그인">
<a href="/">홈페이지</a>
</form>

</body>
</html>