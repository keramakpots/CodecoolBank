package Util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    void TestIsgetDateReturnDateObject() {
        assertEquals(java.sql.Date.class, Date.getDate().getClass());
    }

}