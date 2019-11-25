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
			<a style="font-size: 25px;" href="journal_find">Find notes</a> <br> <br>
			<form action="journal_my_sorted_by_date" method="POST">
				<input value="sort by date ↓/↑" type="submit">
				<input style="display:none;" type="text" value="${state}" name="state">
			</form>
			<br>
			<c:forEach var="listOfNotes" items="${listOfNotes}">
				<div style="width: 400px;" class="alert alert-danger" role="alert">
					Note id = <c:out value="${listOfNotes.id}"/> <br>
					Worker: <c:out value="${listOfNotes.user.surname} ${listOfNotes.user.name}, id = ${listOfNotes.user.id}"/> <br>
					Patient: <c:out value="${listOfNotes.diagnosis.patient.surname} ${listOfNotes.diagnosis.patient.name}, room = ${listOfNotes.diagnosis.patient.room}"/><br>
					Procedures: <c:out value="${listOfNotes.diagnosis.procedures}"/> <br>
					Medicines: <c:out value="${listOfNotes.diagnosis.medicines}"/> <br>
					Operation: <c:out value="${listOfNotes.diagnosis.operations}"/> <br>
					Date/Time: <c:out value="${listOfNotes.date}"/> <br>
					Commentary: <c:out value="${listOfNotes.commentary}"/> <br> <br>
				</div>
			</c:forEach>
			</div>
</body>
</html>