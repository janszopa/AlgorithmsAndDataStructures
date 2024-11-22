package pl.edu.pw.ee.aisd2023zlab2;

public class HashListChainingMultiplicativeHashing<T extends Comparable<T>> extends HashListChaining<T>{

    public HashListChainingMultiplicativeHashing() {
        super();
    }

    public HashListChainingMultiplicativeHashing(int size) {
        super(size);
    }

    static double A = (Math.sqrt(5) - 1) / 2;
    @Override
    int countHashId(T value) {
        int hashCode = value.hashCode();
        int K = hashCode & Integer.MAX_VALUE;

        return (int) ( size * ( (K * A) % 1 ) );
    }
}
