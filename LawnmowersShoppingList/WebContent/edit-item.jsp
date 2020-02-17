<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit an item</title>
</head>
<body>

<form action="editItemServlet" method="post">
		Brand: <input type="text" name="brand" value="${itemToEdit.brand}">
		Color: <input type="text" name="color" value="${itemToEdit.color}">
		<input type="hidden" name="id" value="${itemToEdit.id}"> <input
			type="submit" value="Save Edited Item">
	</form>

</body>
</html>