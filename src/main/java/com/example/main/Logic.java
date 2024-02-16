package com.example.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Logic {
    public static void main(String[] args) {
        int[][] arr = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };


//        arr[1][2] = 2;
//        arr[3][0] = 4;
//
//        prettyPrint(arr);
//
//        insert_2_or_4(arr, 1, 2);
//        insert_2_or_4(arr, 3, 0);
//
//        System.out.println(getEmptyList(arr));
//        prettyPrint(arr);

//        ArrayList[] arrayLists = {new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()};
//        arrayLists[0].add(0);
//        arrayLists[0].add(0);
//        arrayLists[0].add(0);
//        arrayLists[0].add(0);
//
//        arrayLists[1].add(0);
//        arrayLists[1].add(0);
//        arrayLists[1].add(0);
//        arrayLists[1].add(0);
//
//        arrayLists[2].add(0);
//        arrayLists[2].add(0);
//        arrayLists[2].add(0);
//        arrayLists[2].add(0);
//
//        arrayLists[3].add(0);
//        arrayLists[3].add(0);
//        arrayLists[3].add(0);
//        arrayLists[3].add(0);


//        prettyPrint(arrayLists);
//        moveDown(arrayLists);
//        moveDown(arrayLists);
//        prettyPrint(arrayLists);



//        ArrayList arrayList = new ArrayList();
//
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//        arrayList.add(4);
//        arrayList.add(5);
//
//        arrayList.remove(2);
//        arrayList.add(2, 6);
//
//        System.out.println(arrayList);
    }

    public static void prettyPrint(ArrayList[] arr) {
        System.out.println("-------");
        for (int i = 0;i<4;i++) {
            for (int j = 0;j<4;j++) {
                System.out.print(arr[i].get(j) + " ");
            }
            System.out.println();
        }
        System.out.println("-------");
    }

    public static ArrayList getEmptyList(ArrayList[] arr) {
        ArrayList<Integer> empty = new ArrayList<>();
        for (int i = 0;i<4;i++) {
            for (int j = 0;j<4;j++) {
                if (arr[i].get(j).equals(0)) {
                    int num = getNumberFromIndex(i, j);
                    empty.add(num);
                }
            }
        }
        return empty;
    }

    public static int getNumberFromIndex(int i, int j) {
        return i*4+j+1;
    }

    public static int[] getIndexFromNumber(int num) {
        num -= 1;
        int i = num/4;
        int j = num%4;
        return new int[] {i, j};
    }

    public static ArrayList[] insert_2_or_4(ArrayList[] arr, int i, int j) {
        double counter = Math.random();
        if(counter <= 0.75)
            arr[i].set(j, 2);
        else
            arr[i].set(j, 4);
        return arr;
    }

    public static boolean moveRight(ArrayList[] arr, int[] score) {
        ArrayList[] copy = copy(arr);

        for(int i = 0;i<4;i++) {
            for(int j = 0;j<4;j++) {
                arr[i].remove((Object) 0);
            }
            while(arr[i].size() < 4) {
                arr[i].add(0, 0);
            }
        }

        for(int i = 0;i<4;i++) {
            for(int j = 3;j>0;j--) {
                if(arr[i].get(j-1).equals(arr[i].get(j)) && !arr[i].get(j).equals(0)) {
                    int num = (int)arr[i].get(j)*2;
                    arr[i].set(j, num);
                    score[0]+=num;
                    arr[i].remove(j-1);
                    arr[i].add(0, 0);
                }
            }
        }
        return !Arrays.equals(copy, arr) || Arrays.equals(arr, emptyArray());
    }

    public static boolean moveLeft(ArrayList[] arr, int[] score) {
        ArrayList[] copy = copy(arr);

        for(int i = 0;i<4;i++) {
            for(int j = 0;j<4;j++) {
                arr[i].remove((Object) 0);
            }
            while(arr[i].size() < 4) {
                arr[i].add(0);
            }
        }

        for(int i = 0;i<4;i++) {
            for(int j = 0;j<3;j++) {
                if(arr[i].get(j).equals(arr[i].get(j+1)) && !arr[i].get(j).equals(0)) {
                    int num = (int)arr[i].get(j)*2;
                    arr[i].set(j, num);
                    score[0]+=num;
                    arr[i].remove(j+1);
                    arr[i].add(0);
                }
            }
        }
        return !Arrays.equals(copy, arr) || Arrays.equals(arr, emptyArray());
    }

    public static boolean moveUp(ArrayList[] arr, int[] score) {
        ArrayList[] rotatedArr1 = rotate(arr);
        boolean result = moveLeft(rotatedArr1, score);
        ArrayList[] rotatedArr2 = rotate(rotatedArr1);
        for(int i = 0;i<4;i++) {
            arr[i] = rotatedArr2[i];
        }
        return result;
    }
    public static boolean moveDown(ArrayList[] arr, int[] score) {
        ArrayList[] rotatedArr1 = rotate(arr);
        boolean result = moveRight(rotatedArr1, score);
        ArrayList[] rotatedArr2 = rotate(rotatedArr1);
        for(int i = 0;i<4;i++) {
            arr[i] = rotatedArr2[i];
        }
        return result;
    }

    public static boolean checkMatrix(ArrayList[] arr) {
        ArrayList[] copy1 = copy(arr);
        ArrayList[] copy2 = copy(arr);
        ArrayList[] copy3 = copy(arr);
        ArrayList[] copy4 = copy(arr);
        int[] score = {0};

        if(!Logic.moveRight(copy1, score) && !Logic.moveLeft(copy2, score)
           && !Logic.moveUp(copy3, score) && !Logic.moveDown(copy4, score)) {
            return true;
        }
        return false;
    }

    private static ArrayList[] rotate(ArrayList[] arr) {
        ArrayList[] rotatedArr = {new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()};
        for(int i = 0;i<4;i++) {
            for(int j = 0;j<4;j++) {
                rotatedArr[i].add(j, arr[j].get(i));
            }
        }
        return rotatedArr;
    }

    public static int getRandomSlot(ArrayList slots) {
        if(slots.size() == 0) {
            return -1;
        }
        int randomSlotIndex = (int)(Math.random()*(slots.size()-1));
        System.out.println("Slot index: " + randomSlotIndex);
        return (int)slots.get(randomSlotIndex);
    }

    public static ArrayList[] copy(ArrayList[] arr) {

        ArrayList[] result = {new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()};

        for(int i = 0;i<4;i++) {
            for(int j = 0;j<4;j++) {
                result[i].add(j, arr[i].get(j));
            }
        }
        return result;
    }

    private static ArrayList[] emptyArray() {
        ArrayList[] arrayLists = {new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()};
        arrayLists[0].add(0);
        arrayLists[0].add(0);
        arrayLists[0].add(0);
        arrayLists[0].add(0);

        arrayLists[1].add(0);
        arrayLists[1].add(0);
        arrayLists[1].add(0);
        arrayLists[1].add(0);

        arrayLists[2].add(0);
        arrayLists[2].add(0);
        arrayLists[2].add(0);
        arrayLists[2].add(0);

        arrayLists[3].add(0);
        arrayLists[3].add(0);
        arrayLists[3].add(0);
        arrayLists[3].add(0);

        return arrayLists;
    }
}
