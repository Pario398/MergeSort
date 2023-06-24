import java.lang.reflect.Array;
import java.uril.*;
import java.util.Arrays;
public class MergeSort {
  public static int[] merge(int[] a, int[] b) {
        int[] finalArray = new int[a.length + b.length];
        int indexofA = 0;
        int indexofB = 0;
        for (int i = 0; i < finalArray.length; i++) {
            if (indexofA < a.length && indexofB < b.length) {
                if (a[indexofA] < b[indexofB]) {
                    finalArray[i] = a[indexofA];
                    indexofA++;
                } else {
                    finalArray[i] = b[indexofB];
                    indexofB++;
                }
            }
            else if (indexofA < a.length) {
                finalArray[i] = a[indexofA];
                indexofA++;
            } else {
                finalArray[i] = b[indexofB];
                indexofB++;
            }
        }
        return finalArray;
    }
    public static int[] mergesort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }
        left = mergesort(left);
        right = mergesort(right);
        int[] resultArray = merge(left, right);
        return resultArray;
    }
    public static int[] merge3(int[] a, int[] b, int[] c) {
        int[] firstMerge = merge(a, b);
        int[] finalMerge = merge(firstMerge, c);
        return finalMerge;
    }

    public static int[] mergesort3(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        } else {
            int firstLength = arr.length / 3;
            int secondLength = (arr.length - firstLength) / 2;
            int thirdLength = arr.length - firstLength - secondLength;
            int[] arrayOne = new int[firstLength];
            for (int i = 0; i < firstLength; i++) {
                arrayOne[i] = arr[i];
            }
            int[] arrayTwo = new int[secondLength];
            for (int i = 0; i < secondLength; i++) {
                arrayTwo[i] = arr[firstLength + i];
            }
            int[] arrayThree = new int[thirdLength];
            for (int i = 0; i < thirdLength; i++) {
                arrayThree[i] = arr[arr.length - thirdLength + i];
            }
            int[] sortedOne = mergesort3(arrayOne);
            int[] sortedTwo = mergesort3(arrayTwo);
            int[] sortedThree = mergesort3(arrayThree);
            int[] mergedArray = merge3(sortedOne, sortedTwo, sortedThree);
            return mergedArray;
        }
    }

    public static int[] mergeAll(int[][] arrays) {
        int length = 0;
        for (int[] array : arrays) {
            length += array.length;
        }
        int[] result = new int[length];
        int[] indices = new int[arrays.length];
        for (int i = 0; i < length; i++) {
            int minIndex = 0;
            int minValue = Integer.MAX_VALUE;
            for (int j = 0; j < arrays.length; j++) {
                if (indices[j] < arrays[j].length && arrays[j][indices[j]] < minValue) {
                    minIndex = j;
                    minValue = arrays[j][indices[j]];
                }
            }
            result[i] = minValue;
            indices[minIndex]++;
        }  
        return result;
    }
  public static int[] mergesortK(int[] arr, int k) {
        if (arr.length <= 1) {
            return arr;
        }

        int[][] parts = new int[k][];
        int partSize = arr.length / k;
        int remainingElements = arr.length % k;
        for (int i = 0; i < k; i++) {
            int size = partSize;
            if (remainingElements > 0) {
                size++;
                remainingElements--;
            }
            parts[i] = new int[size];
            for (int j = 0; j < size; j++) {
                parts[i][j] = arr[i * partSize + j - remainingElements];
            }
        }

        for (int i = 0; i < k; i++) {
            parts[i] = mergesortK(parts[i], k);
        }

        int[] sortedArray = mergeAll(parts);
        return sortedArray;
    }
}
