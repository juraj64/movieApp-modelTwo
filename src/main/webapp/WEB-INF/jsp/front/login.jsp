<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<html>
<head>
<title>Login</title>
</head>
<body>
	<p><font size="5" color="saddlebrown">MovieApp-modelTwo</font></p>
	<p><font color="red">${errorMessage}</font></p>
	<form action="front" method="POST">
		Input Password : <input name="password" type="password" />
		<input type="submit" />
    </form>
</body>
</html>