package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;






public class BSearchTest {
    private BSearch bSearch;

    @Before
    public void setUp(){
        bSearch = new BSearch();
    };

    @Test
    public void nazwaTestu(){
        int [] nums = {1,2,3,3,4,5};
        int toFind = 0;

        int result = bSearch.search(nums, toFind);
    }


}