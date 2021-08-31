<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약관리</title>
</head>
<body>
예약관리&nbsp; &nbsp; &nbsp;<a href="/room">
객실관리</a> &nbsp; &nbsp; &nbsp;
<a href="/logout">로그아웃</a>
<br><br>

<%-- <%
	String loginid=(String)session.getAttribute("loginid");
	out.println(loginid);
	if(!loginid.equals("wjsalsdk")){
		response.sendRedirect("http://localhost:8080/com/login");
	}
%>	 --%>
<table  border=1>
    <tr rowspan=3>
    <b></b><br>
   </tr>
    <td>객실목록<br>
    <select style="width: 250px;height: 250px;"multiple>
        <option>백두산 2명</option>
        <option>한라산 5명</option>
        <option>태조산 4명</option>
        <option>남산 2명</option>
        <option>관악산 0명</option>
        </select>
    </td>
    <td>
   
    객실이름 <input type=text value=""> <br> <br>
 
    객실분류 <select style="width:250px;height:80px;"multiple> 
        <option> 스위트룸</option>
        <option> 패밀리룸 </option>
        <option> 더블룸 </option>
        <option> 도미토리룸 </option>
    </select></br>
    <br>
    숙박가능인원 <input type=text size="1"><select size="1" ></select>명<br>
    <br>
    1박요금 <input type=text value="" size="15"> 원 <br>
    <button value="">등록</button>
    <button value="">삭제</button>
    <button value="">Clear</button>
   
</td>
<td></td>
</tr>
</table> 
</body>
</html>