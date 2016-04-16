<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Schoolbell' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Rock+Salt' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editor</title>
</head>
<body>
<div>
	<c:forEach var="line" items="${doc.content}">
	${line}
	</c:forEach>
	<%-- <p>${doc.content}</p> --%>
	<form action="edit.do" method="POST" id="editForm">
			<input type="text" name="name" value="${doc.name}">
            <input type ="submit" name="tag" value="last">
            <input type ="submit" name="tag" value="clear">
            <input type ="text" name="insertIndex" value="${doc.length-1}" size="3">
            <input type ="submit" name="tag" value="add">
            <input type ="text" name="grabIndex" value="${doc.length-1}" size="3">
            <input type ="submit" name="tag" value="grab"><br>
            <input type="submit" name="tag" value="txt">
            <input type="submit" name="tag" value="p">
            <input type="submit" name="tag" value="h1">
            <input type="submit" name="tag" value="h2">
            <input type="submit" name="tag" value="h3">
            <input type="submit" name="tag" value="h4">
            <input type="submit" name="tag" value="h5">
            <input type="submit" name="tag" value="h6">
            <input type="submit" name="tag" value="comment">
            <input type="submit" name="tag" value="code">
            
    </form>
        <textarea rows="10" cols="100" name="snippet" form="editForm">${snippet}</textarea>
        
    <form action="io.do" method="GET" id="ioForm">
    	<input type="submit" name="iotransfer" value="Save">
    	<input type="submit" name="iotransfer" value="Open">
    	<input type="text" name="name">
    
    <select name="chooseFile" form="ioForm">
	<option value="test.html">test</option>
    <c:forEach var="file" items="${docs}">
    	<option value="${file.name}">${file.name}</option>
    </c:forEach>
    </select>
    	<input type="submit" name="iotransfer" value="clear history">
    </form>
    
    <textarea rows="20" cols="100" name="htmlOutput" >
    <c:forEach var="line" items="${doc.content}">
    	${line}
    </c:forEach>
    </textarea>
</div>
<div>
<iframe src="http://localhost:8080/CRUD/output/${openFile}" style="width: 90%; height: 300px"></iframe>
</div>    
</body>
</html>