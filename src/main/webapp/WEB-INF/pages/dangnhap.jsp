<%-- 
    Document   : trangchu
    Created on : Jul 26, 2023, 12:46:58 PM
    Author     : ThanhThuyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value="/css/trangchu.css"/>"rel="stylesheet">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>


<c:url value="/dangnhap" var="action"/>



<body>

    <div class="wrapper">
        <c:if test="${param.error!=null}">
            <div class="alert alert-danger" >ĐANG CÓ MỘT VÀI SỰ CỐ XẢY RA! VUI LÒNG QUAY TRỞ LẠI SAU!!! </div>
        </c:if>

        <form method="post" action="${action}">
            <h1>ĐĂNG NHẬP</h1>
            <center>
                <c:if test="${param.accessDenied != null}">
                    <!--<div>Bạn không có quyền truy cập vào trang này</div>-->
                    <center><div style="margin-top: 15px; color: #45FFCA ">Tài khoản của bạn đang đợi được duyệt!</div></center>
                    </c:if>
                <div class="input-box">
                    <input type="text" name="username" placeholder="Tên tài khoản" required>
                </div>
                <div class="input-box">
                    <input type="password" name="password" placeholder="Mật khẩu" required>
                </div>
            </center>                
            <button  class="btn" type="submit" >Đăng nhập</button>

            <div  class="register-link">
                <p class="register-link-p">
                    Bạn chưa có tài khoản?  <a href="<c:url value ="/dangki"/>">  Đăng kí ngay </a>
                </p>

            </div>
        </form>


    </div>

</body>