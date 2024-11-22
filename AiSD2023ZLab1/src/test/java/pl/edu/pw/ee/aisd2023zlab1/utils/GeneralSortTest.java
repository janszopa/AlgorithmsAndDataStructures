package pl.edu.pw.ee.aisd2023zlab1.utils;

import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public abstract class GeneralSortTest {

    protected Sorting sorter;

    public GeneralSortTest(Sorting sorter) {
        this.sorter = sorter;
    }

    @Test
    public void should_ThrowException_WhenInputIsNull() {
        // given
        double[] nums = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            sorter.sort(nums);
        });

        // then
        String message = "Input args (nums) cannot be null!";

        assertThat(exceptionCaught)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ReturnEmptyArray_WhenInputIsEmpty() {
        // given
        double[] nums = {};

        // when
        sorter.sort(nums);

        // then
        assertThat(nums).isEmpty();
    }

    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsRandomAndHuge() {
        // given
        int size = 10_000;
        double[] nums = createRandomData(size);
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

    private double[] createRandomData(int size) {
        assert size >= 0;

        double[] nums = new double[size];

        long eliteSeed = 31337;
        Random rand = new Random(eliteSeed);

        for (int i = 0; i < size; i++) {
            nums[i] = rand.nextDouble();
        }

        return nums;
    }
<<<<<<< HEAD

    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsAlreadySorted() {
        // given
        int size = 10_000;
        double[] nums = createSortedData(size); // zmieniamy w tym teście jedynie randomowe dane, na posortowane
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    private double[] createSortedData(int size) {
        assert size >= 0;

        double[] nums = new double[size];

        for (int i = 0; i < size; i++) {
            nums[i] = i;
        }

        return nums;
    }

    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsSortedBackwards() {
        // given
        int size = 10_000;
        double[] nums = createSortedData(size); // zmieniamy w tym teście dane posortowane na posortowane odwrotnie 100, 99, 98 ...
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    private double[] createDataSortedBackwards(int size) {
        assert size >= 0;

        double[] nums = new double[size];

        for (int i = size; i >= 0; i--) {
            nums[i] = i;
        }

        return nums;
    }

    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsOneElem() {
        // given
        double[] nums = {0}; // zmieniamy w tym teście jedynie randomowe dane, na posortowane
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

=======
>>>>>>> b9c147a (sortowanie lab1)
}
