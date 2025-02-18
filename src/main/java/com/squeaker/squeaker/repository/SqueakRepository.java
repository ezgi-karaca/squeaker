package com.squeaker.squeaker.repository;

import com.squeaker.squeaker.entity.Squeak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqueakRepository extends JpaRepository<Squeak, Long> {
    List<Squeak> findByUserId(Long userId);

}
