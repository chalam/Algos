package datastructures.search;

import org.junit.Test;
import org.omg.CORBA.portable.Streamable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lamuel on 3/29/2016.
 */
public class SymbolTableOrderedArrayTest {
    @Test
    public void doGetPut() {
        SymbolTableOrderedArray<String, Integer> st = new SymbolTableOrderedArray<String, Integer>();
        assertTrue("Symbol table lookup failed", st.isEmpty());

        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 2);
        st.put("R", 3);
        st.put("C", 4);
        st.put("H", 5);
        st.put("E", 6);
        st.put("X", 7);
        st.put("A", 8);
        st.put("M", 9);
        st.put("P", 10);
        st.put("L", 11);
        st.put("E", 12);

        assertEquals("Symbol table lookup failed", new Integer(8), st.get("A"));
        assertEquals("Symbol table lookup failed", new Integer(5), st.get("H"));

        assertEquals("Symbol table lookup failed", true, st.contains("L"));
        assertEquals("Symbol table lookup failed", false, st.contains("B"));

        st.delete("A");
        assertEquals("Symbol table lookup failed", null, st.get("A"));

        st.put("H", 10);
        assertEquals("Symbol table lookup failed", new Integer(10), st.get("H"));

        assertEquals("Symbol table lookup failed", 9, st.size());
        assertEquals("Symbol table lookup failed", 5, st.size("E", "P"));
        assertEquals("Symbol table lookup failed", 5, st.size("D", "Q"));

        assertEquals("Symbol table lookup failed", "C", st.min());
        assertEquals("Symbol table lookup failed", "X", st.max());
        assertEquals("Symbol table lookup failed", "L", st.select(3));
        assertEquals("Symbol table lookup failed", "M", st.floor("N"));
        assertEquals("Symbol table lookup failed", "M", st.ceiling("M"));
        assertEquals("Symbol table lookup failed", "P", st.floor("P"));
        assertEquals("Symbol table lookup failed", "P", st.ceiling("O"));
    }

    @Test(expected=NullPointerException.class)
    public void doNullGet() {
        SymbolTableLinkedList<String, Integer> st = new SymbolTableLinkedList<>();
        st.get(null);
    }

    @Test(expected=NullPointerException.class)
    public void doNullPut() {
        SymbolTableLinkedList<String, Integer> st = new SymbolTableLinkedList<>();
        st.put(null, 0);
    }

    @Test(expected=NullPointerException.class)
    public void doNullContains() {
        SymbolTableLinkedList<String, Integer> st = new SymbolTableLinkedList<>();
        st.contains(null);
    }

    @Test(expected=NullPointerException.class)
    public void doNullDelete() {
        SymbolTableLinkedList<String, Integer> st = new SymbolTableLinkedList<>();
        st.delete(null);
    }
}