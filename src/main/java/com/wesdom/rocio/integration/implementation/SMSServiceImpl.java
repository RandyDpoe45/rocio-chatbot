package com.wesdom.rocio.integration.implementation;


import com.wesdom.rocio.database.repositories.PendingSMSNotificationRepository;
import com.wesdom.rocio.integration.Interface.SMSService;
import com.wesdom.rocio.model.PendingSMSNotification;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class SMSServiceImpl implements SMSService {

    private static final String apiKey = "W0Yq0gUgVKGuY6DoL8tFrBOm1EBOxZ";
    private static final String token = "8e12169f01d6fd4c8807c071314e672f";
    private static final String account = "10016737";
    private static final String restApiUrl = "https://api101.hablame.co/api/sms/v2.1/send/";

    @Autowired
    private PendingSMSNotificationRepository pendingSMSNotificationRepository;

    public Boolean sendMessage(String phoneNumber, String message){
        try {

            HttpPost httpPost = new HttpPost(restApiUrl);
            HttpClient httpClient = HttpClients.createDefault();

            List<NameValuePair> body = new ArrayList<>();
            body.add(new BasicNameValuePair("account",account));
            body.add(new BasicNameValuePair("apiKey",apiKey));
            body.add(new BasicNameValuePair("token",token));
            body.add(new BasicNameValuePair("toNumber",phoneNumber));
            body.add(new BasicNameValuePair("sms",message));
            httpPost.setEntity(new UrlEncodedFormEntity(body,"UTF-8"));

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String result = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8.toString());
                JSONObject jsonObject = new JSONObject(result);
                if(!jsonObject.getString("error_description").isEmpty()){
                    pendingSMSNotificationRepository.create(new PendingSMSNotification().setMessage(message).setPhoneNumber(phoneNumber));
                    System.out.println("Error in SMS notification: "+phoneNumber+"\n"+jsonObject.getString("error_description"));
                    return false;
                }
                System.out.println("successfully notificated: "+phoneNumber);
                return true;
            }
            pendingSMSNotificationRepository.create(new PendingSMSNotification().setMessage(message).setPhoneNumber(phoneNumber));
            return false;
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
            pendingSMSNotificationRepository.create(new PendingSMSNotification().setMessage(message).setPhoneNumber(phoneNumber));
        }
        return false;
    }

    @Override
    public List<PendingSMSNotification> sendPendingNotifications(Iterable<PendingSMSNotification> notificationList) {
        List<PendingSMSNotification> successfull = new ArrayList<>();
        for(PendingSMSNotification pendingSMSNotification : notificationList){
            if(sendMessage(pendingSMSNotification.getPhoneNumber(),pendingSMSNotification.getMessage())){
                successfull.add(pendingSMSNotification);
            }
        }
        pendingSMSNotificationRepository.deleteInList(successfull);
        return successfull;
    }
}
