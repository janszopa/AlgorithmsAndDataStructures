package pl.edu.pw.ee.aisd2023zlab1.qsort.iterative;

<<<<<<< HEAD
import pl.edu.pw.ee.aisd2023zlab1.InsertionSort;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class QuickSortIterativeWithInSort implements Sorting {
    private void validateParams(double[] nums) {
        if (isNull(nums)) {
            throw new RuntimeException("Input args (nums) cannot be null!");
        }
    }
    @Override
    public void sort(double[] nums) {
        validateParams(nums);

        quicksortWithInsort(nums);
    }

    private void quicksortWithInsort(double[] data) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();

        Integer left = 0;
        Integer right = data.length - 1;

        starts.add(left);
        ends.add(right);

        int n = 1;
        int pivot;

        InsertionSort insertionSort = new InsertionSort();

        if (left < right) {

            while (n > 0) {
                if(right - left < 25){
                    insertionSort.sort(data);
                    break;
                } else {
                    n--;
                    left = starts.remove(n);
                    right = ends.remove(n);
                    pivot = partition(data, left, right);

                    if (pivot > left) {
                        starts.add(left);
                        ends.add(pivot);
                        n++;
                    }

                    if (pivot + 1 < right) {
                        starts.add(pivot + 1);
                        ends.add(right);
                        n++;
                    }
                }
            }
        }
    }

    private int partition(double[] nums, int start, int end) {
        double pivot = nums[start];

        int left = start - 1;
        int right = end + 1;

        while (true) {

            while (nums[++left] < pivot) {
            }

            while (nums[--right] > pivot) {
            }

            if (left < right) {
                swap(nums, left, right);
            } else {
                break;
            }

        }

        return right;
    }

    private void swap(double[] data, int firstId, int secondId) {
        if (firstId != secondId) {
            double firstValue = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstValue;
        }
=======
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class QuickSortIterativeWithInSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        throw new UnsupportedOperationException("TODO QSort with insertion sort.");
>>>>>>> b9c147a (sortowanie lab1)
    }

}
