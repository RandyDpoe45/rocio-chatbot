package com.wesdom.rocio.integration.Interface;

import com.wesdom.rocio.model.PendingSMSNotification;

import java.util.List;

public interface SMSService {

    Boolean sendMessage(String phoneNumber, String message);
    List<PendingSMSNotification> sendPendingNotifications(Iterable<PendingSMSNotification> notificationList);
}
