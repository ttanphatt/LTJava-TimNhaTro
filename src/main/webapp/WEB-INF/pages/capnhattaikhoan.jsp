<%-- 
    Document   : capnhattaikhoan
    Created on : Sep 6, 2023, 1:04:36 AM
    Author     : ThanhThuyen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Date" %>

<link href="<c:url value="/css/style.css"/>"rel="stylesheet">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>


<!DOCTYPE html>



<html>
    <div class="formcapnhat">
        <c:url value="/capnhattaikhoan" var="updateTaiKhoan"/>

        <form:form action="${updateTaiKhoan}" method="POST" modelAttribute="taikhoan"  enctype="multipart/form-data" >

            <form:hidden path="id" />
            <form:hidden path="idLoaiTaiKhoan" />
            <form:hidden path="kiemDuyet" />
            <form:hidden path="matKhau" />
            <center><h5 style="padding: 20px">CẬP NHẬT THÔNG TIN</h5></center>
            <div class="wrapper">
                <c:if test="${not empty errMsg}">
                    <div class="error-message">
                        ${errMsg}
                    </div>
                </c:if>
                <div class="input-box">
                    <p>Email </p>
                    <form:input type="text" id="email" name="email" path="email" placeholder="email"/>
                </div>
                <div class="input-box">
                    <p>SDT </p>
                    <form:input type="text" id="sdt" name="sdt" path="sdt" placeholder="So dien thoai"/>
                </div>

                <div class="input-box">
                    <p>Tên tài khoan</p>
                    <form:input type="text" id="tenTaiKhoan" name="tenTaiKhoan" path="tenTaiKhoan" placeholder="Tên tài khoan"/>
                </div>
                <div class="input-box">
                    <p for="file">Avatar: </p>
                    <div class="input-box input-file">
                        <form:input path="file" type="file" id="imageFile" name="imageFile" onchange="chooseFile(this)"  accept="image/jpg, image/jpeg, image/png"/>
                    </div>
                </div>
                <center>
                    <button class="btn custom-button3" type="sumit">Cập nhật</button>
                </center>
            </div>
        </form:form>
    </div>
</html>
