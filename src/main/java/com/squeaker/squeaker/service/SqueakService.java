package com.squeaker.squeaker.service;


import com.squeaker.squeaker.entity.Squeak;
import com.squeaker.squeaker.entity.User;
import com.squeaker.squeaker.repository.SqueakRepository;
import com.squeaker.squeaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqueakService {

    private final SqueakRepository squeakRepository;
    private final UserRepository userRepository;

    @Autowired
    public SqueakService(SqueakRepository squeakRepository, UserRepository userRepository) {
        this.squeakRepository = squeakRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Squeak> createSqueak(Squeak squeak) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        squeak.setUser(user);

        Squeak savedSqueak = squeakRepository.save(squeak);

        return ResponseEntity.ok(savedSqueak);
    }

    public List<Squeak> getAllSqueaks() {
        return squeakRepository.findAll();
    }


    public Squeak getSqueakById(Long id){
        return squeakRepository.findById(id).orElse(null);
    }

    public List<Squeak> getSqueaksByUserId(Long userId){
        return squeakRepository.findByUserId(userId);
    }

    public void deleteSqueak(Long id){
        squeakRepository.deleteById(id);
    }


    public Squeak saveSqueak(Squeak existingSqueak) {
        return squeakRepository.save(existingSqueak);
    }
}
