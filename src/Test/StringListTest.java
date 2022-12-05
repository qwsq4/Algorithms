package Test;

import Content.StringList;
import Content.StringListImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringListTest {
    StringList list;

    @Test
    @BeforeEach
    public void setup() {
        list = new StringListImpl(6);
        list.add("Один");
        list.add("Два");
        list.add("Три");
        list.add("Четыре");
        list.add("Пять");
    }

    @Test
    public void addTest() {
        list.add("Шесть");
        Assertions.assertEquals(6, list.size());
        Assertions.assertEquals("Шесть", list.get(5));
    }

    @Test
    public void addByIndexTest() {
        list.add(3, "Шесть");
        Assertions.assertEquals(6, list.size());
        Assertions.assertEquals("Шесть", list.get(3));
        Assertions.assertEquals("Четыре", list.get(4));
    }

    @Test
    public void setTest() {
        list.set(3, "Шесть");
        Assertions.assertEquals(5, list.size());
        Assertions.assertEquals("Шесть", list.get(3));
        Assertions.assertEquals("Пять", list.get(4));
    }

    @Test
    public void removeByIndexTest() {
        list.remove(3);
        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals("Пять", list.get(3));
    }

    @Test
    public void removeByItem() {
        list.remove("Один");
        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals("Два", list.get(0));
    }

    @Test
    public void containsTrueTest() {
        Assertions.assertTrue(list.contains("Три"));
    }

    @Test
    public void containsFalseTest() {
        Assertions.assertFalse(list.contains("Шесть"));
    }

    @Test
    public void indexOfTest() {
        Assertions.assertEquals(2, list.indexOf("Три"));
    }

    @Test
    public void lastIndexOf() {
        Assertions.assertEquals(2, list.lastIndexOf("Три"));
    }

    @Test
    public void equalsTest() {
        StringList list1 = new StringListImpl(6);
        list1.add("Один");
        list1.add("Два");
        list1.add("Три");
        list1.add("Четыре");
        list1.add("Пять");
        Assertions.assertTrue(list.equals(list1));
    }

    @Test
    public void isEmptyTest() {
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    public void clearTest() {
        StringList list1 = new StringListImpl(10);
        list.clear();
        Assertions.assertTrue(list.equals(list1));
    }

    @Test
    public void validateTest() {
        StringListImpl impl = new StringListImpl();
        try {
            impl.validateString(null);
        } catch (RuntimeException e) {
            Assertions.assertEquals("Строка не может быть равна null", e.getMessage());
        }

        try {
            list.add("Шесть");
            list.add("Семь");
        } catch (RuntimeException e) {
            Assertions.assertEquals("Кол-во элементов не может быть больше размера массива", e.getMessage());
        }

        try {
            impl.validateIndex(-1);
        } catch (RuntimeException e) {
            Assertions.assertEquals("Индекс не может быть отрицательным", e.getMessage());
        }
    }
}

