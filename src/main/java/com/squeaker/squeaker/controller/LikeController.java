package com.squeaker.squeaker.controller;

import com.squeaker.squeaker.entity.Like;
import com.squeaker.squeaker.entity.User;
import com.squeaker.squeaker.service.AuthService;
import com.squeaker.squeaker.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<Like> likes(){
        return likeService.getAllLikes();
    }

    @GetMapping("/{id}")
    public Like getLikeById(@PathVariable Long id){
        return likeService.getLikeById(id);
    }

    @PostMapping("/{squeakId}")
    public ResponseEntity<Like> likeSqueak(@PathVariable Long squeakId) {
        User user = authService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Like savedLike = likeService.saveLike(user, squeakId);
        return ResponseEntity.ok(savedLike);
    }

    @DeleteMapping("/{squeakId}")
    public ResponseEntity<String> dislikeSqueak(@PathVariable Long squeakId) {
        User user = authService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        likeService.deleteLike(user, squeakId);
        return ResponseEntity.ok("Like removed");
    }
}
