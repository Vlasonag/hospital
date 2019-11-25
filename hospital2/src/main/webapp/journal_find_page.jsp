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
	<div style="padding-left: 30px;">
		<a style="font-size: 25px;" href="journal_all">All notes</a>
		<a style="font-size: 25px;" href="journal_my">My notes</a>
		<a style="font-size: 25px;" href="journal_find">Find notes</a> <br>
		<form style="width:400px;" method="POST" action="find_notes">
			<label for="exampleFormControlInput1">Room</label>
			<input name="room" required pattern="^[ 0-9]+$" type="text" class="form-control" value="${room}" id="exampleFormControlInput1" >
			<label for="exampleFormControlInput1">Name</label>
			<input name="name" required type="text" class="form-control" value="${name}" id="exampleFormControlInput1" >
			<label for="exampleFormControlInput1">Surname</label>   
			<input name="surname" required type="text" class="form-control" value="${surname}" id="exampleFormControlInput1" >
			<br>
	        <input type="submit" value="find"> <c:out value="${msg }"/>
	    </form>
    </div>
</body>
</html>