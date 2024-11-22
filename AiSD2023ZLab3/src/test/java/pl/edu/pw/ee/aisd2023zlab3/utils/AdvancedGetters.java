package pl.edu.pw.ee.aisd2023zlab3.utils;

import pl.edu.pw.ee.aisd2023zlab3.HashOpenAdressing;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;

import java.lang.reflect.Field;

public class AdvancedGetters {

    public static int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        
        try {
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashTable<String> createHashInstance(Class<? extends HashOpenAdressing> hashTableClass) {
        HashTable<String> hashInstance;

        try {
            hashInstance = (HashTable) hashTableClass.newInstance();

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException("Cannot create instance by reflection!", e);
        }

        return hashInstance;
    }
}
