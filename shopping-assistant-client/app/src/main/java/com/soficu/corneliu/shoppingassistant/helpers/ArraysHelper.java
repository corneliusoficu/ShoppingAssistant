package com.soficu.corneliu.shoppingassistant.helpers;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ArraysHelper {
    public static <T> List<Pair<T,T>> convertToPairs(List<T> items){
        List<Pair<T,T>> pairsList = new ArrayList<>();
        int size = items.size();
        for(int index = 0; index < size; index+=2) {
            Pair<T,T> newPair;
            if(index + 1 < size) {
                newPair = new Pair<>(items.get(index), items.get(index+1));
            } else {
                newPair = new Pair<>(items.get(index), null);
            }
            pairsList.add(newPair);
        }
        return pairsList;
    }
}
