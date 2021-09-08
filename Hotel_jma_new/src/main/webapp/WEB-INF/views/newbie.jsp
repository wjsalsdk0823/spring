<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/signin" id="frmSignin">
실명:<input type=text name=txtname><br>
로그인 아이디:<input type=text name="login"><br>
비밀번호:<input type=passcocd name=passcode ><br>
비밀번호 확인:<input type=password name=passcode2 ><br>
<input type=submit value="회원가입 등록">
<a href='/'>취소</a>
</form>
</body>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<!-- <script>
$(document)
.on('click','#frmSignin',function(){
   //console.log("debug btn")
  if($('input[name=txtname]').val()==''){
	  alert('이름을 입력하시오');
	  return false;
  }
  if($('input[name=login]').val()==''){
	  alert('id를 입력하시오');
	  return false;
  } 
  if($('input[name=passcode]').val()==''){
	  alert('비밀번호를 입력하시오');
	  return false;
  }
  if($('input[name=passcode]').val()!=$('input[name=passcode2]').val()){
	  alert('비밀번호가 일치하지 않습니다');
	  return false;
  }
  return true;
}); -->
</script>
</html>