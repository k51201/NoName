package insertionsort;

import java.util.Scanner;

/**
 * Created by vampa on 27.01.2016.
 */
public class Main {

    private static int[] arr = new int[32];
    private static int n = 0;

    public static void main(String[] args) {

        Scanner cin = new Scanner(System.in);
        do {
            int curr = cin.nextInt();
            insert(curr);
        } while (cin.hasNext());
        cin.close();

        for (int i = 0; i < n; i++)
            System.out.print("" + arr[i] + " ");
        System.out.println();
    }

    private static void insert(int element) {
        //поиск места вставки
        int first = 0;
        int last = n;
        while (first < last) {
            int mid = (first + last)/2;
            if (element <= arr[mid])
                last = mid;
            else
                first = mid + 1;
        }
        //вставка элемента и сдвиг правой части на 1
        if (arr.length <= n)
            increaseArray();
        for (int i = n++; first < i; i--)
            arr[i] = arr[i - 1];
        arr[first] = element;
    }

    private static void increaseArray() {
        int[] increased = new int[arr.length*2];
        System.arraycopy(arr, 0, increased, 0, arr.length);
        arr = increased;
    }
}
