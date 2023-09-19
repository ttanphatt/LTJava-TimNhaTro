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
    <c:forEach items="${tkChuaDuyet}" var="t" >
        <c:if test="${t.kiemDuyet == 'KIEM_DUYET_1'}">
            <div class="bviet">
                <div class="bviet_anh">
                    <img src="${t.avatar}"/>
                </div>
                <div class="bviet_ndung">
                    <table style="width:100%">
                        
                     
                        <tr>
                            <th>Tên tài khoản:</th>
                            <td>${t.tenTaiKhoan}</td>
                        </tr>
                        <tr>
                            <th>Email:</th>
                            <td>${t.email}</td>
                        </tr>
                          <tr>
                            <th></th>
                            <td>   
                                <c:url value="/thtin_taiKhoan" var="taiKhoanAction">
                                    <c:param name="taiKhoanId" value="${t.id}" />  
                                </c:url>
                                <a href="${taiKhoanAction}" class="bt-docthem" style="vertical-align:middle"> <span>Xem thêm </span></a>
                            </td>
                        </tr>
                      
                    </table>
                </div>
            </div>
        </c:if>
    </c:forEach>
</html>