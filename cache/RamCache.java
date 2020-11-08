package cache;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class RamCache <KeyType, ValueType> implements
        ICache <KeyType, ValueType>, IFrequencyCallObject <KeyType>{

    private final HashMap<KeyType, ValueType> hashMap = new HashMap<>();
    private final TreeMap<KeyType, Integer> frequencyMap = new TreeMap<>();



    @Override
    public void cache(KeyType key, ValueType value) {
        frequencyMap.put(key, 1);
        hashMap.put(key, value);

    }

    @Override
    public ValueType getObject(KeyType key) {
        if (hashMap.containsKey(key)){
            int frequency = frequencyMap.get(key);
            frequencyMap.put(key, ++frequency);
            return hashMap.get(key);
        }
        return null;
    }

    @Override
    public void deleteObject(KeyType key) {
        if (hashMap.containsKey(key)){
            hashMap.remove(key);
            frequencyMap.remove(key);
        }
    }

    @Override
    public void clearCache() {
        hashMap.clear();
        frequencyMap.clear();
    }

    @Override
    public ValueType removedObject(KeyType key) {
        if (hashMap.containsKey(key)){
            ValueType result = this.getObject(key);
            this.deleteObject(key);
            return result;
        }
        return null;
    }

    @Override
    public boolean containsKey(KeyType key) {
        return hashMap.containsKey(key);
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public Set <KeyType> getMostFrequencyUsedKeys() {
        SpecialComparator comparatorClass = new SpecialComparator(frequencyMap);
        TreeMap<KeyType, Integer> sorted = new TreeMap(comparatorClass);
        sorted.putAll(frequencyMap);
        return sorted.keySet();
    }

    @Override
    public int getFrequencyOfCallingObject(KeyType key) {
        if (hashMap.containsKey(key)){
            return frequencyMap.get(key);
        }
        return 0;
    }
}
