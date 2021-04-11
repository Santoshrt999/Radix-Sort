package com.graphs;

import java.util.Arrays;

/**
 * Efficient Radix Sort with the help of Counting Sort
 */
public class RadixSort {

    public static int[] radixSort(int[] A){
        int max = Arrays.stream(A).max().getAsInt();
        int noOfDigits = calcDigits(max);
        int placeValue = 1;  //start with 1's place 10's place and then 100's place and so on

        while(noOfDigits > 0){
            applyCountingSort(A, placeValue);
            placeValue *= 10;
            noOfDigits--;
        }

        System.out.println(Arrays.toString(A));

        return A;
    }

    private static void applyCountingSort(int[] a, int placeValue) {

        int range = 10; // decimal system, numbers from 0-9

        int[] freq = new int[range];
        int[] output = new int[a.length];

        //calculate the frequency of the digit
        for(int i=0; i<a.length; i++){
            int digit = (a[i]/placeValue) % range;
            freq[digit]++;
        }

        //Sum up the prev values from the frequency array

        for(int i=1; i<freq.length; i++){
            freq[i] += freq[i-1];
        }

        for(int i=a.length-1; i>=0; i--){
            int digit = (a[i]/placeValue) % range;
            output[freq[digit]-1]=a[i];
            freq[digit]--;
        }

        System.arraycopy(output, 0, a, 0, a.length);
    }

    private static int calcDigits(int max) {
        int count=0;
        while(max>0){
            count++;
            max/=10;
        }
        return count;
    }


    /*Driver Code*/
    public static void main(String[] args)
    {
        int arr[] = { 0, 20, 9, 90, 802, 24, 2, 180 };
        int n = arr.length;

        System.out.println(Arrays.toString(radixSort(arr)));
    }
}
