package decktask;

import java.util.Scanner;

/**
 * Created by vampa on 12.01.2016.
 */
public class Deck {
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
        }
        if (expectation)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static int[] array = new int[4];
    private static int front = 0;
    private static int back = 0;

    private static int popBack() {
        if (front == back)
            return -1;
        if (back == array.length - 1)
            back = 0;
        else
            back++;
        return array[back];
    }

    private static int popFront() {
        if (front == back)
            return -1;
        if (front == 0) {
            front = array.length - 1;
            return array[0];
        } else
            return array[front--];
    }

    private static void pushBack(int value) {
        if ((front + 2) % array.length == back)
            increaseArray();
        if (back == 0) {
            back = array.length - 1;
            array[0] = value;
        } else
            array[back--] = value;
    }

    private static void pushFront(int value) {
        if ((front + 2) % array.length == back)
            increaseArray();
        if (front == array.length - 1)
            front = 0;
        else
            front++;
        array[front] = value;
    }

    private static void increaseArray() {
        int[] increased = new int[array.length*2];
        if (front < back) {
            System.arraycopy(array, 0, increased, 0, front);
            System.arraycopy(array, back, increased, back + array.length, array.length - back);
        }
        System.arraycopy(array, 0, increased, 0, array.length);
        array = increased;
    }
}
