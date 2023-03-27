package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testEquals() {
        assertFalse(e1.equals(null));
        int anInt = 3;
        assertFalse(e1.equals(anInt));
        assertTrue(e1.equals(e1));
    }

    @Test
    public void testHashCode() {
        assertEquals(13 * e1.getDate().hashCode() + e1.getDescription().hashCode(), e1.hashCode());
        assertEquals(e1.hashCode(), e1.hashCode());
    }
}
