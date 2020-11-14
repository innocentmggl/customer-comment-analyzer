package com.ikhokha.techcheck.matchers;

public class LengthMatcher implements PatternMatcher {

    public static enum Condition {
        GreaterThan,
        LessThan,
        Equal
    }

    private String key;
    private int length;
    private Condition condition;

    public LengthMatcher(String reportKey, Condition condition, int length) throws Exception{
        if(length < 1){
            throw new Exception("LengthMatcher: Condition length cannot be less than 1");
        }else if(reportKey.isEmpty()){
            throw new Exception("LengthMatcher: Report key cannot be empty");
        }
        this.key = reportKey;
        this.length = length;
        this.condition = condition;
    }

    @Override
    public String getReportKey() {
        return this.key;
    }

    @Override
    public int count(String comment) {
        switch (this.condition){
            case Equal:
                return comment.length() == length ? 1 : 0;
            case LessThan:
                return comment.length() < length ? 1 : 0;
            case GreaterThan:
                return comment.length() > length ? 1 : 0;
        }
        return 0;
    }
}
