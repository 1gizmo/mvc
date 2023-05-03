@<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
    
    <!-- 상단 제목 박스 -->
    <header id="headerf">
        <h1 id="title">제목입니다</h1>
        <button id="login">로그인</button>
        <button id="newuser">회원가입</button>
    </header>


    <!-- 메인 박스 -->
    <section id="bodybox">
        <!-- 왼쪽 사이드 메뉴 박스 -->
        <div id="sidemenu">
          <li>
            <ul>자유게시판</ul>
            <ul>게시판</ul>
          </li>
        </div>

        <!-- 게시글 보여주는 메인 박스 -->
        <div id="textlist">



        </div>
        


    </section>


    <script>
        document.getElementById(bodybox).addEventListener(onmouse)


    </script>





</body>
</html>