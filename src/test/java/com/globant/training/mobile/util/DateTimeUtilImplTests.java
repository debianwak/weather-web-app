package com.globant.training.mobile.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DateTimeUtilImplTests {
    
    @Spy
    private SimpleDateFormat simpleDateFormat;
    
    @InjectMocks
    private DateTimeUtilImpl dateTimeImplUtil;
    
    private static final Long UNIX_DATE = 1556897470L;
    private static final String STR_DATE = "05/03/2019";
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException() {
        DateTimeUtil dateTimeUtil = new DateTimeUtilImpl(null);
    }
    
    @Test
    public void testConvertUnixDateToString() {
        /* run */
        String strDate = dateTimeImplUtil.convertUnixDateToString("MM/dd/yyyy", UNIX_DATE);
        
        /* review */
        assertNotNull(strDate);
        assertEquals(STR_DATE, strDate);
    }
    
    @Test
    public void testConvertDateToString() {
        Calendar someDate = Calendar.getInstance();
        someDate.set(Calendar.YEAR, 2019);
        someDate.set(Calendar.MONTH, 4);
        someDate.set(Calendar.DAY_OF_MONTH, 3);
        
        /* run */
        String strDate = dateTimeImplUtil.convertDateToString("MM/dd/yyyy", someDate.getTime());
    
        /* review */
        assertNotNull(strDate);
        assertEquals(STR_DATE, strDate);
    }
}
