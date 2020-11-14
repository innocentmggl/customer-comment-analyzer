package com.ikhokha.techcheck.matchers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContainsMatcher implements PatternMatcher {

    private String key;
    private String regex;

    public ContainsMatcher(String reportKey, String regex) throws Exception {
        if(regex.isEmpty() || reportKey.isEmpty()){
            throw new Exception("ContainsMatcher: Report key or regex cannot be empty");
        }
        this.key = reportKey;
        this.regex = regex;
    }

    @Override
    public String getReportKey() {
        return key;
    }

    @Override
    public int count(String comment) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(comment);
        return matcher.find() ? 1 : 0;
    }
}
