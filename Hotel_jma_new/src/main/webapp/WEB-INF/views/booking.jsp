<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<table border=1>
	<tr>
		<td>   
		     예약기간<input type=date id="period1">~<input type=date id="period2"><br>
		     객실종류
		     <select size=1 id="select_type">
		     <option value='-'>전체</option>
		     	<option value=1>Suite Room</option>
           		<option value=2>Family Room</option>
            	<option value=3>Double Room</option>
            	<option value=4>Single Room</option>
		     </select> <input type="button"  id="look" value=조회><br>
		     예약가능객실<br>	     
	        <select id="selRoom" size=10 style='width:250px;'>    	
	       <%--  <c:forEach items="${list}" var="room">
	        	<option value='${room.roomcode}'>${room.roomname},${room.typename},${room.howmany},${room.howmuch}</option>
	        </c:forEach> --%>
	        </select>
	          
        </td>
        <td>
        	<table>
        	<tr>
        	   
        		<td align=right>객실명</td>
        		
        		<td><input type=text id=txtName><input type=hidden id="roomcode"></td>
        	</tr>
        	<tr>
        		<td align=right>객실종류</td>
        		<td>
        			<select size=1 style='width:150px;' id=selType>
				     	<option value=1>Suite Room</option>
		           		<option value=2>Family Room</option>
		            	<option value=3>Double Room</option>
		            	<option value=4>Single Room</option>
        			</select>	
        		</td>
        	</tr>
        	<tr>
        		<td align=right>숙박인원</td><td><input type=number id=human></td>
        	</tr>
        	<tr>
        		<td align=right>최대인원</td><td><input type=number id=max_howmany></td>
        	</tr>
        	<tr>
        		<td align=right>숙박기간</td><td><input type=date id="period3">~<input type=date id="period4"></td>
        	</tr>
        	<tr>
        		<td align=right>총숙박비</td><td><input type=number id="total"></td>
        	</tr>
        	<tr>
        		<td align=right>예약자명</td><td><input type=text id=booker></td>
        	</tr>
        	<tr>
        		<td align=right>모바일</td><td><input type=text id=mobile></td>
        	</tr>
        	<tr>
        		<td colspan=2 align=center>
        			<input type=button value='예약완료' id=btnBook>&nbsp;
        			<input type=button value='비우기' id=btnDelete>&nbsp;
        			<input type=button value='예약취소' id=btnEmpty>&nbsp;       			 
        		</td>     		
        	</tr>
        	
        	</table><td> 
        	<table>
        	<tr>예약된 객실<br>
        	<select id=selBooked style='width:230px;' size=15></select>
        	</tr>
        	</table>
        </td><tr>  
      </table>
      	<td>
      	</td>
      </body>
      <script src='https://code.jquery.com/jquery-3.5.0.js'></script>
      <script>
      $(document)
      .ready(function(){    
    	  $.post("http://localhost:8080/getRoomList",{},function(result){
    		  console.log(result);
    		  $.each(result,function(ndx,value){
    			  str='<option value="'+value['roomcode']+'">'+value['roomname']+','+
    			  	  value['typename']+','+value['howmany']+','+value['howmuch']+'</option>';
    			  	  $('#selRoom').append(str);
    		  });
    	  },'json');             
        	  $.post("http://localhost:8080/getBookingList",{},function(result){
        		  console.log(result);
        		  $.each(result,function(ndx,value){
        			  str='<option value="'+value['bookcode']+'">'+value['roomcode']+','+
        			  	  value['person']+','+value['checkin']+','+value['checkout']+','+value['name']+','+['mobile']+'</option>';
        			  	  $('#selBooked').append(str);
        		  });
        	  },'json');
      })
      
      .on('click','#look',function(){
    	  $.post("http://localhost:8080/getBookedList",
       		   {checkin:('#checkin').val(),checkout:('#checkout').val()},
       		   function(result){
           		  console.log(result);
           		  $.each(result,function(ndx,value){
           			  str='<option value="'+value['bookcode']+'">'+value['roomcode']+','+
           			  	  value['person']+','+value['checkin']+','+value['checkout']+','+value['name']+','+['mobile']+'</option>';
           			  	  $('#selBooked').append(str);
           		  });
           	  },'json');
         })
       
      .on('click','#selRoom option',function(){
    	let str=$(this).text();
    	let ar=str.split(',');
    	 $("#txtName").val(ar[0]);
    	 $('#selType option:contains("'+ar[1]+'")').prop('selected','selected');
    	 $("#human").val(ar[2]);
    	 $("#total").val(ar[3]);
    	 var typecode=$('#select_type').val();
    	 if(typecode==1){
 			$('#select_room1').val(1).prop("selected", true);
 		}else if(typecode==2){
 			$('#select_room1').val(2).prop("selected", true);
 		}else if(typecode==3){
 			$('#select_room1').val(3).prop("selected", true);
 		}else if(typecode==4){
 			$('#select_room1').val(4).prop("selected", true);
 		}
    	 let code=$(this).val();
    	 $('#roomcode').val(code);
    	 var period1 = String($("#period1").val());
    	 var period2 = String($("#period2").val());
    	 $("#period3").val(period1);
    	 $("#period4").val(period2);
    	 return false;
      })
        .on('click','#btnBook',function(){
   		let roomcode=$('#selType').val();
   		let person=$('#human').val();
   		let checkin=$('#period3').val();
   		let checkout=$('#period4').val();
  		let name=$('#booker').val();
   		let mobile=$('#mobile').val();
   		$('#period3','#period4').trigger('change');
   // validation(유효성 검사)
   if(roomcode=='' || person=='' || checkin=='' || checkout=='' || name=='' || mobile=='') {
      alert('누락된 값이 있습니다.');
      return false;
   }  
      $.post('http://localhost:8080/addbooking',
            {roomcode:roomcode,person:person,checkin:checkin,checkout:checkout, name: name,mobile:mobile},
            function(result){
               if(result=='ok') {
                  //location.reload();
               }
            },'text');
   
   })
   
   .on('click','#btnDelete',function(){
	   console.log("btn")
	   $('#roomcode,#selType,#human,#period3,#period4,#booker,#txtName,#mobile,#total,#max_howmany').val('');
   })
   .on('change','#period3','#period4',function(){
	   let checkin=$('#period1').val();
	   let checkout=$('#period2').val();
	   if(checkin=='' || checkout=='')return false;
	   checkin=new Date(checkin);
	   checkout=new Date(checkout);
	   if(checkin > checkout) {
		   alert('check확인'); return false;
	   }
	   let ms=Math.abs(checkout-checkin);
	   let days=Math.ceil(ms/(1000*60*60*24));
	   let total=days*parseInt($('#person').val());
	   $('#total').val(total);
	   return false;
   })
   .on('click','#look',function(){
	   console.log("look")
	   $('#roomcode,#name,#type,#howmany,#howmuch').val();
   })
   .on
      </script> 
</table> 
</body>
</html>