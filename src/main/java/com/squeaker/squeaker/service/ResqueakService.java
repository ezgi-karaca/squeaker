package com.squeaker.squeaker.service;


import com.squeaker.squeaker.entity.Resqueak;
import com.squeaker.squeaker.repository.ResqueakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResqueakService {

    private final ResqueakRepository resqueakRepository;

    @Autowired
    public ResqueakService(ResqueakRepository resqueakRepository) {
        this.resqueakRepository = resqueakRepository;
    }

    public Resqueak saveResqueak(Resqueak resqueak){
        return resqueakRepository.save(resqueak);
    }

    public void deleteResqueak(Long id){
        resqueakRepository.deleteById(id);
    }
}
