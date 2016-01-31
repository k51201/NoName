package mergesort;

import java.util.Scanner;

/**
 * Created by vampa on 28.01.2016.
 *
 * На числовой прямой окрасили N отрезков. Известны координаты левого и правого концов каждого отрезка (Li и Ri).
 * Найти сумму длин частей числовой прямой, окрашенных ровно в один слой.
 * Для сортировки реализуйте сортировку слиянием.
 *
 * Sample Input:
 * 3
 * 1 4
 * 7 8
 * 2 5
 * Sample Output:
 * 3
 */

public class Main {

    private static Point[] points;

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        points = new Point[n*2];
        for (int i = 0; i < points.length;) {
            points[i++] = new Point(cin.nextInt(), true);
            points[i++] = new Point(cin.nextInt(), false);
        }

        mergeSort(0, points.length);

        int layer = 0;
        int painted1 = 0;
        for (int i = 0; i < points.length; i++) {
            if (layer == 1)
                painted1 += points[i].coord - points[i - 1].coord;
            if (points[i].begin)
                layer++;
            else
                layer--;
        }
        System.out.println(painted1);
    }

    private static void mergeSort(int pos, int length) {
        if (length <= 1)
            return;
        int firstLen = length/2;
        int secondLen = length - firstLen;
        mergeSort(pos, firstLen);
        mergeSort(pos + firstLen, secondLen);
        Point[] bufArray = new Point[length];
        merge(pos, firstLen, pos + firstLen, secondLen, bufArray);
        System.arraycopy(bufArray, 0, points, pos, length);
    }

    private static void merge(int firstPos, int firstLen, int secondPos, int secondLen, Point[] resultArray) {
        int i = 0, j = 0;
        while (i < firstLen && j < secondLen)
            if (points[i + firstPos].isLess(points[j + secondPos])) {
                resultArray[i + j] = points[i + firstPos];
                i++;
            } else {
                resultArray[i + j] = points[j + secondPos];
                j++;
            }
        if (i == firstLen)
            for (;j < secondLen; j++)
                resultArray[i + j] = points[j + secondPos];
        else
            for (;i < firstLen; i++)
                resultArray[i + j] = points[i + firstPos];
    }


    private static class Point {
        int coord;
        boolean begin; // true - начало, false - конец отрезка

        public Point(int coord, boolean begin) {
            this.coord = coord;
            this.begin = begin;
        }

        public boolean isLess(Point that) {
            if (this.coord < that.coord || (this.coord == that.coord & !this.begin & that.begin))
                return true;
            else
                return false;
        }
    }

}
