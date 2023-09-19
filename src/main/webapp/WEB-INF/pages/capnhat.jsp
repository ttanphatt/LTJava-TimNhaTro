<%-- 
Document   : thtin_bviet
    Created on : Aug 12, 2023, 2:34:35 PM
    Author     : Admins
--%>


<%@page import="java.awt.image.BufferedImage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.io.*, java.net.URL, javax.imageio.ImageIO" %>

<link href="<c:url value="/css/style.css" />" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>

<c:url value="/capnhat" var="updateAction">
</c:url>
<html>
    <center><h3 style="margin: 20px 0; color: #005555;">CẬP NHẬT BÀI VIẾT</h3></center>


    <section class="chitiettin" >
        <div class="chitiettin-col1">
            <div class="ct-anh">
                <center>
                    <img src="${BaiViet.hinhAnh}"  style="width:100%" > 
                </center>
            </div>
            <div class="ndung-chitiet">
                <form:form method="post" action="${updateAction}" var="p" modelAttribute="BaiViet" enctype="multipart/form-data" >
                    <form:hidden path="id" />
                    <form:hidden path="loaiBaiViet.id"/>
                    <form:hidden path="idNguoiDung.id"/>

                    <div class="dangbai-tinnhaplieu">
                        <div class="input-bigsize">
                            <div style="display: flex">
                                <p>Loại bài viết:  </p>    
                                <p style="text-indent: 10px"> ${BaiViet.loaiBaiViet.tenLoaiBaiViet}</p>
                            </div>
                            <div style="display: flex">
                                <p>Mã tin:  </p>    
                                <p style="text-indent: 10px"> #${BaiViet.id}</p>
                            </div>
                        </div>

                        <div class="input-bigsize">
                            <p>Tiêu đề bài đăng </p>
                            <form:input type="text" id="tenbv" name="tenbv" path="tenBaiViet" placeholder="Tiêu đề bài đăng"/>
                        </div>
                        <div class="input-bigsize">
                            <p>Ngày đăng: </p>
                            <form:input type="text" id="ngaydangbv" name="ngaydangbv" path="ngayDang" placeholder="Format yyyy/mm/dd (VD: 2023-05-22)" disabled="true"/>
                        </div>
                        <!--BÀI CỦA CHỦ TRỌ-->
                        <c:if test="${BaiViet.idNguoiDung.idLoaiTaiKhoan.id==2}">
                            <div class="input-smallsize">
                                <div class="input-smallsize1">                            
                                    <p>Khu vực(quận/huyện/thành phố)</p>
                                    <form:input type="text" id="khuvucbv" name="khuvucbv" path="phamViCanTim" placeholder="Nhập khu vực (VD: Gò Vấp)"/>
                                </div>

                                <div class="input-smallsize1">
                                    <p>Giá cho thuê </p>
                                    <form:input type="text" id="giathuebv" name="giathuebv" path="giaThue" placeholder="Nhập giá thuê (VD: 3000000)"  /> 
                                </div>
                            </div>
                            <div class="input-smallsize">
                                <div class="input-smallsize2">
                                    <p>Số lượng người </p>
                                    <form:input type="text" id="slnguoibv" name="slnguoibv" path="soNguoi" placeholder="Nhập số người (VD: 3)" /> 
                                </div>

                                <div class="input-smallsize2">
                                    <p>Diện tích phòng (m2) </p>
                                    <form:input type="text" id="dientichbv" name="dientichbv" path="dienTich" placeholder="Nhập diện tích (VD: 30)"/>
                                </div>
                            </div>

                            <div class="input-bigsize">                            
                                <p>Địa chỉ chi tiết: </p>
                                <form:input type="text" id="dchibv" name="dchibv" path="diaChiCt"/>
                            </div>

                            <script>
                                function chooseFile(fileInput) {
                                    if (fileInput.files && fileInput.files[0]) {
                                        var reader = new FileReader();

                                        reader.onload = function (e) {
                                            $('#image').attr('src', e.target.result);
                                        }
                                        reader.readAsDataURL(fileInput.files[0]);
                                    }
                                }
                            </script>

                            <div>
                                <p for="file">Hình ảnh phòng trọ: </p>
                                <form:input path="file" type="file" id="imageFile" name="imageFile" onchange="chooseFile(this)"  accept="image/jpg, image/jpeg, image/png"/>
                            </div>

                        </c:if>

                        <!--BÀI CỦA KHÁCH HÀNG-->
                        <c:if test="${BaiViet.loaiBaiViet.id==2}">
                            <div>                            
                                <p>Phạm vi cần tìm: </p>
                                <form:input type="text" id="khuvucbv" name="khuvucbv" path="phamViCanTim"/>
                            </div>

                            <div class="input-bigsize">
                                <p>Mô tả chi tiết</p>     
                                <form:textarea  type="text" id="motabv" name="motabv"  path="noiDung" placeholder="Mô tả chi tiết"/>
                            </div>
                        </c:if>

                    </div>
                    <button class="btn btn-info " type="sumit">Cập nhật</button>

                </form:form>

<!--                <h4 style="color: tomato; font-weight: bold">${BaiViet.tenBaiViet}</h4>
                <%--<c:if test="${BaiViet.loaiBaiViet.id==1}">--%>
                    <p>Địa chỉ:<h ${BaiViet.diaChiCt}</p>
                    <div class="chitiet-3tt">
                        <p>Giá: ${BaiViet.giaThue}</p>
                        <p>Diện tích: ${BaiViet.dienTich}</p>
                        <p> #${BaiViet.id}</p>
                    </div>
                <%--</c:if>--%>
                <h4>Thông tin mô tả:</h4>
                <p>${BaiViet.noiDung}</p>

                DAC DIEM TIN DANG
                <h4>Đặc điểm tin đăng:</h4>
                <div class="dacdiemtin">
                    <table style="width:100%">
                        <tr>
                            <th>Mã tin:</th>
                            <td>#${BaiViet.id}</td>
                        </tr>
                        <tr>-->
                <%--<c:if test="${BaiViet.loaiBaiViet.id==1}">--%>
                <!--<th>Khu vực cho thuê trọ:</th>-->

                <%--</c:if>--%>
                <%--<c:if test="${BaiViet.loaiBaiViet.id==2}">--%>
                <!--<th>Khu vực cần tìm trọ:</th>-->
                <%--</c:if>--%>
            <!--<td>${BaiViet.phamViCanTim}</td>-->
                <!--                        </tr>
                                        <tr>
                                            <th>Ngày đăng:</th>
                                            <td>${BaiViet.ngayDang}</td>
                                        </tr>
                                        <tr>
                                            <th>Ngày hết hạn:</th>
                                            <td>${BaiViet.ngayDang}</td>
                                        </tr>
                                    </table>
                                </div>-->

            </div>
        </div>

        <div class="chitiettin-col2">
            <div class="ct-thtinngdung">
                <center>
                    <img src="${BaiViet.idNguoiDung.avatar}" class="rounded-circle" style="width: 100px; height: 100px; border-radius: 50px" alt="${pageContext.request.userPrincipal.name}" />
                    <p>${BaiViet.idNguoiDung.tenNguoiDung}</p>
                    <p>${BaiViet.idNguoiDung.sdt}</p>
                </center>
            </div>
            <div class="ct-tinnoibat">

            </div>
        </div>


    </section>
    <script>
        var ngayDangField = document.getElementById("ngaydangbv");

        var currentDate = new Date();

        var formattedDate = currentDate.toISOString().slice(0, 10);

        ngayDangField.value = formattedDate;
    </script>






</html>