package Content;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] list;
    private int realSize = 0;

    public StringListImpl() {
        list = new String[10];
    }

    public StringListImpl(int size) {
        list = new String[size];
    }

    @Override
    public String add(String item) throws RuntimeException {
        validateSize();

        for (int i = 0; i <= realSize; i++) {
            if (list[i] == null) {
                list[i] = item;
                break;
            }
        }
        realSize++;
        return "Строка " + item + " добавлена в массив";
    }

    @Override
    public String add(int index, String item) throws RuntimeException {
        validateSize();
        validateIndex(index);
        validateString(item);

        if (list[index] == null) {
            list[index] = item;
        } else {
            for (int i = realSize - 1; i > index; i--) {
                list[i] = list[i - 1];
            }
            list[index] = item;
            realSize++;
        }
        return "Строка " + item + " добавлена в массив";
    }

    @Override
    public String set(int index, String item) throws RuntimeException {
        validateSize();
        validateIndex(index);
        validateString(item);
        if (list[index] == null) {
            list[index] = item;
            realSize++;
            return "Строка " + item + " добавлена в массив";
        } else {
            list[index] = item;
            return "Строка " + item + " добавлена в массив, заменив строку " + list[index];
        }
    }

    @Override
    public String remove(String item) throws RuntimeException{
       validateString(item);

        for (int i = 0; i < realSize - 1; i++) {
            if (list[i].equals(item)) {
                for (int j = i; j < realSize - i; j++) {
                    list[j] = list[j + 1];
                }
            }
        }
        realSize--;
        list[list.length - 1] = null;
        return "Строка " + item + " удалена из массива";
    }

    @Override
    public String remove(int index) throws RuntimeException{
        validateSize();
        validateIndex(index);
        for (int i = index; i < realSize; i++) {
            list[i] = list[i + 1];
        }
        realSize--;
        list[list.length - 1] = null;
        return "Строка под индексом " + index + "была удалена из массива";
    }

    @Override
    public boolean contains(String item)  throws RuntimeException{
        validateString(item);
        return Arrays.stream(list).toList().contains(item);
    }

    @Override
    public int indexOf(String item) throws RuntimeException{
        validateString(item);
        for (int i = 0; i < realSize; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) throws RuntimeException{
        validateString(item);
        for (int i = realSize - 1; i > 0; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) throws RuntimeException{
        validateIndex(index);
        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray()) && this.realSize == otherList.size();
    }

    @Override
    public int size() {
        return realSize;
    }

    @Override
    public boolean isEmpty() {
        return realSize == 0;
    }

    @Override
    public void clear() {
        this.list = new String[10];
        realSize = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(list, realSize);
    }

    public void validateString(String item) {
        if (item == null) {
            throw new RuntimeException("Строка не может быть равна null");
        }
    }

    public void validateSize() {
        if (realSize == list.length) {
            throw new RuntimeException("Кол-во элементов не может быть больше размера массива");
        }
    }

    public void validateIndex(int index) {
        if (index < 0 || index > realSize) {
            throw new RuntimeException("Индекс не может быть отрицательным");
        }
    }
}
