package test;

import com.ikhokha.techcheck.matchers.ContainsMatcher;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class ContainsMatcherTest {

    private ContainsMatcher testClass;
    private final String question = "How do i view my sales report from a specific card reader?";
    private final String gratitude = "Thank you iKhokha for changing mu life!!";
    private final String spam = "Checkout the new Square Reader for magstripe https://squareup.com/us/en/hardware/reader";

    @Test
    public void whenGetReportKeyIsCalledThenCorrectKeyIsReturned() throws Exception
    {
        testClass = new ContainsMatcher("THE_REPORT_KEY", "[?]");
        assertFalse(testClass.getReportKey().isEmpty());
        assertEquals(testClass.getReportKey(),"THE_REPORT_KEY");
    }

    @Test
    public void whenQuestionsCountIsCalledThenCorrectOccurrenceIsReturned() throws Exception
    {
        testClass = new ContainsMatcher("REPORT_KEY", "[?]");
        int count = testClass.count(question);
        int thanks = testClass.count(gratitude);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }

    @Test
    public void whenSpamCountIsCalledThenCorrectOccurrenceIsReturned() throws Exception
    {
        final String URL_REGEX = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";
        testClass = new ContainsMatcher("REPORT_KEY", URL_REGEX);
        int count = testClass.count(spam);
        int thanks = testClass.count(gratitude);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }

    @Test(expected = Exception.class)
    public void whenReportKeyIsEmptyThenExceptionIsThrown() throws Exception {
        testClass = new ContainsMatcher("", "");
        assertNull(testClass);
    }
}
