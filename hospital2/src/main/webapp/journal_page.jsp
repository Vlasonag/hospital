<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="height: 800px;">	
	
	<jsp:include page="header.jsp" />
	<div style="padding-left: 30px;">
		<a style="font-size: 25px;" href="journal_all">All notes</a>
		<a style="font-size: 25px;" href="journal_my">My notes</a>
		<a style="font-size: 25px;" href="journal_find">Find notes</a> <br> <br>
		<form action="journal_all_sorted_by_date" method="POST">
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
		<%--For displaying Previous link except for the 1st page --%>
	    <c:if test="${currentPage != 1}">
	        <td><a href="journal_all_sorted?page=${currentPage - 1}">Previous</a></td>
	    </c:if>
	 
	    <%--For displaying Page numbers. 
	    The when condition does not display a link for the current page--%>
	    <table border="1" cellpadding="5" cellspacing="5">
	    
	        <tr>
	            <c:forEach begin="1" end="${noOfPages}" var="i">
	                <c:choose>
	                    <c:when test="${currentPage eq i}">
	                        <td>${i}</td>
	                    </c:when>
	                    <c:otherwise>
	                        <td><a href="journal_all_sorted?page=${i}">${i}</a></td>
	                    </c:otherwise>
	                </c:choose>
	            </c:forEach>
	        </tr>
	    </table>
	     
	    <%--For displaying Next link --%>
	    <c:if test="${currentPage lt noOfPages}">
	        <td><a href="journal_all_sorted?page=${currentPage + 1}">Next</a></td>
	    </c:if>
	</div>
</body>
</html>