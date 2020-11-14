package com.ikhokha.techcheck.matchers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OccurrenceMatcher implements PatternMatcher {

    private String key;
    private String keyword;

    public OccurrenceMatcher(String reportKey, String searchWord) throws Exception{
        if(searchWord.isEmpty() || reportKey.isEmpty()){
            throw new Exception("OccurrenceMatcher: Report key or search keyword cannot be empty");
        }
        this.key = reportKey;
        this.keyword = searchWord;
    }

    @Override
    public String getReportKey() {
        return this.key;
    }

    @Override
    public int count(String comment) {
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(comment);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
