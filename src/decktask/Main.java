package decktask;

import java.util.Scanner;

/**
 * Created by vampa on 12.01.2016.
 */
public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        boolean expectation = true;

        for (int i = 0; i < n; i++) {
            int operCode = cin.nextInt();
            int inputValue = cin.nextInt();

            switch (operCode) {
                case 1:
                    pushFront(inputValue);
                    break;
                case 2:
                    if (popFront() != inputValue)
                        expectation = false;
                    break;
                case 3:
                    pushBack(inputValue);
                    break;
                case 4:
                    if (popBack() != inputValue)
                        expectation = false;
            }
            for (int j = 0; j < array.length; j++)
                System.out.print("" + array[j] + " ");
            System.out.println(expectation);
        }
        if (expectation)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static int[] array = new int[4];
    private static int head = 0; // Показывает позицию элемента в начале дека
    private static int tail = 1; // Показывает позицию элемента в конце дека

    private static int popBack() {
        if ((head + 1) % array.length == tail)
            return -1;

        if (tail == array.length - 1) {
            tail = 0;
            return array[array.length - 1];
        } else
            return array[tail++];
    }

    private static int popFront() {
        if ((head + 1) % array.length == tail)
            return -1;

        if (head == 0) {
            head = array.length - 1;
            return array[0];
        } else
            return array[head--];
    }

    private static void pushBack(int value) {
        if ((head + 2) % array.length == tail)
            increaseArray();

        if (tail == 0)
            tail = array.length - 1;
        else
            tail--;

        array[tail] = value;
    }

    private static void pushFront(int value) {
        if ((head + 2) % array.length == tail)
            increaseArray();

        if (head == array.length - 1)
            head = 0;
        else
            head++;

        array[head] = value;
    }

    private static void increaseArray() {
        int[] increased = new int[array.length*2];
        if (head < tail) {
            int incTail = tail + array.length;
            System.arraycopy(array, 0, increased, 0, head + 1);
            System.arraycopy(array, tail, increased, incTail, array.length - tail);
            tail = incTail;
        } else
            System.arraycopy(array, tail, increased, tail, head - tail + 1);
        array = increased;
    }
}
