<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>객실관리</title>
</head>
<body>
<a href="/booking">예약관리</a>
&nbsp; &nbsp; &nbsp;
객실관리 &nbsp; &nbsp; &nbsp;
<a href="/logout">로그아웃</a>
<body>
    <table  border=1>
    <tr rowspan=4><br>
    </tr>
   
    <td>숙박기간
    <input type="date"><br>
    <br>객실분류
    <select style="width: 140px;height: 25px;"></select><br>
    예약가능<br>
    <select style="width: 250px;height: 150px;"></select><br>
    <td>
    객실이름 <input type=text size="16" value=""><br><br>
    숙박기간 <input type="date"></br>
    <br>
    숙박인원 <input type=text size="10"><select size="1" ></select>명<br>
    <br>
    총 숙박비<input type=text value="" size="14">원<br>
    예약자 전화번호<br>
    <input type="text" size="26"><br>
    <button value="" >등록</button>
    <button value="">취소</button>
    <button value="">Clear</button>
    </td>
   
    <td>예약된 객실<br><br>
    <textarea cols="20" rows="18"></textarea>
    </td>
    </tr>
    </table> 
</body>
</body>
</html>