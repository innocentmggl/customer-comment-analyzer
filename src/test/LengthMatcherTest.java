package test;

import com.ikhokha.techcheck.matchers.LengthMatcher;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class LengthMatcherTest {

    private LengthMatcher testClass;
    private final String ikhokha = "iKhokha";
    private final String gratitude = "Thank you iKhokha for changing mu life!!";

    @Test
    public void whenGetReportKeyIsCalledThenCorrectKeyIsReturned() throws Exception
    {
        testClass = new LengthMatcher("REPORT_KEY", LengthMatcher.Condition.LessThan, 15);
        assertFalse(testClass.getReportKey().isEmpty());
        assertEquals(testClass.getReportKey(),"REPORT_KEY");
    }

    @Test
    public void whenLessThanCountIsCalledThenCorrectOccurrenceIsReturned() throws Exception
    {
        testClass = new LengthMatcher("REPORT_KEY", LengthMatcher.Condition.LessThan, 15);
        int count = testClass.count(ikhokha);
        int thanks = testClass.count(gratitude);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }

    @Test
    public void whenGreaterThanCountIsCalledThenCorrectOccurrenceIsReturned() throws Exception
    {
        testClass = new LengthMatcher("REPORT_KEY", LengthMatcher.Condition.GreaterThan, 15);
        int count = testClass.count(ikhokha);
        int thanks = testClass.count(gratitude);
        assertEquals(count,0);
        assertEquals(thanks,1);
    }

    @Test
    public void whenEqualCountIsCalledThenCorrectOccurrenceIsReturned() throws Exception
    {
        testClass = new LengthMatcher("REPORT_KEY", LengthMatcher.Condition.Equal, 7);
        int count = testClass.count(ikhokha);
        int thanks = testClass.count(gratitude);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }


    @Test(expected = Exception.class)
    public void whenReportKeyIsEmptyThenExceptionIsThrown() throws Exception {
        testClass = new LengthMatcher("ZERO_LENGTH", LengthMatcher.Condition.LessThan, 0);;
        assertNull(testClass);

        testClass = new LengthMatcher("", LengthMatcher.Condition.GreaterThan, 10);;
        assertNull(testClass);
    }
}
