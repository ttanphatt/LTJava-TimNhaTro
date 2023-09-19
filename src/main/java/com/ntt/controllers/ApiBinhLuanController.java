/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.BinhLuan;
import com.ntt.service.BinhLuanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.oops.ObjArray;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * @author ThanhThuyen
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiBinhLuanController {

    @Autowired
    private BinhLuanService binhLuanService;

    @GetMapping("/listBinhLuanByBV/{id}")
    public ResponseEntity<List<Object>> listComment(@PathVariable(value = "id") int id) {
        List<Object> binhluan = this.binhLuanService.getBinhLuanByBV(id);
        return new ResponseEntity<>(binhluan, HttpStatus.OK);
    }

    @DeleteMapping("/thtin_bvietBinhLuan/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBinhLuanwpr(@PathVariable(value = "id") int id) {
        this.binhLuanService.deleteBinhLuan(id);
    }        
    
    @PostMapping("/binhluan/")
    public ResponseEntity<Object> addComment(@RequestBody BinhLuan binhluan){ 
        return new ResponseEntity<>(this.binhLuanService.addOrUpdateBinhLuan(binhluan),
                HttpStatus.CREATED);
    }
    
    @PostMapping("/binhluan/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id, @RequestBody BinhLuan binhluan) {
        BinhLuan c =  this.binhLuanService.getBinhLuanById(id);
        c.setNoiDung(binhluan.getNoiDung());
        return new ResponseEntity<>(this.binhLuanService.addOrUpdateBinhLuan(c),HttpStatus.OK);
    }
    
    @DeleteMapping("/binhluan/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable(value = "id") int id) {
        this.binhLuanService.deleteBinhLuan(id);
    }
    

    @PostMapping("/replyToComment/{parentId}")
    public ResponseEntity<String> replyToComment(
            @PathVariable(value = "parentId") int parentId,
            @RequestBody BinhLuan replyComment) {
        try {
            BinhLuan parentComment = binhLuanService.getBinhLuanById(parentId);

            if (parentComment != null) {
                
                BinhLuan newReply = new BinhLuan();
                newReply.setNoiDung(replyComment.getNoiDung());
                newReply.setHoiDap(parentId); 
                binhLuanService.saveBinhLuan(newReply);

                return ResponseEntity.ok("Trả lời bình luận thành công");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi trả lời bình luận: " + e.getMessage());
        }
    }

}
