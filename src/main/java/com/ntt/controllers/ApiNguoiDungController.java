/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.components.JwtService;
import com.ntt.pojo.BaiViet;
import com.ntt.pojo.NguoiDung;
import com.ntt.service.BaiVietService;
import com.ntt.service.NguoiDungService;
import com.ntt.service.TaiKhoanService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admins
 */
@RestController
@RequestMapping("/api")
public class ApiNguoiDungController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private NguoiDungService userService;

    @Autowired
    private TaiKhoanService taikhoanService;
    @Autowired
    private BaiVietService baivietService;

//    @Autowired
//    private TaiKhoanService tkService;
    @PostMapping("/dangnhap/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody NguoiDung user) {
        if (this.userService.authUser(user.getTenTaiKhoan(), user.getMatKhau()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getTenTaiKhoan());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/users/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<NguoiDung> addUser(@RequestParam Map<String, String> params,
            @RequestPart MultipartFile avatar) {
//            , @RequestPart MultipartFile hinhAnh) {
        NguoiDung user = this.userService.addUser(params, avatar);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/test/")
    @CrossOrigin(origins = {"127.0.0.1:5500"})
    public ResponseEntity<String> test(Principal pricipal) {
        return new ResponseEntity<>("SUCCESSFUL", HttpStatus.OK);
    }

    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<NguoiDung> details(Principal user) {
        NguoiDung u = this.userService.getTaiKhoanbyTenTK(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping("/getTTNgDung/{id}")
    @CrossOrigin
    public Object ThTinNgDung(Model model, @RequestParam Map<String, String> params,
            @PathVariable(value = "id") int id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String i = String.valueOf(id);
        NguoiDung b = (NguoiDung) this.userService.getNgDungById(id);
        Object ngdungJson = objectMapper.writeValueAsString(b);
        return new ResponseEntity<>(ngdungJson, HttpStatus.OK);
    }

    @PostMapping("/doimatkhau/")
    @CrossOrigin
    public ResponseEntity<NguoiDung> changePassword(@RequestParam Map<String, String> params) {
        if (this.userService.authUser(params.get("tenTaiKhoan").toString(), params.get("matKhau").toString()) == true) {
            NguoiDung ngdung = userService.doiMatKhau(params);

            return new ResponseEntity<>(ngdung, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/gettaikhoanbytentk/")
    @CrossOrigin
    public ResponseEntity<List<NguoiDung>> list() {
        List<NguoiDung> ngdung = this.userService.getNgDungAll();
        return new ResponseEntity<>(ngdung, HttpStatus.OK);

    }

}
