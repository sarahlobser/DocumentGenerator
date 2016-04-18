<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	${doc.style.fontUrl}
    <link rel="stylesheet" type="text/css" href="${doc.style.fileName}">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<title>Editor</title>
</head>
<body>
	<div class="w3-row">
		<div class="w3-container w3-half">

			<form class="w3-container" action="edit.do" method="POST"
				id="editForm">
				<ul class="w3-navbar">
					<li><input class="w3-input w3-border w3-tiny w3-padding-tiny" type="text" name="insertIndex" value="${doc.length-1}" size="3"></li>
					<li><input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="add"></li>
					<li><input class="w3-input w3-border w3-tiny w3-padding-tiny" type="text" name="grabIndex" value="${doc.length-1}" size="3"></li>
					<li><input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="grab"></li>
				</ul>
				<div class="w3-btn-group w3-center">
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="p"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="h1"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="h2"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="h3"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="h4"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="h5"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="h6"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="ul"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="ol"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="li"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-yellow" type="submit" name="tag" value="Y"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-pink" style="background-color: #ffaacc; color: #ffaacc" type="submit" name="tag" value="P"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-blue" style="background-color: #66eeee; color: #66eeee" type="submit"name="tag" value="B"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-green" style="background-color: #77ff88; color: #77ff88" type="submit" name="tag" value="G"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="comment"> 
					<input class="w3-btn w3-tiny w3-padding-small w3-light-grey w3-hover-white" type="submit" name="tag" value="code"> 
					<input class="w3-btn w3-tiny w3-padding-small" type="submit" name="tag" value="clear">
				</div>
				<textarea class="w3-input w3-border w3-tiny" type="text" rows="8" cols="100" name="snippet" form="editForm">${snippet}</textarea>
			</form>

			<form class="w3-container" action="io.do" method="GET" id="ioForm">
				<ul class="w3-navbar w3-center">
					<li><input class="w3-btn w3-tiny w3-padding-small" type="submit" name="Go" value="Go"></li>
					<li><select name="iotransfer">
							<option value="Save">Save</option>
							<option value="Open">Open</option>
							<option value="Read">Read</option>
							<option value="Write">Write</option>
							<option value="Delete">Delete</option>
							<option value="Clear">Clear History</option>
						</select>
					</li>

					<li><input class="w3-input w3-border-0 w3-tiny w3-padding-tiny" type="text" name="name" value="${doc.name}" size="15"></li>

					<li><select name="chooseFile" form="ioForm">
							<c:forEach var="file" items="${docs}">
								<option value="${file.name}">${file.name}</option>
							</c:forEach>
						</select>
					</li>
					<li><select name="styleOptions" form="ioForm">
							<c:forEach var="style" items="${styles}">
								<option value="${style.name}">${style.name}</option>
							</c:forEach>
						</select>
					</li>
				</ul>
				<textarea class="w3-input w3-border w3-tiny" type="text" rows="14"
					cols="100" name="htmlOutput">
    			<c:forEach var="line" items="${doc.content}">
    				${line}
    			</c:forEach>
    			</textarea>
			</form>
		</div>


		<div class="w3-container w3-half">
			<c:forEach var="line" items="${doc.content}">
				${line}
			</c:forEach>
		</div>
	</div>
	<div class="w3-section w3-teal w3-padding-medium">
		<ul class="w3-navbar">
			<c:forEach var="url" items="${writtenHistory}">
				<li><a href="http://52.38.163.64:8080/DocGen/${url}">${url}</a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="w3-container w3-padding-jumbo">
		<iframe src="http://52.38.163.64:8080/DocGen/${openFile}"
			style="width: 100%; height: 600px"></iframe>
	</div>
</body>
</html>