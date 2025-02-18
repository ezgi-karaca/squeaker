package com.squeaker.squeaker.service;


import com.squeaker.squeaker.entity.Like;
import com.squeaker.squeaker.entity.Squeak;
import com.squeaker.squeaker.entity.User;
import com.squeaker.squeaker.repository.LikeRepository;
import com.squeaker.squeaker.repository.SqueakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    private final SqueakRepository squeakRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository, SqueakRepository squeakRepository) {
        this.likeRepository = likeRepository;
        this.squeakRepository = squeakRepository;
    }

    public List<Like> getAllLikes(){
        return likeRepository.findAll();
    }

    public Like getLikeById(Long id){
        return likeRepository.findById(id).orElse(null);
    }

    public Like saveLike(User user, Long squeakId) {
        Squeak squeak = squeakRepository.findById(squeakId).orElseThrow(() -> new RuntimeException("Squeak not found"));
        Like like = new Like(user, squeak);
        return likeRepository.save(like);
    }

    public void deleteLike(User user, Long squeakId) {
        Like like = likeRepository.findByUserAndSqueak(user, squeakRepository.findById(squeakId).orElseThrow(() -> new RuntimeException("Squeak not found")))
                .orElseThrow(() -> new RuntimeException("Like not found"));

        likeRepository.delete(like);
    }


}
