<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<form style="padding-left: 30px; width: 400px;" method="POST" action="find_operations">	
		<label for="exampleFormControlInput1">Room</label>
		<input name="room" required pattern="^[ 0-9]+$" type="text" class="form-control" value="${room}" id="exampleFormControlInput1" >
		<label for="exampleFormControlInput1">Name</label>
		<input name="name" required type="text" class="form-control" value="${name}" id="exampleFormControlInput1" >
		<label for="exampleFormControlInput1">Surname</label>
		<input name="surname" required type="text" class="form-control" value="${surname}" id="exampleFormControlInput1" >
		<br> 
        <input type="submit" value="find"> 
    </form><br>
				<form style="padding-left: 30px; width: 400px;" method="POST" action="make_operation_note">
				Operation: <c:out value="${diagnosis.operations}"/> <br> <br>
				<label for="exampleFormControlInput1">Commentary</label>
	    		<textarea name="commentary" required class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea> 
		        <br>
		        <input type="submit" value="Operate">
					<p style="display: none;"><textarea name="room" cols="40" rows="3" style="resize: none; height: 15px;">${room}</textarea></p>
			        <input style="display: none;"type="text" value="${name}" name="name">
			        <input style="display: none;"type="text" value="${surname}" name="surname">
	    	  	</form>
   
</body>
</html>