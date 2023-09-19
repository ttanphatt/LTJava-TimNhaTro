<%-- 
    Document   : index
    Created on : Jul 25, 2023, 4:05:23 PM
    Author     : ThanhThuyen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="<c:url value="/css/style.css"/>" rel="stylesheet" />

<!DOCTYPE html>
<html>
    <body>

        <c:if test="${pageContext.request.userPrincipal.name != null}">

            <div class="input-timkiem" style="text-align: center">
                <c:url value="/" var="action"/>
                <c:if test="${not empty error}">
                    <p class="error">${error}</p>
                </c:if>
                <form action="${action}" method="GET">
                    <input  class="find custom-input" type="text" name="address" placeholder="Nhập địa chỉ...">
                    <input class="find custom-input" type="text" name="price" placeholder="Nhập giá tiền...">
                    <input class="find custom-input" type="text" name="soNguoi" placeholder="Nhập số người...">
                    <button class="find custom-button" type="submit">Tìm kiếm</button>
                </form>
            </div>
        </c:if>
    <center>
        <c:if test="${pages > 1}">
            <ul class="pagination mt-1">
                <c:url value="/" var="pageUrl">
                    <c:param name="page" value="0" /> 
                </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">Tất cả</a></li>
                    <c:forEach begin="1" end="${pages}" var="i">
                        <c:url value="/" var="pageAction">
                            <c:param name="page" value="${i}" /> 
                        </c:url>
                    <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                    </c:forEach>

            </ul>
        </c:if>
    </center>
    <div class="bangtin">
        <c:forEach items="${baiviet}" var="t" >
            <c:if test="${t.loaiTrangThai.id==1}">
                <div class="bviet">
                    <div class="bviet_anh">
                        <c:if test="${t.loaiBaiViet.id==1}">
                            <img src="${t.hinhAnh}"/>
                        </c:if>
                        <c:if test="${t.loaiBaiViet.id==2}">
                            <img src="https://res.cloudinary.com/dpp5kyfae/image/upload/v1694280790/timtro2_k6dbqd.jpg"/>
                        </c:if>
                    </div>
                    <div class="bviet_ndung">
                        <table class="table_bv" style="width:100%">
                            <c:url value="/thtin_bviet" var="bvietAction">
                                <c:param name="baivietId" value="${t.id}" />  
                            </c:url>
                            <c:if test="${t.loaiBaiViet.id==1}">
                                <a  href="${bvietAction}"><h5 class="text-danger">TIN CHO THUÊ</h5>${t.tenBaiViet}</a>
                            </c:if>
                            <c:if test="${t.loaiBaiViet.id==2}">
                                <a  href="${bvietAction}"><h5 class="text-warning">TIN TÌM TRỌ</h5>${t.tenBaiViet}</a>
                            </c:if>
                            <tr>
                                <th>Khu vực:</th>
                                <td>${t.phamViCanTim}</td>
                            </tr>
                            <tr>
                                <th>Giá thuê:</th>
                                <td>${t.giaThue}/tháng</td>
                            </tr>
                            <tr>
                                <th>Diện tích:</th>
                                <td>${t.dienTich}m2</td>
                            </tr>

                        </table>
                        <c:url value="/thtin_bviet" var="bvietAction">
                            <c:param name="baivietId" value="${t.id}" />  
                        </c:url>
                        <div class="groupbtn"><a href="${bvietAction}"> Đọc thêm</a></div>

                    </div>
                </div>

            </c:if>

        </c:forEach>


    </div>
    <button id="goToTopBtn" class="go-to-top-button" onclick="scrollToTop()">
        <i class="fa-solid fa-up-long">

        </i></button>

</body>
</html>
<script src="<c:url value="/js/main.js"/>"></script>
