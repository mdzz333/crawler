package com.dyw.crawler.util;

import com.dyw.crawler.file.RegularCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * Created by dyw on 2017/9/1.
 */
public class RegularUtils {

    /**
     * 获取 A 标签的正则表达式
     *
     * @param html 匹配的内容
     * @return List结果集
     */
    public static List<String> getAUrl(String html) {
        return match(RegularCollection.AURL_REG, html);
    }

    /**
     * 获取 IMG 标签的正则表达式
     *
     * @param html 匹配的内容html
     * @return List结果集
     */
    public static List<String> getIMGUrl(String html) {
        //获取所有的<img开头的>结尾的字符串
        List<String> imgUrls = match(RegularCollection.IMGURL_REG, html);
        //获取没有http开头的url
        List<String> match1 = match(RegularCollection.IMGSRC_REG1, imgUrls);
        //获取http开头的url
        match1.addAll(match(RegularCollection.IMGSRC_REG, imgUrls));
        return match1;
    }

    /**
     * 获取 A 标签的正则表达式
     *
     * @param html 匹配的内容
     * @return List结果集
     */
    public static List<String> getIMGSrc(String html) {
        return match(RegularCollection.IMGSRC_REG, html);
    }

    /**
     * String匹配正则，封装到list中
     *
     * @param regular 正则表达式
     * @param html    匹配的内容
     * @return 匹配到的结果 List
     */
    public static List<String> match(String regular, String html) {
        Matcher matcher = Pattern.compile(regular).matcher(html);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    /**
     * list匹配正则，封装到list中
     *
     * @param regular 正则表达式
     * @param list    匹配的列表
     * @return 匹配到的结果 List
     */
    public static List<String> match(String regular, List<String> list) {
        List<String> result = new ArrayList<>();
        list.forEach(string -> {
            Matcher matcher = Pattern.compile(regular).matcher(string);
            while (matcher.find()) {
                result.add(matcher.group());
            }
        });
        return result;
    }
}
