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
	<div style="padding-left: 30px; padding-top: 10px;">
	    <c:forEach var="listOfPatients" items="${listOfPatients}">	
		     <form action="discharge_patient">
				<c:out value="${listOfPatients}"/> <input type="submit" value="discharge">
				<input type="text" name="patient_id" style="display: none;" value="${listOfPatients.id}"> <br> <br>
			</form>
		</c:forEach>
	</div>
</body>
</html>