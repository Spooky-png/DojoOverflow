<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Question</title>
</head>
<body>
<h1>What is your question?</h1>
<form:form action="/overflow/question/new" type="POST" modelAttribute="questions">
    <p>
        <form:label path="question">Question:</form:label>
        <form:errors path="question"/>
        <form:input path="question"/>
    </p>   
    <p>
        <form:label path="tags">Tags:</form:label>
        <form:errors path="tags"/>
        <form:input name="t" path="tags"/>
    </p>
    <button type="submit">Submit</button>
</form:form>
</body>
</html>