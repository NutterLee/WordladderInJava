import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidWordTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void valid_word() {
        assertEquals(true,Main.valid_word("code","data"));
    }
}