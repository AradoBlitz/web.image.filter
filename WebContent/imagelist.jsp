<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web Image Filter</title>
</head>
<body>
	<form id="uploadimage"  method="post" action="upload" enctype="multipart/form-data">
		<input id="imagefile"  type="file" name="file"/>
		<input id="submit" value="submit" type="submit"/>
	</form>
	
   

	<c:forEach begin="1" end="${imagesNumber}" step="1" var="first">
	<c:set var="index" value="${first-1}"></c:set>
	<c:set var="item" value="${imageList[index]}"></c:set>
	<c:out value="Image ${item.name}!"></c:out>
	<img alt="" src="image/${first}" height="400" width="600">
	<a href="apply/DiffusionFilter/${first}" >Apply Filter</a>
	</c:forEach>
</body>
</html>