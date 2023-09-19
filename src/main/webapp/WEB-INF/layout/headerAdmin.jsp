<%-- 
    Document   : headerAdmin
    Created on : Aug 26, 2023, 1:05:13 PM
    Author     : ThanhThuyen
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class=" navbar navbar-expand-sm navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value ="/" />">PHÒNG TRỌ SINH VIÊN</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand" href="<c:url value ="/admin" />">
            <img src="${taikhoan.avatar}" alt="${pageContext.request.userPrincipal.name}" style="width:50px;" class="rounded-pill"> 
        </a>
        <div class="collapse navbar-collapse" id="mynavbar">

            <ul class="navbar-nav me-auto">
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/dangnhap"/>"> Đăng nhập </a>                       
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/dangki"/>"> Đăng kí </a>                       
                    </li>
                </c:if>

                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li class="nav-item">
                        <a style="color: red" class="nav-link" href="<c:url value ="/admin" />">Xin chào, ${pageContext.request.userPrincipal.name} </a>                       
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/adminmonth"/> ">Thông kê theo tháng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/adminquarter"/> ">Thông kê theo quý</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/adminduyetbai"/> ">Duyệt bài viết</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/adminduyettaikhoan"/> ">Duyệt tài khoản chủ trọ</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value ="/logout" />"> Đăng xuất </a>                       
                    </li>


                </c:if>



            </ul>


        </div>
    </div>
</nav>