package pl.edu.pw.ee.aisd2023zlab3;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        key = key & Integer.MAX_VALUE;

        int hash = (key % m + i + i * i) % m;

        return hash;
    }

}
