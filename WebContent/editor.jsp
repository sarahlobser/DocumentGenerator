<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	${doc.style.fontUrl}
    <link rel="stylesheet" type="text/css" href="${doc.style.fileName}">
<title>Editor</title>
</head>
<body>
<div>
	<c:forEach var="line" items="${doc.content}">
	${line}
	</c:forEach>
	<form action="edit.do" method="POST" id="editForm">
            <input type ="submit" name="tag" value="clear">
            <input type ="text" name="insertIndex" value="${doc.length-1}" size="3">
            <input type ="submit" name="tag" value="add">
            <input type ="text" name="grabIndex" value="${doc.length-1}" size="3">
            <input type ="submit" name="tag" value="grab"><br>
            
            <input type="submit" name="tag" value="p">
            <input type="submit" name="tag" value="h1">
            <input type="submit" name="tag" value="h2">
            <input type="submit" name="tag" value="h3">
            <input type="submit" name="tag" value="h4">
            <input type="submit" name="tag" value="h5">
            <input type="submit" name="tag" value="h6">
            <input type="submit" name="tag" value="ul">
            <input type="submit" name="tag" value="ol">
            <input type="submit" name="tag" value="li">
            <input style="background-color:yellow;color:yellow" type="submit" name="tag" value="Y">
            <input style="background-color:#ffaacc;color:#ffaacc" type="submit" name="tag" value="P">
            <input style="background-color:#66eeee;color:#66eeee" type="submit" name="tag" value="B">
            <input style="background-color:#77ff88;color:#77ff88" type="submit" name="tag" value="G">
            <input type="submit" name="tag" value="comment">
            <input type="submit" name="tag" value="code">
    </form>
        <textarea rows="10" cols="100" name="snippet" form="editForm">${snippet}</textarea>
        
    <form action="io.do" method="GET" id="ioForm">
    
    <input type="submit" name="go" value="go">
    
    <select name="iotransfer">
    	<option value="Save">save</option>
    	<option value="Open">open</option>
    	<option value="write to file">write</option>
    	<option value="read from file">read</option>
    	<option value="clear history">clear history</option>
    </select>
       	<input type="text" name="name" value="${doc.name}" size="15">
    	
    <select name="chooseFile" form="ioForm">
		<option value="test.html">test</option>
    	<c:forEach var="file" items="${docs}">
    		<option value="${file.name}">${file.name}</option>
    	</c:forEach>
    </select>
    	
    <select name="styleOptions" form="ioForm">
    	<c:forEach var="style" items="${styles}">
    		<option value="${style.name}">${style.name}</option>
    	</c:forEach>
    </select>
    </form>
    
    <textarea rows="20" cols="100" name="htmlOutput" >
    <c:forEach var="line" items="${doc.content}">
    	${line}
    </c:forEach>
    </textarea>
</div>
<div>
<iframe src="http://localhost:8080/DocGen/${openFile}" style="width: 100%; height: 600px"></iframe>
</div>    
</body>
</html>