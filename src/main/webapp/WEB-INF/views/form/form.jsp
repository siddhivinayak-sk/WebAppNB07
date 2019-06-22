<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Form Test</title>
<style>
.error { color: #ff0000; }
.errorblock { color: #000; background-color: #ffEEEE; border: 3px solid #ff0000; padding: 8px; margin: 16px; }
</style>
</head>
<body>
<s:form method="post" action="add">
<s:errors path="*" element="div" cssClass="errorblock"></s:errors>
	<table>
		<tr>
			<td><s:label path="id">Id</s:label></td>
			<td><s:input path="id"/> <s:errors path="id" cssClass="error"/></td>
		</tr>
		<tr>
			<td><s:label path="name">Name</s:label></td>
			<td><s:input path="name"/></td>
		</tr>
		<tr>
			<td><s:label path="age">Age</s:label></td>
			<td><s:input path="age"/> <s:errors path="age" cssClass="error"/></td>
		</tr>
		<tr>
			<td><s:label path="password">Password</s:label></td>
			<td><s:password path="password"/></td>
		</tr>
		<tr>
			<td><s:label path="adderss">Address</s:label></td>
			<td><s:textarea path="adderss"/></td>
		</tr>
		<tr>
			<td><s:label path="isIndian">Indian ?</s:label></td>
			<td><s:checkbox path="isIndian"/></td>
		</tr>
		<tr>
			<td><s:label path="bookTypes">Book Types</s:label></td>
			<td><s:checkboxes items="${getbooklist}" path="bookTypes"/></td>
		</tr>
		<tr>
			<td><s:label path="gender">Gender</s:label></td>
			<td><s:radiobutton path="gender" value="M" label="Male"/><s:radiobutton path="gender" value="F" label="Female"/></td>
		</tr>
		<tr>
			<td><s:label path="favNumber">Fav Number</s:label></td>
			<td><s:radiobuttons path="favNumber" items="${getFavNumbers}"/></td>
		</tr>
		<tr>
			<td><s:label path="country">Country</s:label></td>
			<td>
				<s:select path="country">
					<s:option value="NONE">--Select--</s:option>
					<s:options items="${getCountryList}"/>
				</s:select>
			</td>
		</tr>
		<tr>
			<td><s:label path="states">States</s:label></td>
			<td>
				<s:select path="states" items="${getStateList}" multiple="true"/>
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="Save"/></td>
		</tr>
	</table>
	<s:hidden path="hiddenName" />
</s:form>

<hr/>
<div>
	<p>Id: ${id}</p>
	<p>Name: ${name}</p>
	<p>Age: ${age}</p>
	<p>Password: ${password}</p>
	<p>Address: ${adderss}</p>
	<p>IsIndian: ${isIndian}</p>
	<p>Book Types: ${blist}</p>
	<p>Gender: ${gender}</p>
	<p>Fav Number: ${favNumber}</p>
	<p>Country: ${country}</p>
	<p>States: ${states}</p>
	<p>Hidden Name: ${hiddenName}</p>
</div>

<c:url value="upload" var="uploadfile"></c:url>
<p><a href="${uploadfile}">Upload File</a></p>
</body>
</html>