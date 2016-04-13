package datastructures.hash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 4/11/2016.
 */
public class SeparateChainingHashSTTest {
    SeparateChainingHashST st;

    @Before
    public void setUp() throws Exception {
        st = new SeparateChainingHashST<String, Integer>();

    }

    @Test
    public void doTest() throws Exception {
        String test = "S E A R C H E X A M P L E";
//        String test = "S E A R";

        String[] keys = test.split(" ");
        int N = keys.length;
        for (int i = 0; i < N; i++)
            st.put(keys[i], keys[i].hashCode());

        assertEquals("Hash failed", 10, st.size());
        assertEquals("Hash failed", true, st.contains("L"));
        assertEquals("Hash failed", false, st.contains("N"));

        assertEquals("Hash failed", 65, st.get("A"));
        assertEquals("Hash failed", 88, st.get("X"));
        st.delete("A");
        assertEquals("Hash failed", null, st.get("A"));
    }
}