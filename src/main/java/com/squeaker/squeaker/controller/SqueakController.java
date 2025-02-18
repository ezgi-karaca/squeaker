package com.squeaker.squeaker.controller;

import com.squeaker.squeaker.entity.Comment;
import com.squeaker.squeaker.entity.Squeak;
import com.squeaker.squeaker.service.CommentService;
import com.squeaker.squeaker.service.SqueakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/squeak")
public class SqueakController {

    @Autowired
    private SqueakService squeakService;

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Squeak> createSqueak(@RequestBody Squeak squeak) {
        return squeakService.createSqueak(squeak);
    }

    @GetMapping
    public List<Squeak> getAllSqueaks() {
        return squeakService.getAllSqueaks();
    }

    @GetMapping("/user/{userId}/squeak")
    public List<Squeak> getSqueaksByUser(@PathVariable Long userId){
        return squeakService.getSqueaksByUserId(userId);
    }

    @GetMapping("/{squeakId}")
    public Squeak getSqueakById(@PathVariable Long squeakId){
        return squeakService.getSqueakById(squeakId);
    }

    @GetMapping("/{squeakId}/comments")
    public List<Comment> getCommentsBySqueakId(@PathVariable Long squeakId){
        return commentService.getCommentsBySqueakId(squeakId);
    }

    @PutMapping("/{squeakId}")
    public Squeak updateSqueak(@PathVariable Long squeakId, @RequestBody Squeak squeak){
        Squeak existingSqueak = squeakService.getSqueakById(squeakId);

        if(existingSqueak != null) {
            existingSqueak.setContent(squeak.getContent());
            return squeakService.saveSqueak(existingSqueak);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteSqueak(@PathVariable Long id) {
        squeakService.deleteSqueak(id);
    }

}
