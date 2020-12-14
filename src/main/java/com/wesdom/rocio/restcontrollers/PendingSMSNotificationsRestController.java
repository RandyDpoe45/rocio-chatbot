package com.wesdom.rocio.restcontrollers;

import com.wesdom.rocio.database.repositories.PendingSMSNotificationRepository;
import com.wesdom.rocio.integration.Interface.SMSService;
import com.wesdom.rocio.model.PendingSMSNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("rocio/v1/pendingSMSNotifications")
public class PendingSMSNotificationsRestController {

    @Autowired
    private PendingSMSNotificationRepository pendingSMSNotificationRepository;

    @Autowired
    private SMSService smsService;

    @GetMapping
    public Page<PendingSMSNotification> getAll(Map<String,String> queryParams){
        return pendingSMSNotificationRepository.getAll(queryParams);
    }

    @PutMapping("send/{ids}")
    public List<PendingSMSNotification> sendNotifications(@PathVariable String ids){
        Page<PendingSMSNotification> pendingSMSNotifications = null;
        Map<String,String> queryParams = new HashMap<>();
        if(ids.contains("-1")){
            pendingSMSNotifications = pendingSMSNotificationRepository.getAll(queryParams);
        }else{
            queryParams.put("id",ids);
            pendingSMSNotifications = pendingSMSNotificationRepository.getAll(queryParams);
        }
        return smsService.sendPendingNotifications(pendingSMSNotifications);
    }
}
