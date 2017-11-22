package com.temp;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created on 2017/11/15.
 */
@Data
@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
public class F635Res extends  BaseRes{
    private String busiNum;//	商户号	Char(4)
    private String memNum;//	会员号	Char(12)
    private String transSeqNo;//	平台流水号	Char（30）
    private String transDate;//	交易日期	Char（8）
    private String cardType;//	客户账号类型	Char（1）		D-借记卡；C-贷记卡
    private String messageId;//	动态标识号	Char(32)
    private String filed4;//	备用字段4	Char(40)
    private String filed5;//	备用字段5	Char(40)
    private String filed6;//	备用字段6	Char(40)

}
