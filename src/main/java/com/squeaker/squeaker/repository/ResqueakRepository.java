package com.squeaker.squeaker.repository;

import com.squeaker.squeaker.entity.Resqueak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResqueakRepository extends JpaRepository<Resqueak, Long> {

}
