package com.ikhokha.techcheck.matchers;

public interface PatternMatcher {
    String getReportKey();
    int count(String comment);
}
