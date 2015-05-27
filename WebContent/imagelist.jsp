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
	
   

	
	<table width="600" border="3">
	<c:forEach begin="1" end="${imagesNumber}" step="1" var="first">
		
		<tr>
			<td>
				<c:set var="index" value="${first-1}"></c:set>
				<c:set var="item" value="${imageList[index]}"></c:set>
				<c:out value="Image ${item.name}!"></c:out>
			</td>
			<td rowspan="2">
				<img alt="" src="image/${first}" height="400" width="600">
			</td>
		</tr>
		<tr>	
			<td>	
				<a href="apply/DiffusionFilter/${first}" >Apply Filter</a>
			</td>
			
		</tr>
		
	</c:forEach>
</table>
</body>
</html>