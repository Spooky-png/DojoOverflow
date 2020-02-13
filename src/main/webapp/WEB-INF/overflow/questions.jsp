<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Questions Dashboard</title>
</head>
<body>
<table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">Question</th>
      <th scope="col">Tags</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${question}" var="question">
    <tr>
      <td><a href="/overflow/question/${question.id}"><c:out value="${question.question}"/></a></td>
      <td><c:out value="${question.tags}"/></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
<a href="/overflow/question/new">New Question</a>
</body>
</html>