<%-- 
    Document   : bando
    Created on : Aug 22, 2023, 2:07:58 AM
    Author     : ThanhThuyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="/css/style.css" />" rel="stylesheet" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="<c:url value="/css/style.css" />" rel="stylesheet" />

<!DOCTYPE html>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Map with Geocoder</title>

        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />
        <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>

        <link rel="stylesheet" href="https://unpkg.com/leaflet-control-geocoder@1.13.0/dist/Control.Geocoder.css" />
        <script src="https://unpkg.com/leaflet-control-geocoder@1.13.0/dist/Control.Geocoder.js"></script>
    </head>
    <body>
    <center>
        <div id="search-bar">
            <input class="diadiemtim" type="text" id="search-input" placeholder="Nhập địa chỉ hoặc tên địa điểm">
            <button class="custom-button1" id="search-button">Tìm kiếm ngay</button>
        </div>
    </center>
    <center><div class="map" id="map" style="width: 80%; height: 500px;"></center>
    <script>
        var map = L.map('map').setView([10.7769, 106.7009], 12);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);

        <c:forEach items="${dsBaiViet}" var="baiViet">
            <%--<c:if test="${baiviet.loaiTrangThai.id.toString() eq '1'}">--%>
        var diaChiCt = "<c:out value='${baiViet.diaChiCt}' />";

        L.Control.Geocoder.nominatim().geocode(diaChiCt, function (results) {
            if (results && results.length > 0) {
                var latlng = results[0].center;
                var marker = L.marker(latlng).addTo(map);
                marker.bindPopup("<c:out value='${baiViet.diaChiCt}' />").openPopup();
            } else {
                console.log('Không tìm thấy địa chỉ cho: ' + diaChiCt);
            }
        });
            <%--</c:if>--%>

        </c:forEach>

        var searchInput = document.getElementById('search-input');
        var searchButton = document.getElementById('search-button');

        searchButton.addEventListener('click', function () {
            var query = searchInput.value;

            L.Control.Geocoder.nominatim().geocode(query, function (results) {
                if (results && results.length > 0) {
                    var latlng = results[0].center;
                    map.setView(latlng, 15);
                } else {
                    alert('Không tìm thấy địa điểm.');
                }
            });
        });

        var latlng = results[0].center;
        map.setView(latlng, 12);
    </script>
</div>
</body>
</html>