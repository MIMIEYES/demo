package com.pinyin;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by Pierreluo on 2017/11/29.
 */
public class Pinyin4jTest {
    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        if(args != null && args.length > 0) {
            System.out.println("转换后的内容:\n\n" + hanyu2pinyin(args[0]) + "\n\n");
        }
    }

    private static String hanyu2pinyin(String hanyu) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        String pinyin = PinyinHelper.toHanyuPinyinString(hanyu, outputFormat, " ");
        return pinyin;
    }
}
