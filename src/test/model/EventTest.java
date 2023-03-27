package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

public class EventTest {
    private Event e1;
    private Event e2;
    private Date d1;
    private Date d2;

    @BeforeEach
    public void setup() {
        d1 = Calendar.getInstance().getTime();
        e1 = new Event("Added new function: x");

        d2 = Calendar.getInstance().getTime();
        e2 = new Event("Cleared history");

    }

    @Test
    public void testEvent() {
        assertEquals("Added new function: x", e1.getDescription());
        assertEquals(d1.toString(), e1.getDate().toString());
        assertEquals("Cleared history", e2.getDescription());
        assertEquals(d2.toString(), e2.getDate().toString());
    }

    @Test
    public void testToString() {
        assertEquals(d1.toString() + "\n" + "Added new function: x", e1.toString());
        assertEquals(d2.toString() + "\n" + "Cleared history", e2.toString());
    }
}
