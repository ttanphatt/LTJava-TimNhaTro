/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ThanhThuyen
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiFollowController {

    @Autowired 
    private FollowService followService;

    @DeleteMapping("/thtin_bviet/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFollowpr(@PathVariable(value = "id") int id) {
        this.followService.deleteFollow(id);
        

    }
}
