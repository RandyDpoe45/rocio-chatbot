/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.restcontrollers;

import com.wesdom.rocio.dtos.WebhookDto;
import java.util.Arrays;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author randy
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping()
public class BotRestController {
    
    /*
        $bot_id = $_POST['bot_id'];
        $user_id = $_POST['user_id'];
        $module_id = $_POST['module_id'];
        $channel = $_POST['channel'];

        // Add a message that will be displayed in the chat
        $message = 'Here u are!'\n;
        $message += 'The temperature is'+$arrayOpenweather["main"]["temp"];

        // Add quick replies to the chat
        $suggestedReplies = ['Next 5 days','Pick a day', 'Return to the beginning'];

        // Add answer’s header
        header('Content-Type: application/json');

        // Build an array $response
        $response = [
                'user_id' => $user_id,
                'bot_id' => $bot_id,
                'module_id' => $module_id,
                'message' => $message,
                'suggested_replies' => $suggestedReplies,
                'blocked_input' => false
            ];
    */
    
    
    
    @GetMapping
    public String getTestString(){
        return "Everything works so far";
    }
    
    @GetMapping("/1")
    public String getTestString1(){
        return "Everything works so far 1";
    }
}
