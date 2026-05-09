<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <h2>FORM LOGIN</h2>

    <form action="validasi.jsp" method="post">
        Username :
        <input type="text" name="user">
        <br><br>

        Password :
        <input type="password" name="pass">
        <br><br>

        <input type="submit" value="LOGIN">
    </form>

</body>
</html>