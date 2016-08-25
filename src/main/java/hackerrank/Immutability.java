package hackerrank;

import java.util.*;

/**
 * Make a person object immutable
 * Created by Lamuel on 8/24/2016.
 */
public class Immutability {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(1980, Calendar.MAY, 20);

        String[] cols = {"RED", "BLUE", "GREEN"};
        List<String> colors = new ArrayList<>(Arrays.asList(cols));
        Person p1 = new Person("Lam", cal.getTime(), colors);
        System.out.printf("\n1=%s", p1);

        Person p2 = p1;
        colors.add("YELLOW");
        System.out.printf("\n2=%s is equal to %s", p2, p1.equals(p2));

        Person p3 = p1;
        p3.getDob().setYear(2010);
        try {
            p3.getFavoriteColors().add("CYAN");
        }
        catch (UnsupportedOperationException ex) {
            System.out.printf("\n3=%s", ex);
        }
        System.out.printf("\n3=%s is equal to %s", p3, p1.equals(p3));

        Person p4 = p1;
        System.out.printf("\n4=%s is equal to %s", p4, p1.equals(p4));

    }
}

/**
 * It is safe to return immutable objects and primitives (as they are returned by value)
 * However you need to make defensive copies of mutable objects
 *
 * Effective Java:
 *  Item 15: Minimize mutability
 *  Item 39: Make Defensive copies when needed
 * 1. it is essential to make a defensive copy of each mutable parameter to the constructor
 * 2.  return defensive copies of mutable internal fields
 */
final class Person {
    private final String name;
    private final Date dob;
    private final List<String> favoriteColors;

    Person(String name, Date dob, List<String> favoriteColors) {
        this.name = name;
//        this.dob = dob;

//        1. always mutable
//        this.favoriteColors = favoriteColors;

//        2. mutable from original reference
//        this.favoriteColors = Collections.unmodifiableList(favoriteColors);

//        3. defensive copy disconnect from original reference, hence guarantees immutability
//        this.favoriteColors = Collections.unmodifiableList(new ArrayList<>(favoriteColors));

//        4. make defensive copy and getter to return immutable list
        this.dob = new Date(dob.getTime());
        this.favoriteColors = new ArrayList<>(favoriteColors);
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
//        return dob;
        return new Date(dob.getTime());
    }

    public List<String> getFavoriteColors() {
//        return favoriteColors;
        return Collections.unmodifiableList(favoriteColors);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append(", dob=").append(dob);
        sb.append(", favoriteColors=").append(favoriteColors);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (dob != null ? !dob.equals(person.dob) : person.dob != null) return false;
        return favoriteColors != null ? favoriteColors.equals(person.favoriteColors) : person.favoriteColors == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (favoriteColors != null ? favoriteColors.hashCode() : 0);
        return result;
    }
}