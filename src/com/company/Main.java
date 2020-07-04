package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static Digraph readDigraph() {
        Digraph digraph = new Digraph();
        System.out.println("Введите количество рёбер: ");
        Scanner scn = new Scanner(System.in);
        int countAdges = scn.nextInt();
        for (int i = 0; i < countAdges; i++) {
            System.out.println(
                    "Введите по очереди через enter сначала номер вершины - начала ребра, \n" +
                            "затем номер вершины - конца ребра: ");
            digraph.addAdge(scn.nextInt(), scn.nextInt());
        }
        return digraph;
    }

    public static void main(String[] args) {
//        Digraph graph = readDigraph();
        /**
         * пример:
         * здесь удовлетворяет условию только вершина 3
         * */
        Digraph graph = new Digraph();
        graph.addAdge(0, 1);
        graph.addAdge(0, 7);
        graph.addAdge(1, 2);
        graph.addAdge(1, 7);
        graph.addAdge(2, 3);
        graph.addAdge(3, 4);
        graph.addAdge(3, 6);
        graph.addAdge(4, 5);
        graph.addAdge(6, 5);
        graph.addAdge(7, 3);
//*/
        System.out.println("Поиск вершин, имеющихся во всех путях от a до b: ");
        System.out.println("Введите a: ");
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        System.out.println("Введите b: ");
        int b = scn.nextInt();
        ArrayList<Integer> list = graph.searchCountGraphPaths(a, b);
        String listToString = listToString(list);
        System.out.println("Найдено вершин: " + list.size());
        System.out.printf("Вершины, имеющиеся в каждом пути от %,d до %,d: %s\n", a, b, listToString);
        String graphInDot = graph.toDot();
        System.out.println(graphInDot);
    }

    public static String listToString(ArrayList<Integer> list) {
        if (list.size() == 0) {
            return "отсутсвуют";
        }
        String string = "";
        for (Integer i: list) {
            string+= i + " ";
        }
        return string;
    }
}
