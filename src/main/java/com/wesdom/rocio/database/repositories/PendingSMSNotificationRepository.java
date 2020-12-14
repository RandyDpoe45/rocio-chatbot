package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.PendingSMSNotification;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface PendingSMSNotificationRepository {
    
    PendingSMSNotification get(Long id);
    PendingSMSNotification create(PendingSMSNotification pendingSMSNotification);
    PendingSMSNotification update(Long id, PendingSMSNotification pendingSMSNotification);
    void delete(Long id);
    Page<PendingSMSNotification> getAll(Map<String,String> queryParams);
    void deleteInList(List<PendingSMSNotification> pendingSMSNotifications);
    
}
