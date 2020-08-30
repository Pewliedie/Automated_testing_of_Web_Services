package com.epam.cdp.kzta2020.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils {

    public static List<String> selectText(String str){
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile("id=\"advert-\\d+\"")
                .matcher(str);
        while (m.find()) {
            matches.add(m.group());
        }
        return matches;
    }
}
