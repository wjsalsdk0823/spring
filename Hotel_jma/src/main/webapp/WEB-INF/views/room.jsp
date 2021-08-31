<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>객실관리</title>
</head>
<a href="/booking">예약관리</a>&nbsp; &nbsp; &nbsp;객실관리 &nbsp; &nbsp; &nbsp;
<a href="/logout">로그아웃</a><br>
<table border=1>
	<tr>
		<td>        
	        <select id="selRoomList" size=10 style='width:250px;'>
	        <c:forEach items="${list}" var="room">
	        	<option value='${room.roomcode}'>${room.name},${room.type},${room.howmany},${room.howmuch}</option>
	        </c:forEach>
	        </select>
        </td>
        <td>
        	<table>
        	<tr>
        		<td align=right>객실명</td>
        		<td><input type=text id=txtName><input type=hidden id="roomcode"></td>
        	</tr>
        	<tr>
        		<td align=right>타입</td>
        		<td>
        			<select size=5 style='width:120px;' id=selType>
        			<c:forEach items="${list2}" var="roomtype">
	        			<option value='${roomtype.typecode}'>${roomtype.name}</option>
	        		</c:forEach> 
        			</select>	
        		</td>
        	</tr>
        	<tr>
        		<td align=right>최대숙박인원</td><td><input type=number id=txtNum></td>
        	</tr>
        	<tr>
        		<td align=right>1박가격</td><td><input type=number id=txtPrice></td>
        	</tr>
        	<tr>
        		<td colspan=2 align=center>
        			<input type=button value='등록' id=btnAdd>&nbsp;
        			<input type=button value='삭제' id=btnDelete>&nbsp;
        			<input type=button value='취소' id=btnEmpty>&nbsp;
        		</td>
        	</tr>
        	</table>
        </td>
      </tr>
      </table>
      </body>
      <script src='https://code.jquery.com/jquery-3.5.0.js'></script>
      <script>
      	 $(document)
         .ready(function(){
        	 
        	 console.log($("#selRoomList option:selected").val())
        	 
        	 $("#selRoomList").change(function(){
        		 $("#txtName").val($("#selRoomList option:checked").text().substring(0,3));
        	 })
         })
        
//       $(document).on('click','#'){
    	  
//       }

     	
      	
   		
      </script>
      </html>
  <!--   <table  border=1>
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
    </table>  -->

