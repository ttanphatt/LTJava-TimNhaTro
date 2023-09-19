<%-- 
    Document   : thtin_bviet
    Created on : Aug 12, 2023, 2:34:35 PM
    Author     : Admins
--%>


<%@page import="com.ntt.pojo.BaiViet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Objects" %>
<link href="<c:url value="/css/style.css" />" rel="stylesheet" />


<c:url value="/thtin_taiKhoan" var="actionTaiKhoan">
    <c:param name="taiKhoanId" value="${taikhoanduyet.id}" />  
</c:url>
<c:url value="/thtin_taiKhoanDele" var="actionTaiKhoanDele">
    <c:param name="taiKhoanId" value="${taikhoanduyet.id}" />  
</c:url>

<section class="chitiettin" >
    <div class="chitiettin-col1">
        <c:if test="${taikhoanduyet.kiemDuyet =='KIEM_DUYET_2'}">
            <input class="btn btn-info"  placeholder="Đã xác nhận" readonly="true"/>
        </c:if>
        <div class="ct-anh">

            <center>
                <img src="${taikhoanduyet.avatar}"" style="width:100%" > 
            </center>
        </div>
        <div class="ndung-chitiet">
            <h4 style="color: tomato; font-weight: bold">${taikhoanduyet.tenNguoiDung}</h4>

            <p>Email: ${taikhoanduyet.email}</p>
            <div class="chitiet-3tt">
                <p>SDT :${taikhoanduyet.sdt}</p>

                <p> ID tài khoản: ${taikhoanduyet.id}</p>
            </div>
            <P>Ảnh nhà trọ</P>
            <div class="ct-anh">

                <center>
                    <img src="${taikhoanduyet.hinhAnh}"" style="width:100%" > 
                </center>
            </div>
            <form:form method="post" action="${actionTaiKhoan}">
                <button class="btn btn-danger" type="submit">Xác nhận</button>
            </form:form>

            <form:form method="post" action="${actionTaiKhoanDele}">
                <button class="btn btn-danger" type="submit">Xóa</button>
            </form:form>



        </div>



        <script>
            window.onload = function () {
                let dates = document.getElementsByClassName("commentDate")
                for (let i = 0; i < dates.length; i++)
                {
                    dates[i].innerText = moment(dates[i].innerText).fromNow()
                }
            }

        </script>
        <script src="<c:url value="/js/main.js"/>"></script>


</section>