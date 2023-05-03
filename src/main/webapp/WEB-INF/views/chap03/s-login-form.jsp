<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="/hw/s-login-check" method="POST">

        <h1>로그인 하기 ~ ~ </h1>
        #아이디 : <input type="text" id="id">
        <br>
        #비밀번호 : <input type="text" id="pw">
        
        <button id="login-btn" type="submit">로그인</button>
        
    </form>


</body>
</html>