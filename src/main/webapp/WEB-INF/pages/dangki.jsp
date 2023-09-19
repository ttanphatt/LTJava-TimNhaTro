<%-- 
    Document   : dangki
    Created on : Jul 26, 2023, 4:02:50 PM
    Author     : ThanhThuyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Date" %>
<link href="<c:url value="/css/trangchu.css"/>"rel="stylesheet">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>


<c:url value="/dangki" var="action"/>

<body>
    <div class="wrapper">
        <form:form method="post" action="${action}" modelAttribute="user"  enctype="multipart/form-data">
            <h1>ĐĂNG KÍ TÀI KHOẢN</h1>
            <center>
                <c:if test="${not empty errMsg}">
                    <div class="error-message">
                        ${errMsg}
                    </div>
                </c:if>
                <div class="input-box">
                    <form:input type="text" placeholder="Họ và tên" path="tenNguoiDung" />
                </div>
                <div class="input-box">
                    <form:input type="text" placeholder="Email" path="email" />
                </div>
                <div class="input-box">
                    <form:input type="text" placeholder="Số điện thoại" path="sdt" />
                </div>

                <div class="input-box">
                    <form:input type="text" placeholder="Tên tài khoản" path="tenTaiKhoan" />
                </div>
                <div class="input-box">
                    <form:input type="password" placeholder="Mật khẩu" path="matKhau" />           
                </div>
                <div class="input-box">
                    <form:input type="password" placeholder="Xác nhận lại mật khẩu" path="xacNhanMatKhau" />           
                </div>
                <div class="input-box chonloaiTK">
                    <form:select class="role" name="role" id="role" path="idLoaiTaiKhoan">
                        <c:forEach items="${user_role}" var="c">
                            <option class="option" value="${c.id}" selected>${c.tenLoaiTaiKhoan}</option>
                        </c:forEach>
                    </form:select>
                </div> 

                <div class="upload-avatar" id="upload-avatar-div" style="display: none; margin: 0 auto;">
                    <h4>Ảnh trọ</h4>                   
                    <div class="upload-avatar-input" style=" margin: 0 auto; width: 100%; padding: 10px;">
                        <form:input type="file" id="file2" path="file2" placeholder="Upload ảnh phòng trọ"/> 
                    </div>
                </div>

                <div class="upload-avatar">
                    <h4>Avatar</h4>                   

                    <div class="upload-avatar-input">
                        <form:input type="file" id="fileInput" path="file" placeholder="Upload Avatar"/>
                    </div>
                </div>

            </center>



            <script>
                document.getElementById('role').addEventListener('change', function () {
                    var selectedValue = this.value;
                    var uploadAvatarDiv = document.getElementById('upload-avatar-div');
                    if (selectedValue === '2') {
                        uploadAvatarDiv.style.display = 'block';
                    } else {
                        uploadAvatarDiv.style.display = 'none';
                    }
                });
            </script>
            <button class="btn-dangki" type="submit" >Đăng kí tài khoản</button>
            <div  class="register-link">
                <p class="register-link-p">
                    Bạn đã có tài khoản?  <a href="<c:url value ="/dangnhap"/>">  Đăng nhập ngay </a>
                </p>

            </div>
        </form:form>

    </div>

</body>
<script>
    document.querySelector("form").addEventListener("submit", function (e) {
        const fileInput = document.getElementById("fileInput");

        if (fileInput.files.length === 0) {
            e.preventDefault(); // Ngăn chặn gửi yêu cầu nếu không có tệp nào được chọn
            alert("Vui lòng chọn một tệp hình ảnh.");
        }
    });
</script>