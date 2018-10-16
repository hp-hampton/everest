package com.hup.everest.notification.model;

import lombok.Data;

import java.util.Map;

/**
 * @author hupeng on 2018/10/16
 * @version 1.0
 * @Description
 */
@Data
public class Mail {

    //短信模版内容
    private Map<String, String> context;

    //收件人
    private String[] receivers;

    //抄送人
    private String[] carbonCopys;

    //消息模版Id
    private Long msgId;

    //是否html
    private Boolean isHtml;
}
