<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>updateemployee</title>
</head>
<body>
<form action="ModifyEmployeeServlet" method="post"><!-- -need  to mention methodpost-->
		<center>
			ID: <input type='text' name='id' ><br><!--  -->
			<div>
			</div>
			First Name: <input type='text' name='fname' ><br>
			<div>
			</div>
			Last Name: <input type='text' name='lname' ><br>
			<div>
			</div>
			email: <input type='text' name='email' ><br>
			<div>
			</div>
			Hire Date: <input type='text' name='hdate' ><br>
			<div>
			</div>
			Job ID: <input type='text' name='jobid' ><br>
			<div>
			</div>
			Salary: <input type='text' name='salary' ><br>
			<div>
			</div>
			<input type=submit value='update employee' name='submit'><!-- on submit a Http request  -->
			
		</center>
		</form>
</body>
</html>