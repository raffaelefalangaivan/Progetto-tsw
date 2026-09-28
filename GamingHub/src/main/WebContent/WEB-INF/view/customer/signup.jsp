<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
</head>
<body>

<h1>Registrazione</h1>

<form action="${pageContext.request.contextPath}/register"
      method="post">

    <label>Username</label>
    <input type="text" name="username" required>                                  
    <br><br>

    <label>Email</label>
    <input type="email" name="email" required>
    <br><br>

    <label>Password</label>
    <input type="password" name="password" required>                                  
    <br><br>

    <button type="submit">
        Registrati
    </button>

</form>

</body>
</html>