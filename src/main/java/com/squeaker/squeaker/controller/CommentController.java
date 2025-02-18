package com.squeaker.squeaker.controller;

import com.squeaker.squeaker.entity.Comment;
import com.squeaker.squeaker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getComments(){
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id){
        return commentService.getCommentById(id);
    }

    @GetMapping("/squeak/{squeakId}")
    public List<Comment> getCommentsBySqueakId(@PathVariable Long squeakId) {
        return commentService.getCommentsBySqueakId(squeakId);
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment){
        return commentService.saveComment(comment);
    }

    @PostMapping("/squeak/{squeakId}")
    public Comment createCommentForSqueak(@PathVariable Long squeakId, @RequestBody Comment comment) {
        return commentService.saveCommentForSqueak(squeakId, comment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment){
        Comment existingComment = commentService.getCommentById(id);

        if(existingComment != null) {
            existingComment.setContent(comment.getContent());
            return commentService.saveComment(existingComment);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id) {
         commentService.deleteComment(id);
         return "Comment deleted succesfully.";
    }



}
