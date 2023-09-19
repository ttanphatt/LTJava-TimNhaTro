<%-- 
    Document   : doimatkhau
    Created on : Sep 4, 2023, 10:41:51 PM
    Author     : Admins
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<link href="<c:url value="/css/style.css"/>"rel="stylesheet">

<html>
    <div class="formdoimatkhau">
        <c:url value="/doimatkhau" var="actionDoiMatKhau">
            <c:param name="idNguoiDung" value="${taikhoan.id}" />
        </c:url>
        <center><h5 style="padding: 20px">THAY ĐỔI MẬT KHẨU</h5></center>
        <div class="wrapper">
            <c:if test="${not empty errMsg}">
                <div class="error-message">
                    ${errMsg}
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <p style="color: red">${error}</p>
            </c:if>

            <c:if test="${not empty success}">
                <p style="color: green">${success}</p>
            </c:if>
            <div class="change-password">


                <form action="${actionDoiMatKhau}" method="post">
                    <div class="input-box1">
                        <label for="matKhauCu">Mật khẩu cũ:</label>
                        <input type="password" id="matKhauCu" name="matKhauCu" required><br><br>
                    </div>

                    <div class="input-box1">
                        <label for="matKhauMoi">Mật khẩu mới:</label>
                        <input type="password" id="matKhauMoi" name="matKhauMoi" required><br><br>
                    </div>

                    <div class="input-box1">
                        <label for="xacNhanMatKhauMoi">Xác nhận mật khẩu mới :</label>
                        <input type="password" id="xacNhanMatKhauMoi" name="xacNhanMatKhauMoi" required><br><br>
                    </div>
                    <center>
                        <input type="submit" class="btn custom-button3" value="Đổi Mật Khẩu">
                    </center>
                </form>

            </div>
        </div>
    </div>
</html>