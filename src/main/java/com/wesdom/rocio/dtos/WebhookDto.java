/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wesdom.rocio.dtos;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 *
 * @author randy
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class WebhookDto {
    
    private String bot_id;
    private String channel;
    private String user_id;
    private String module_id;
    private String message;
    private Boolean blocked_input;
    private Object suggested_replies;
    private Object cards;
}
