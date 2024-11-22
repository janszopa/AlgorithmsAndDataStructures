package pl.edu.pw.ee.aisd2023zlab3;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {
    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
    }
    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        int mPrim = m - 2;

        key = key & Integer.MAX_VALUE;
        int h1 = key % m;
        int h2 = 1 + (key % mPrim);

        int hash = (h1 + i * h2) % m;

        return hash;
    }

}
