package com.squeaker.squeaker.controller;

import com.squeaker.squeaker.entity.Resqueak;
import com.squeaker.squeaker.service.ResqueakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resqueaks")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ResqueakController {
    @Autowired
    private ResqueakService resqueakService;

    @PostMapping
    public Resqueak resqueak(@RequestBody Resqueak resqueak) {
        return resqueakService.saveResqueak(resqueak);
    }

    @DeleteMapping("/{id}")
    public String deleteResqueak(@PathVariable Long id) {
        resqueakService.deleteResqueak(id);
        return "Resqueak deleted successfully";
    }
}
