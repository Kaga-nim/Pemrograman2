<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.Cookie"%>

<!DOCTYPE html>
<html>
<head>
    <title>Validasi</title>
</head>
<body>

<%
    String username = request.getParameter("user");
    String password = request.getParameter("pass");

    if(username.equals("ADMIN") && password.equals("ADMIN"))
    {
        // SESSION
        session.setAttribute("userLogin", username);
        session.setMaxInactiveInterval(60*60*24);

        // COOKIE
        Cookie userCookie = new Cookie("user", username);
        userCookie.setMaxAge(60*60*24);

        response.addCookie(userCookie);
%>

        <h2>LOGIN BERHASIL</h2>

        Username :
        <%=session.getAttribute("userLogin")%>

        <br><br>

        <a href="index.jsp">Kembali</a>

<%
    }
    else
    {
%>

        <h2>LOGIN GAGAL</h2>

        Username / Password salah

        <br><br>

        <a href="index.jsp">Login Lagi</a>

<%
    }
%>

</body>
</html>