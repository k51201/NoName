package polyline;

import java.util.Scanner;

/**
 * Created by vampa on 28.01.2016.
 *
 * Задано N точек на плоскости. Указать (N-1)-звенную несамопересекающуюся незамкнутую ломаную, проходящую через все эти точки.
 * Стройте ломаную в порядке возрастания x-координаты. Если имеются две точки с одинаковой x-координатой, то расположите раньше ту точку, у которой y-координата меньше.
 * Для сортировки точек реализуйте пирамидальную сортировку.
 *
 * Sample Input:
 * 4
 * 0 0
 * 1 1
 * 1 0
 * 0 1
 *
 * Sample Output:
 * 0 0
 * 0 1
 * 1 0
 * 1 1
 */

public class Main {

    private static int[][] coords;

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        coords = new int[n][2];
        for (int i = 0; i < n; i++) {
            coords[i][0] = cin.nextInt();
            coords[i][1]  = cin.nextInt();
        }
        cin.close();

        int heapSize = n;
        for (int i = n; 0 <= i ; i--)
            heapify(n, i);
        while (heapSize > 1) {
            swapCoords(0, --heapSize);
            heapify(heapSize, 0);
        }

        for (int i = 0; i < n; i++) {
            System.out.println("" + coords[i][0] + " " + coords[i][1]);
        }
    }

    private static void heapify(int size, int index) {
        int left = 2*index + 1;
        int right = left + 1;

        int largest = index;
        if (left < size && compareCoords(largest, left) < 0)
            largest = left;
        if (right < size && compareCoords(largest, right) < 0)
            largest = right;

        if (largest != index) {
            swapCoords(index, largest);
            heapify(size, largest);
        }
    }

    private static void swapCoords(int a, int b) {
        int buf = coords[a][0];
        coords[a][0] = coords[b][0];
        coords[b][0] = buf;
        buf = coords[a][1];
        coords[a][1] = coords[b][1];
        coords[b][1] = buf;
    }

    private static int compareCoords(int a, int b) {
        if (coords[a][0] < coords[b][0])
            return -1;
        else if (coords[a][0] == coords[b][0]) {
            if (coords[a][1] < coords[b][1])
                return -1;
            else if (coords[a][1] == coords[b][1])
                return 0;
            else
                return 1;
        } else
            return 1;
    }

}
