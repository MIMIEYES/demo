package com.temp;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created on 2017/11/15.
 */
@Data
@XmlRootElement(name = "head")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseRes {
    private String version;//	版本号	Char(8)	是	1.0.1
    private String instID;//	机构号	Char(30)	是	按0880+商户号填写
    private String trmSeqNum;//	终端流水号	Char(30)	是
    private String tranDate;//	交易日期	Char(8)	是
    private String tranTime;//	交易时间	Char(6)	是
    private String tradeCode;//	交易码	Char(30)	是	Rsp+请求交易码
    private String servName;//	服务名	char（200）
    private String reserve1;//	保留字段1	Char（256）		备用1
    private String reserve2;//	保留字段2	Char(256)		备用2
    private String reserve3;//	保留字段3	Char(256)		备用3

    public boolean isFailure() {
        return "ERR000".equalsIgnoreCase(getTradeCode());
    }
}
