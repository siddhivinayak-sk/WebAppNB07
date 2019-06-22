<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload File</title>
</head>
<body>
<s:form method="post" enctype="multipart/form-data" action="uploadfile" modelAttribute="fileUpload">
	Please select a file to upload:
	<input type="file" name="file"/>
	<input type="submit" value="Upload"/>
</s:form>

<hr/>
<p>File Uploaded: ${file }</p>
</body>
</html>