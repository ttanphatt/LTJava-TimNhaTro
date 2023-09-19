<%-- 
    Document   : adminduyetbai
    Created on : Aug 31, 2023, 2:58:18 AM
    Author     : ThanhThuyen
--%>

<%@page import="com.ntt.pojo.BaiViet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Objects" %>
<link href="<c:url value="/css/style.css"/>" rel="stylesheet" />
<!DOCTYPE html>
<html>
    <c:forEach items="${bvChuaDuyet}" var="t" >
        <c:if test="${t.loaiTrangThai.id==2}">
            <div class="bviet">
                <div class="bviet_anh">
                    <img src="${t.hinhAnh}"/>
                </div>
                <div class="bviet_ndung">
                    <table style="width:100%">
                        <c:url value="/thtin_bviet" var="bvietAction">
                            <c:param name="baivietId" value="${t.id}" />  
                        </c:url>
                        <a href="${bvietAction}" >${t.tenBaiViet}</a>
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
                        <tr>

                            <th></th>
                            <td>   
                                <c:url value="/thtin_bviet" var="bvietAction">
                                    <c:param name="baivietId" value="${t.id}" />  
                                </c:url>
                                <a href="${bvietAction}" class="bt-docthem" style="vertical-align:middle"> <span>Đọc thêm </span></a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </c:if>
    </c:forEach>
</html>