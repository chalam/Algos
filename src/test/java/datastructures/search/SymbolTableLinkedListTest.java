package datastructures.search;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lamuel on 3/29/2016.
 */
public class SymbolTableLinkedListTest {
    @Test
    public void doGetPut() {
        SymbolTableLinkedList<String, Integer> st = new SymbolTableLinkedList<>();
        assertTrue("Symbol table lookup failed", st.isEmpty());

        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 2);
        st.put("R", 3);
        st.put("C", 4);
        st.put("H", 5);

        assertEquals("Symbol table lookup failed", new Integer(2), st.get("A"));
        assertEquals("Symbol table lookup failed", new Integer(5), st.get("H"));

        assertEquals("Symbol table lookup failed", true, st.contains("R"));
        assertEquals("Symbol table lookup failed", false, st.contains("T"));

        st.delete("A");
        assertEquals("Symbol table lookup failed", null, st.get("A"));

        st.put("H", 10);
        assertEquals("Symbol table lookup failed", new Integer(10), st.get("H"));

        assertEquals("Symbol table lookup failed", 5, st.size());


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