package pl.edu.pw.ee.aisd2023zlab3;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.createHashInstance;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getNumOfElems;

public abstract class GeneralHashTest {

    private final Class<? extends HashOpenAdressing> HashTableClass;

    private HashTable<String> hashTableStrings;
    protected GeneralHashTest(Class<? extends HashOpenAdressing> HashTableClass){
        this.HashTableClass = HashTableClass;
    }

    @Before
    public void setup(){ hashTableStrings = createHashInstance(HashTableClass);}

    @Test
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            new HashLinearProbing<>(initialSize);
        });

        // then
        String message = "Initial size of hash table cannot be lower than 1!";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        String newEleme = "P. Czarnek";

        // when
        int nOfElemsBeforePut = getNumOfElems(hashTableStrings);
        hashTableStrings.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(hashTableStrings);

        // then
        assertThat(nOfElemsBeforePut).isEqualTo(0);
        assertThat(nOfElemsAfterPut).isEqualTo(1);
    }

    @Test
    public void should_CorrectlyGetElems(){
        String elem1 = "jan";
        String elem2 = "piotr";

        hashTableStrings.put(elem1);
        hashTableStrings.put(elem2);

        assertThat(hashTableStrings.get(elem1)).isEqualTo("jan");
        assertThat(hashTableStrings.get(elem2)).isEqualTo("piotr");
    }

    @Test
    public void should_CorrectlyDeleteElems(){
        String elem1 = "jan";
        String elem2 = "piotr";
        String elem3 = "jakub";

        hashTableStrings.put(elem1);
        hashTableStrings.put(elem2);
        hashTableStrings.put(elem3);

        hashTableStrings.delete(elem1);
        hashTableStrings.delete(elem2);
        hashTableStrings.delete(elem3);

        assertThat(hashTableStrings.get(elem1)).isEqualTo(null); //metoda get zwraca stałą nil (równą null) gdy nie znajdzie szukanego elementu
        assertThat(hashTableStrings.get(elem2)).isEqualTo(null);
        assertThat(hashTableStrings.get(elem3)).isEqualTo(null);
    }

    @Test
    public void should_CorrectlyGetElems_WhenElemBeforeThemWasDeleted(){
        String elem1 = "jan";
        String elem2 = "piotr";
        String elem3 = "jakub";
        String elem4 = "artur";

        hashTableStrings.put(elem1);
        hashTableStrings.put(elem2);
        hashTableStrings.put(elem3);
        hashTableStrings.put(elem4);

        hashTableStrings.delete(elem1);
        hashTableStrings.delete(elem3);

        assertThat(hashTableStrings.get(elem2)).isEqualTo("piotr");
        assertThat(hashTableStrings.get(elem4)).isEqualTo("artur");
    }
}
