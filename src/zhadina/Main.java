package zhadina;

import java.util.Scanner;

/**
 * Created by vampa on 27.01.2016.
 */
public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);

        int fruitsNumber = cin.nextInt();

        FruitBasket fruitBasket = new FruitBasket(fruitsNumber);
        for (int i = 0; i < fruitsNumber; i++)
            fruitBasket.add(cin.nextInt());

        int carrying = cin.nextInt();
        cin.close();

        int count = 0;
        while (!fruitBasket.isEmpty()) {
            //набираем фрукты в руки
            int gramsInHands = 0;
            int[] hands = new int[carrying];
            int fruitsInHands = 0;
            while (fruitBasket.atNext() + gramsInHands <= carrying && !fruitBasket.isEmpty()) {
                int fruit = fruitBasket.next();
                hands[fruitsInHands++] = fruit;
                gramsInHands += fruit;
            }
            //хаваем фрукты
            for (int i = 0; i < fruitsInHands; i++)
                if (hands[i] > 1)
                    fruitBasket.add(hands[i]/2);

            count++;
        }

        System.out.println(count);
    }

    static class FruitBasket {

        private int[] basket;
        private int number;

        public FruitBasket(int fruitsNumber) {
            basket = new int[fruitsNumber];
            number = 0;
        }

        public void add(int fruit) {
            basket[number] = fruit;
            siftUp(number++);
        }

        public int next() {
            if (isEmpty())
                return 0;
            int res = basket[0];
            basket[0] = basket[--number];
            siftDown(0);
            return res;
        }

        private void siftUp(int index0) {
            int current = index0;
            while(current > 0) {
                int parent = (current - 1) /2;
                if (basket[current] <= basket[parent])
                    return;

                int buf = basket[current];
                basket[current] = basket[parent];
                basket[parent] = buf;

                current = parent;
            }
        }

        private void siftDown(int index) {
            int left = 2*index + 1;
            int right = 2*index + 2;

            int largest = index;
            if (left < number && basket[index] < basket[left])
                largest = left;
            if (right < number && basket[largest] < basket[right])
                largest = right;

            if (largest != index) {
                int buf = basket[index];
                basket[index] = basket[largest];
                basket[largest] = buf;
                siftDown(largest);
            }
        }

        public boolean isEmpty() {
            return number == 0;
        }

        public int atNext() {
            if (0 < number)
                return basket[0];
            else
                return 0;
        }
    }
}
