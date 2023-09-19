<%-- 
    Document   : chutro
    Created on : Jul 31, 2023, 12:45:22 PM
    Author     : ThanhThuyen
--%>

<%@page import="com.ntt.pojo.BaiViet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Objects" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="<c:url value="/css/style.css" />" rel="stylesheet" />
<!DOCTYPE html>

<html>

    <section class="body-trangcanhan">
        <div class="trangcanhan">
            <div class="trangcanhan-col1">
                <div class="catngang">
                    <h5 style="color: white; padding: 10px;">THÔNG TIN CÁ NHÂN</h5>
                </div>
                <div class="thongtincanhan">
                    <center>
                        <img src="${taikhoan.avatar}" class="rounded-circle" style="width:50%;" alt="${pageContext.request.userPrincipal.name}" />
                        <p><c:if test="${taikhoan.idLoaiTaiKhoan.id == 2}">CHỦ TRO</c:if></p>
                        <p><c:if test="${taikhoan.idLoaiTaiKhoan.id == 3}">KHÁCH HÀNG</c:if></p>
                        <p>${taikhoan.tenNguoiDung}</p>
                        <p>${taikhoan.sdt}</p>
                        <div class="canhanhoa">

                            <button class="btn btn-success"><a style="color:white; text-decoration: none" href="<c:url value ="/capnhattaikhoan"/> ">Cập nhật</a></button>
                            <button class="btn btn-success"><a style="color:white; text-decoration: none" href="<c:url value ="/doimatkhau"/> ">Đổi mật khẩu</a></button>
                        </div>
                    </center>
                </div>
            </div>
            <div class="trangcanhan-col2">
                <div class="catngang">
                    <h5 style="color: white; padding: 10px;">TIN ĐĂNG</h5>
                </div>
                <div>
                    <c:forEach items="${baiviet}" var="p">
                        <c:if test="${p.loaiTrangThai.id==1}">
                            <c:if test="${p.loaiBaiViet.id==1}">
                                <c:url value="/canhan/${p.id}" var="apiDe"/>

                                <div class="tincanhan">
                                    <div class="tincanhan-anh">
                                        <img src="${p.hinhAnh}"/>
                                    </div>
                                    <div class="tincanhan-noidung">
                                        <table style="width:100%">
                                            <c:url value="/thtin_bviet" var="bvietAction">
                                                <c:param name="baivietId" value="${p.id}" />  
                                            </c:url>

                                            <a style="text-decoration: none; font-size: 18px" href="${bvietAction}" >${p.tenBaiViet}</a>
                                            <tr>
                                                <th>Khu vực:</th>
                                                <td>${p.phamViCanTim}</td>
                                            </tr>
                                            <tr>
                                                <th>Giá thuê:</th>
                                                <td>${p.giaThue}/tháng</td>
                                            </tr>
                                            <tr>
                                                <th>Ngày đăng:</th>
                                                <td>${p.ngayDang}</td>
                                            </tr>

                                            <tr>
                                                <th></th>
                                                <td>   
                                                    <c:url value="/capnhat" var="bvietAction">
                                                        <c:param name="baivietId" value="${p.id}" />  
                                                    </c:url>
                                                    <a href="${bvietAction}" class="btn btn-success" style="vertical-align:middle"> Cập nhật bài viết</a>
                                                </td>
                                                <td>   
                                                    <c:url value="/canhan_xoa" var="actionXoa">
                                                        <c:param name="idBaiVietXoa" value="${p.id}" />  
                                                    </c:url>
                                                    <form:form method="post" action="${actionXoa}">
                                                        <button class="btn btn-danger" type="submit">Xóa</button>
                                                    </form:form>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${p.loaiBaiViet.id==2}">
                                <c:url value="/canhan/${p.id}" var="apiDe"/>
                                <div class="tincanhan">

                                    <div class="tincanhan-noidung">
                                        <table style="width:100%">
                                            <c:url value="/thtin_bviet" var="bvietAction">
                                                <c:param name="baivietId" value="${p.id}" />  
                                            </c:url>

                                            <a style="text-decoration: none" href="${bvietAction}" >${p.tenBaiViet}</a>
                                            <tr>
                                                <th>Khu vực:</th>
                                                <td>${p.phamViCanTim}</td>
                                            </tr>

                                            <tr>
                                                <th>Ngày đăng:</th>
                                                <td>${p.ngayDang}</td>
                                            </tr>
                                            <tr>
                                                <th>Mô tả:</th>
                                                <td>${p.noiDung}</td>
                                            </tr>
                                            <tr>
                                                <th></th>
                                                <td>   
                                                    <c:url value="/capnhat" var="bvietAction">
                                                        <c:param name="baivietId" value="${p.id}" />  
                                                    </c:url>
                                                    <a href="${bvietAction}" class="btn btn-success" style="vertical-align:middle"> Cập nhật bài viết</a>
                                                </td>
                                                <td>   
                                                    <c:url value="/canhan_xoa" var="actionXoa">
                                                        <c:param name="idBaiVietXoa" value="${p.id}" />  
                                                    </c:url>
                                                    <form:form method="post" action="${actionXoa}">
                                                        <button class="btn btn-danger" type="submit">Xóa</button>
                                                    </form:form>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </c:if>

                        </c:if>
                    </c:forEach>
                </div>
                    <div class="catngang">
                        <h5 style="color: white; padding: 10px;">TIN ĐANG CHỜ DUYỆT</h5>
                    </div>

                <div>
                    <c:forEach items="${baiviet}" var="p">
                        <c:if test="${p.loaiTrangThai.id==2}">
                            <c:if test="${p.loaiBaiViet.id==1}">
                                <c:url value="/canhan/${p.id}" var="apiDe"/>

                                <div class="tincanhan">
                                    <div class="tincanhan-anh">
                                        <img src="${p.hinhAnh}"/>
                                    </div>
                                    <div class="tincanhan-noidung">
                                        <table style="width:100%">
                                            <c:url value="/thtin_bviet" var="bvietAction">
                                                <c:param name="baivietId" value="${p.id}" />  
                                            </c:url>

                                            <a href="${bvietAction}" >${p.tenBaiViet}</a>
                                            <tr>
                                                <th>Khu vực:</th>
                                                <td>${p.phamViCanTim}</td>
                                            </tr>
                                            <tr>
                                                <th>Giá thuê:</th>
                                                <td>${p.giaThue}/tháng</td>
                                            </tr>
                                            <tr>
                                                <th>Ngày đăng:</th>
                                                <td>${p.ngayDang}</td>
                                            </tr>

                                            <tr>
                                                <th></th>
                                                <td>   
                                                    <c:url value="/capnhat" var="bvietAction">
                                                        <c:param name="baivietId" value="${p.id}" />  
                                                    </c:url>
                                                    <a href="${bvietAction}" class="btn btn-success" style="vertical-align:middle"> Cập nhật bài viết</a>
                                                </td>
                                                <td>   
                                                    <c:url value="/canhan_xoa" var="actionXoa">
                                                        <c:param name="idBaiVietXoa" value="${p.id}" />  
                                                    </c:url>

                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${p.loaiBaiViet.id==2}">
                                <c:url value="/canhan/${p.id}" var="apiDe"/>
                                <div class="tincanhan">

                                    <div class="tincanhan-noidung">
                                        <table style="width:100%">
                                            <c:url value="/thtin_bviet" var="bvietAction">
                                                <c:param name="baivietId" value="${p.id}" />  
                                            </c:url>

                                            <a href="${bvietAction}" >${p.tenBaiViet}</a>
                                            <tr>
                                                <th>Khu vực:</th>
                                                <td>${p.phamViCanTim}</td>
                                            </tr>

                                            <tr>
                                                <th>Ngày đăng:</th>
                                                <td>${p.ngayDang}</td>
                                            </tr>
                                            <tr>
                                                <th>Mô tả:</th>
                                                <td>${p.noiDung}</td>
                                            </tr>
                                            <tr>
                                                <th></th>
                                                <td>   
                                                    <c:url value="/capnhat" var="bvietAction">
                                                        <c:param name="baivietId" value="${p.id}" />  
                                                    </c:url>
                                                    <a href="${bvietAction}" class="btn btn-success" style="vertical-align:middle"> Cập nhật bài viết</a>
                                                </td>
                                                <td>   
                                                    <c:url value="/canhan_xoa" var="actionXoa">
                                                        <c:param name="idBaiVietXoa" value="${p.id}" />  
                                                    </c:url>
                                                    <form:form method="post" action="${actionXoa}">
                                                        <button class="btn btn-danger" type="submit">Xóa</button>
                                                    </form:form>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </c:if>

                        </c:if>
                    </c:forEach>
                </div>
                <div class="catngang">
                    <h5 style="color: white; padding: 10px;">TIN BỊ TỪ CHỐI</h5>
                </div>

                <div>
                    <c:forEach items="${baiviet}" var="p">
                        <c:if test="${p.loaiTrangThai.id==3}">
                            <c:if test="${p.loaiBaiViet.id==1}">
                                <c:url value="/canhan/${p.id}" var="apiDe"/>

                                <div class="tincanhan">
                                    <div class="tincanhan-anh">
                                        <img src="${p.hinhAnh}"/>
                                    </div>
                                    <div class="tincanhan-noidung">
                                        <table style="width:100%">
                                            <c:url value="/thtin_bviet" var="bvietAction">
                                                <c:param name="baivietId" value="${p.id}" />  
                                            </c:url>

                                            <a href="${bvietAction}" >${p.tenBaiViet}</a>
                                            <tr>
                                                <th>Khu vực:</th>
                                                <td>${p.phamViCanTim}</td>
                                            </tr>
                                            <tr>
                                                <th>Giá thuê:</th>
                                                <td>${p.giaThue}/tháng</td>
                                            </tr>
                                            <tr>
                                                <th>Ngày đăng:</th>
                                                <td>${p.ngayDang}</td>
                                            </tr>

                                            <tr>
                                                <th></th>

                                                <td>   
                                                    <c:url value="/canhan_xoa" var="actionXoa">
                                                        <c:param name="idBaiVietXoa" value="${p.id}" />  
                                                    </c:url>
                                                    <form:form method="post" action="${actionXoa}">
                                                        <button class="btn btn-danger" type="submit">Xóa</button>
                                                    </form:form>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${p.loaiBaiViet.id==2}">
                                <c:url value="/canhan/${p.id}" var="apiDe"/>
                                <div class="tincanhan">

                                    <div class="tincanhan-noidung">
                                        <table style="width:100%">
                                            <c:url value="/thtin_bviet" var="bvietAction">
                                                <c:param name="baivietId" value="${p.id}" />  
                                            </c:url>

                                            <a href="${bvietAction}" >${p.tenBaiViet}</a>
                                            <tr>
                                                <th>Khu vực:</th>
                                                <td>${p.phamViCanTim}</td>
                                            </tr>

                                            <tr>
                                                <th>Ngày đăng:</th>
                                                <td>${p.ngayDang}</td>
                                            </tr>
                                            <tr>
                                                <th>Mô tả:</th>
                                                <td>${p.noiDung}</td>
                                            </tr>
                                            <tr>
                                                <th></th>
                                                <td>   
                                                    <c:url value="/capnhat" var="bvietAction">
                                                        <c:param name="baivietId" value="${p.id}" />  
                                                    </c:url>
                                                    <a href="${bvietAction}" class="btn btn-success" style="vertical-align:middle"> Cập nhật bài viết</a>
                                                </td>
                                                <td>   
                                                    <c:url value="/canhan_xoa" var="actionXoa">
                                                        <c:param name="idBaiVietXoa" value="${p.id}" />  
                                                    </c:url>
                                                    <form:form method="post" action="${actionXoa}">
                                                        <button class="btn btn-danger" type="submit">Xóa</button>
                                                    </form:form>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </c:if>

                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
</html>
<script src="<c:url value="/js/main.js" />"></script>