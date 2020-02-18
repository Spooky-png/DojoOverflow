<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Question Profile</title>
</head>
<body>
<h1><c:out value="${question.question}"/></h1>
<h1><c:out value="${question.id}"/></h1>

<c:forEach items="${question.tags}" var="tag">
<h3>Tags: <c:out value="${tag.subject}"/></h3>
</c:forEach>

<c:forEach items="${question.answers}" var="an">
	<p>
		<c:out value="${an.answer}"/>
	</p>
</c:forEach>
<h4>Add your answer:</h4>
	<form action="/overflow/question/${question.id}" method="post">
	    <p>
	        <label>Answer:</label>
	        <input name="answer">
	    </p>
	    <button type="submit">Add</button>
	</form>
</body>
</html>