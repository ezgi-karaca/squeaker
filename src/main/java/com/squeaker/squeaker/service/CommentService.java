package com.squeaker.squeaker.service;


import com.squeaker.squeaker.entity.Comment;
import com.squeaker.squeaker.entity.Squeak;
import com.squeaker.squeaker.entity.User;
import com.squeaker.squeaker.repository.CommentRepository;
import com.squeaker.squeaker.repository.SqueakRepository;
import com.squeaker.squeaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final SqueakRepository squeakRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, SqueakRepository squeakRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.squeakRepository = squeakRepository;
        this.userRepository = userRepository;
    }

    public Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Comment saveCommentForSqueak(Long squeakId, Comment comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        System.out.println("Kullanıcı bulundu: " + user.getUsername());

        comment.setUser(user);

        Squeak squeak = squeakRepository.findById(squeakId)
                .orElseThrow(() -> new RuntimeException("Squeak not found"));
        comment.setSqueak(squeak);

        return commentRepository.save(comment);
    }


    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }

    public List<Comment> getCommentsBySqueakId(Long squeakId){
        return commentRepository.findBySqueakId(squeakId);
    }

    public Comment getCommentById(Long id){
        return commentRepository.findById(id).orElse(null);
    }
}
