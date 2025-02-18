package com.squeaker.squeaker.repository;

import com.squeaker.squeaker.entity.Like;
import com.squeaker.squeaker.entity.Squeak;
import com.squeaker.squeaker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndSqueak(User user, Squeak squeak);
}
