package arr;

import java.util.Arrays;
import java.util.Random;
import java.util.*;

public class ObjArrayer {
    protected ObjArrayer() {
        throw new RuntimeException();
    }
    
    public static<T> void compact(T[] arr, Object a) {
        int shift = 0;
        
        int i = indexOf(arr, a);
        if (i == -1) return;
        shift++;
        
        for ( ; i + shift < arr.length ; ) {
            arr[i] = arr[i + shift];
            if (arr[i].equals(a)) {
                shift++;
            }
            else i++;
        }
        
        for (i = arr.length - shift ; i < arr.length ; i++) {
            arr[i] = null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static<T extends Comparable> int compare(T[] a, T[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Array lengths must be equal to compare");
        }
        
        int comparison = 0;
        
        for (int i = 0; i < a.length ; i++) {
            comparison += a[i].compareTo(b[i]);
        }
        
        return comparison;
    }
    
    public static<T> int compareSize(T[] a, T[] b) {
        return a.length - b.length;
    }
    
    public static<T> boolean contains(T[] arr, T a) {
        return contains(arr, a, 0, arr.length - 1);
    }

    public static<T> boolean contains(T[] arr, T a, int start) {
        return contains(arr, a, start, arr.length - 1);
    }

    public static<T> boolean contains(T[] arr, T a, int start, int end) {
        return indexOf(arr, a, start, end) != -1;
    }
    
    public static<T> boolean equals(Object[] a, Object[] b) {
        if (a == b) return true;
        if (a.length != b.length) return false;
        if (! a[0].getClass().equals(b[0].getClass())) return false;
        
        for (int i = 0 ; i < a.length ; i++) {
            if (a[i] == null) {
                if (b[i] == null) {
                    continue;
                }
                return false;
            }
            if (! a[i].equals(b[i])) return false;
        }
        return true;
    }

    public static<T> Class getClass(T[] arr) {
        if (arr.length > 0 && arr[0] != null) {
            return arr[0].getClass();
        }
        return arr.getClass().getComponentType();
    }
    
    public static<T> int indexOf(T[] arr, T a) {
        return indexOf(arr, a, 0, arr.length - 1);
    }   
    
    public static<T> int indexOf(T[] arr, T a, int start) {
        return indexOf(arr, a, start, arr.length - 1);
    }
    
    public static<T> int indexOf(T[] arr, T a, int start, int end) {
        if (a == null) {
            for (int i = start ; i <= end ; i++) {
                if (arr[i] == null) {
                    return i;
                }
            }
            return -1;
        }
        for (int i = start ; i <= end ; i++) {
            if (a.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }   
    
    @SafeVarargs
    public static<T> int largestOf(T[]... arrs) {
        int largestIndex = 0;
        for (int i = 1 ; i < arrs.length ; i++) {
            if (arrs[i].length < arrs[largestIndex].length) {
                largestIndex = i;
            }
        }
        return largestIndex;
    }
    
    @SuppressWarnings("unchecked")
    public static<T> T[] newArray(Class<T> type, int size) {
        return (T[]) new Object[size];
    }
    
    public static<T> void randomize(T[] arr) {
        Random rng = new Random();
        int[] randomInt = new int[arr.length];
        
        /* Fisher–Yates shuffle.
        In this randomization, the current object at the (decreasing) index
        is swapped with a random one between the first element (inclusive) and the object
        at the index (exclusive).
        */
        for (int i = randomInt.length - 1 ; i > 0 ; i--) {
            randomInt[i] = rng.nextInt(i + 1);
        }
        for (int i = arr.length - 1 ; i > 0; i--) {
            swap(arr, i, randomInt[i]);
        }
    }

    public static  int[] range(int to) {
        return range(0, to);
    }

    public static  int[] range(int from, int to) {
        int[] range = new int[to - from + 1];
        for (int i = from ; i < range.length ; i++) {
            range[i] = i;
        }
        return range;
    }

    public static<T> void replace(T[] arr, int index, T replacement) {
        arr[index] = replacement;
    }
    
    public static<T> boolean replaceAll(T[] arr, T toReplace, T replacement) {
        boolean replaced = false;
        int i = 0;
        for ( ; i < arr.length ; i++) {
            if (toReplace.equals(arr[i])) {
                arr[i] = replacement;
                replaced = true;
                break;
            }
        }
        
        for ( ; i < arr.length ; i++) {
            if (toReplace.equals(arr[i])) {
                arr[i] = replacement;
            }
        }
        
        return replaced;
    }
    
    public static <T> void reverse(T[] arr) {
        reverse(arr, 0, arr.length - 1);
    }

    public static<T> void reverse(T[] arr, int left, int right) {
        int mid = arr.length / 2;
        for (int l = left, r = right ; l < mid ; l++, r--) {
            swap(arr, l, r);
        }
    }
    
    @SafeVarargs
    public static<T> int smallestOf(T[]... arrs) {
        int smallestIndex = 0;
        for (int i = 1 ; i < arrs.length ; i++) {
            if (arrs[i].length < arrs[smallestIndex].length) {
                smallestIndex = i;
                if (arrs[i].length == 0) break;
            }
        }
        return smallestIndex;
    }
    
    @SuppressWarnings("unchecked")
    public static<T> T[] subArray(T[] arr, int start, int end) {
        T[] subArr = (T[]) new Object[end - start + 1];
        System.arraycopy(arr, start, subArr, 0, end - start + 1);
        return null;
    }
    
    public static<T> void swap(T[] arr, int index1, int index2) {
        T temp1 = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp1;
    }
    
    public static <T> String toCSV(T[] arr) {
        String stringVal = "";
        for (T t : arr) {
            stringVal += t + ",";
        }
        return stringVal.substring(0, stringVal.length() - 1);
        // trims the extra comma
    }
    
    public static <T> String toString(T[] arr) {
        String stringVal = "";
        for (T t : arr) {
            stringVal += t.toString() + ", ";
        }
        return "{" + stringVal.substring(0, stringVal.length() - 2) + "}";
        // trims the extra comma and space
    }
}
            
