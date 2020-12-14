/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.restcontrollers;

import com.wesdom.rocio.database.repositories.PendingSMSNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author randy
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping()
public class BotRestController {

    @Autowired
    private PendingSMSNotificationRepository pendingSMSNotificationRepository;
    
    @GetMapping
    public String getTestString(){
        return "Everything works so far";
    }
    
    @GetMapping("/1")
    public String getTestString1(){
        return "Everything works so far 1";
    }
}
