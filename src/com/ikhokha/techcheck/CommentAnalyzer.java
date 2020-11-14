package com.ikhokha.techcheck;

import com.ikhokha.techcheck.matchers.PatternMatcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class CommentAnalyzer implements Callable {

	private File file;
	private List<PatternMatcher> matchers;

	public CommentAnalyzer(File file, List<PatternMatcher> matchers) {
		this.file = file;
		this.matchers = matchers;
	}

	/**
	 * This method increments a counter by 1 for a match type on the countMap. Uninitialized keys will be set to 1
	 * @param countMap the map that keeps track of counts
	 * @param occurrenceCount number of occurrences in line
	 * @param key the key for the value to increment
	 */
	private void incOccurrence(Map<String, Integer> countMap, int occurrenceCount, String key) {
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + occurrenceCount);
	}

	@Override
	public Object call() throws Exception {

		Map<String, Integer> resultsMap = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			String line = null;
			while ((line = reader.readLine()) != null) {
				for (PatternMatcher matcher : matchers){
					int count = matcher.count(line);
					if (count > 0) {
						incOccurrence(resultsMap, count, matcher.getReportKey());
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Report file no longer exist: "+ file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("IO Error processing file: "+ file.getAbsolutePath());
		}
		return resultsMap;
	}
}
