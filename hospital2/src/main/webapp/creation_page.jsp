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
		<form style="padding-left: 30px; width: 300px;" method="POST" action="register_worker">
		<h3>Registration</h3>
			  <div class="form-group">
			    <label for="exampleFormControlInput1">Login</label>
			    <input name="login" required type="text" class="form-control" id="exampleFormControlInput1" >
			  	<label for="exampleFormControlInput1">Password</label>
			    <input name="password" required type="text" class="form-control" id="exampleFormControlInput1" >
			  	<label for="exampleFormControlInput1">Name</label>
			    <input name="name" required type="text" class="form-control" id="exampleFormControlInput1" >
			  	<label for="exampleFormControlInput1">Surname</label>
			    <input name="surname" required type="text" class="form-control" id="exampleFormControlInput1" >
			    <label for="exampleFormControlInput1">Role</label> <br>
			  	<select  name ="ROLE" required>
					<option value="DOCTOR">Doctor</option>
					<option value="NURSE">Nurse</option>
				</select> <br> <br>
				<input type="submit" value="register"> 
				<h3>${msg}</h3>
			  </div>
	 	 </form>
</body>
</html>