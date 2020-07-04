package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Digraph {
    private boolean[][] matrixDigraph;

    public int size () {
        return matrixDigraph.length;
    }

    public void addAdge(int v1, int v2) {
        if (matrixDigraph == null) {
            int max = v1 > v2 ? v1: v2;
            matrixDigraph = new boolean[max + 1][max + 1];
        }
        if (v1 >= matrixDigraph.length || v2 >= matrixDigraph.length) {
            int max = v1 > v2 ? v1: v2;
            matrixDigraph = booleanArrayCopyOf(matrixDigraph, max + 1);
        }
        matrixDigraph[v1][v2] = true;
    }

    public void removeAdge(int v1, int v2) {
        if (matrixDigraph != null) {
            if (v1 < matrixDigraph.length && v2 < matrixDigraph.length) {
                matrixDigraph[v1][v2] = false;
            }
        }
    }

    public ArrayList<Integer> searchCountGraphPaths (int v1, int v2) {
        if (v2 > matrixDigraph.length || v1 > matrixDigraph.length) {
            return null;
        }
        int[] countLastNode = new int[matrixDigraph.length];
        countLastNode[v1] = 1;
        countLastNode = searchCountLastNode(v1, v2, countLastNode);
        int[] countPathInThisNode = searchCountPathInThisNode(0, 5);
        ArrayList<Integer> nodesInAllPaths = new ArrayList<>();
        nodesInAllPaths = searchNodesInAllPaths(countPathInThisNode, countLastNode[v2]);
        return nodesInAllPaths;
    }

    private int[] searchCountPathInThisNode (int v1, int v2) {
        int[] allPaths = new int[matrixDigraph.length];
        boolean[] path = new boolean[matrixDigraph.length];
        allPaths = searchCountPathInThisNodeRecurs(v1, v2, path, allPaths);
        return allPaths;
    }

    private int[] searchCountPathInThisNodeRecurs(int v1, int v2, boolean[] path, int[] allPaths) {

        for (int i = 0; i < matrixDigraph.length; i++) {
            if (matrixDigraph[v1][i]) {
                if (i == v2) {
                    boolean[] newPath = Arrays.copyOf(path, path.length);
                    int[] pathInInt = toInt(newPath);
                    allPaths = sumTwoArrays(allPaths, pathInInt);
                } else if (i != v1) {
                    boolean[] newPath = Arrays.copyOf(path, path.length);
                    newPath[i] = true;
                    allPaths = searchCountPathInThisNodeRecurs(i, v2, newPath, allPaths);
                }
            }
        }
        return allPaths;
    }

    private int[] searchCountLastNode(int v0, int v2, int[] countLastNode) {
        for (int i = 0; i < matrixDigraph.length; i++) {
            if (i != v0 && matrixDigraph[v0][i] == true) {
                countLastNode[i]++;
            }
        }
        for (int i = 0; i < matrixDigraph.length; i++) {
            if (i != v2 && matrixDigraph[v0][i] == true) {
                int[] count = searchCountLastNode(i,v2, countLastNode);
                countLastNode = Arrays.copyOf(count, count.length);
            }
        }
        return countLastNode;
    }

    private ArrayList<Integer> searchNodesInAllPaths(int[] countPathInNode, int countOfPaths) {
        ArrayList<Integer> nodesInAllPaths = new ArrayList<>();
        for (int i = 0; i < countPathInNode.length; i++) {
            if (countPathInNode[i] == countOfPaths) {
                nodesInAllPaths.add(i);
            }
        }
        return nodesInAllPaths;
    }

    private int[] sumTwoArrays (int[] first, int[] second) {
        int[] sumArray = new int[first.length];
        for (int i = 0; i < first.length; i++) {
            sumArray[i] += first[i] + second[i];
        }
        return sumArray;
    }

    private static int[] toInt (boolean[] array) {
        int[] arrayInInt = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i]) {
                arrayInInt[i] = 1;
            } else {
                arrayInInt[i] = 0;
            }
        }
        return arrayInInt;
    }

    public String toDot () {
        String dotProgram = "graph {\n";
        for (int i = 0; i < matrixDigraph.length; i++) {
            for (int j = i + 1; j < matrixDigraph.length; j++) {
                if (matrixDigraph[i][j] == true) {
                    dotProgram += "    " + i + " -> " + j + "\n";
                }
            }
        }
        dotProgram += "}";
        return dotProgram;
    }

    private boolean[][] booleanArrayCopyOf(boolean[][] matrixDigraph, int newLength) {
        boolean[][] newMatrixDigraph = new boolean[newLength][newLength];
        for (int i = 0; i < newLength; i++) {
            for (int j = 0; j < newLength; j++) {
                if (i < matrixDigraph.length && j < matrixDigraph.length) {
                    newMatrixDigraph[i][j] = matrixDigraph[i][j];
                }
            }
        }
        return newMatrixDigraph;
    }
}
