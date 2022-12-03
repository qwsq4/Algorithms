import java.util.Arrays;
import java.util.stream.Stream;

public class StringListImpl implements StringList {
    private final String[] list;
    private int realSize = 0;

    public StringListImpl(int size) {
        list = new String[size];
    }

    @Override
    public String add(String item) throws RuntimeException {
        if (!contains(null)) {
            throw new RuntimeException("Массив полностью заполнен");
        }

        for (int i = 0; i < list.length; i++) {
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
        if (list.length < index) {
            throw new RuntimeException("Размер массива меньше указанного индекса");
        }

        if (list[index] != null) {
            throw new RuntimeException("Эта ячейка массива уже заполнена");
        } else list[index] = item;
        realSize++;
        return "Строка " + item + " добавлена в массив";
    }

    @Override
    public String set(int index, String item) throws RuntimeException {
        if (list.length < index) {
            throw new RuntimeException("Размер массива меньше указанного индекса");
        }

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
    public String remove(String item) {
        if (!contains(item)) {
            throw new RuntimeException("Такого элемента в массиве нет");
        }

        for (int i = 0; i < item.length() - 2; i++) {
            if (list[i].equals(item)) {
                for (int j = i; j < list.length - i; j++) {
                    list[j] = list[j + 1];
                }
            }
        }
        list[list.length - 1] = null;
        return "Строка " + item + " удалена из массива";
    }

    @Override
    public String remove(int index) {
        if (list.length < index + 1 || list[index] == null) {
            throw new RuntimeException("Такого элемента в массиве нет");
        }

        for (int i = index; i < list.length - 2; i++) {
            list[i] = list[i + 1];
        }
        list[list.length - 1] = null;
        return "Строка под индексом " + index + "была удалена из массива";
    }

    @Override
    public boolean contains(String item) {
        return Arrays.stream(list).toList().contains(item);
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = list.length - 1; i > 0; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (list.length < index + 1) {
            throw new RuntimeException("Указанный индекс выходит за пределы массива");
        } else return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
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
        realSize = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(list, realSize);
    }
}
