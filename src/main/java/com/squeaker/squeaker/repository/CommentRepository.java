package com.squeaker.squeaker.repository;

import com.squeaker.squeaker.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.squeak.id = :squeakId")
    List<Comment> findBySqueakId(Long squeakId);

}
