package com.mimieye;

import com.google.common.base.Splitter;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pierreluo on 2017/4/25.
 */
public class SplitTest {

    public static void main(String[] args) {
        String str = "id=777290058135880&merCatCode=5811&name=商户名称&termId=49000020";
        String str1 = "signature=Ip8C0x5M2Gdb746e4K9B4jaFhVV12lwLdRF3uYjlRCJUmfAh/D8yiABQvWsm8xOQKsOF38iaTl0fZ7/NfCmkZZld75YPrD0YSumrZED0edEBxFIcDo0Ewk5v/lqKNKfsufsQ+N49ZpbdgpSpXVTzpUazYGUwi3eMlA1JEO3hAXTZsdXc1YEF25nUAu+weEhCOUjKVG6G+7RuSObQJPgGf0+keUmFUAv55UdNV7PY58qGJTJUuqU/V8/yKFsq5ZBfZy2/PQD+JjPW4SLLnjYuiQLgEgCi60Hp9gNqS730xS8QtY8/b+giCE9h9QQFOX5hk/2Y3g+edtS0nqx+e7TuRw==&reqType=0250000903&respMsg=已处理&certId=68759529225&version=1.0.0&respCode=00";
        /*Map<String, String> map = bulidUpMap(str);
        System.out.println(map.toString());*/
        Map<String, String> map1 = Splitter.on("&").withKeyValueSeparator("=").split(str1);
        System.out.println(map1.toString());
    }

    private static Map<String,String> bulidUpMap(String str) {
        // 校验str, 验空 and so on.... -。-
        if(StringUtils.isBlank(str))return null;
        String dealStr = str.substring(1,str.length()-1);
        String[] pairs = dealStr.split("&");
        Map<String, String> result = new HashMap<>();
        String[] realPair = null;
        for(String pair : pairs){
            realPair = pair.split("=");
            result.put(realPair[0],realPair[1]);
        }
        return result;
    }
}
