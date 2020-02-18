
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
<form action="/overflow/question/new" method="post">
    <p>
        <label>Question:</label>
        <input name="question"/>
    </p>
    <p>
        <label>Tags:</label>
        <input name="tag"/>
    </p>
    <button type="submit">Submit</button>
</form>
</body>
</html>