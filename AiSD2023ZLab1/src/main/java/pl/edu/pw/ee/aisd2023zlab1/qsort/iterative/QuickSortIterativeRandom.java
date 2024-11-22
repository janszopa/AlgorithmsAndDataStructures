package pl.edu.pw.ee.aisd2023zlab1.qsort.iterative;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

=======
>>>>>>> b9c147a (sortowanie lab1)
public class QuickSortIterativeRandom implements Sorting{

    @Override
    public void sort(double[] nums) {
<<<<<<< HEAD
        if (nums == null) {
            throw new IllegalArgumentException("Input args (nums) cannot be null!");
        }

        quicksortRandom(nums);
    }
    private void quicksortRandom(double[] data) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();

        Integer left = 0;
        Integer right = data.length - 1;

        starts.add(left);
        ends.add(right);

        int n = 1;
        int pivot;

        if (left < right) {

            while (n > 0) {
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
    private int partition(double[] nums, int start, int end) {
        int randomIndex = randomIndex(start, end); //TODO
        swap(nums, start, randomIndex);
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
    }

    private int randomIndex(int start, int end){
        Random random = new Random();
        int randomIndex = random.nextInt (end - start + 1) + start;

        return randomIndex;
    }
=======
        throw new UnsupportedOperationException("TODO QSort random.");
    }
    
>>>>>>> b9c147a (sortowanie lab1)
}
