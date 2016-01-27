package braces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
//        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
//        String braces = cin.readLine();
//        cin.close();

        test();
    }

    private static void test() {
        System.out.println(calculate(")]").equals("[()]"));
        System.out.println(calculate("([").equals("([])"));
        System.out.println(calculate("([]").equals("([])"));
        System.out.println(calculate("]}[{]}{[(").equals("IMPOSSIBLE"));
        System.out.println(calculate("([])").equals("([])"));
        System.out.println(calculate("[{()}]]").equals("[[{()}]]"));
        System.out.println(calculate(")[").equals("()[]"));
        System.out.println(calculate("((])").equals("IMPOSSIBLE"));
        System.out.println(calculate("(({)").equals("IMPOSSIBLE"));
        System.out.println(calculate(")[(()").equals("()[(())]"));
        System.out.println(calculate(")[(()[]").equals("()[(()[])]"));
        System.out.println(calculate("()]").equals("[()]"));
        System.out.println(calculate("}[[([{[]}").equals("{}[[([{[]}])]]"));
        System.out.println(calculate("{][[[[{}[]").equals("IMPOSSIBLE"));
        System.out.println(calculate("]()}[](({}").equals("{[]()}[](({}))"));
    }

    private static String calculate(String braces) {
        char[] bracesArray = braces.toCharArray();
        System.arraycopy(bracesArray, 0, array, 0, bracesArray.length);
        tail = 0;
        head = bracesArray.length - 1;

        for (int i = 0; i < bracesArray.length; i++) {
            switch (array[i]) {
                case '(':
                case '[':
                case '{':
                case ')':
                case ']':
                case '}':
            }
        }
        return "IMPOSSIBLE";
    }

    private static char[] array = new char[1000000];
    private static int head; // Показывает позицию элемента в начале дека
    private static int tail; // Показывает позицию элемента в конце дека

    private static char popBack() {
        if ((head + 1) % array.length == tail)
            return 0;

        if (tail == array.length - 1) {
            tail = 0;
            return array[array.length - 1];
        } else
            return array[tail++];
    }

    private static char popFront() {
        if ((head + 1) % array.length == tail)
            return 0;

        if (head == 0) {
            head = array.length - 1;
            return array[0];
        } else
            return array[head--];
    }

    private static void pushBack(char value) {
        if (tail == 0)
            tail = array.length - 1;
        else
            tail--;

        array[tail] = value;
    }

    private static void pushFront(char value) {
        if (head == array.length - 1)
            head = 0;
        else
            head++;

        array[head] = value;
    }
}
