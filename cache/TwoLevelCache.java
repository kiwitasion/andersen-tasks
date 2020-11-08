package cache;


import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class TwoLevelCache<KeyType, ValueType extends Serializable> implements ILeveledCache<KeyType, ValueType> {

    RamCache<KeyType, ValueType> ramCache;
    MemoryCache<KeyType, ValueType> memoryCache;
    int maxRamCacheCapacity;
    int numberOfRequests;
    int numberOfRequestsForRecache;

    public TwoLevelCache(int maxRamCacheCapacity, int numberOfRequestsForRecache) {
        this.maxRamCacheCapacity = maxRamCacheCapacity;
        this.numberOfRequestsForRecache = numberOfRequestsForRecache;
        ramCache = new RamCache<>();
        memoryCache = new MemoryCache<>();
        numberOfRequests = 0;
    }

    @Override
    public void recache() throws IOException {
        TreeSet<KeyType> ramKeySet = new TreeSet<>(ramCache.getMostFrequencyUsedKeys());
        int boundFrequency = 0;

        for (KeyType key : ramKeySet){
            boundFrequency += ramCache.getFrequencyOfCallingObject(key);
        }
        boundFrequency /= ramKeySet.size();

        for (KeyType key : ramKeySet){
            if (ramCache.getFrequencyOfCallingObject(key) <= boundFrequency){
                memoryCache.cache(key, ramCache.removedObject(key));
            }
        }

        TreeSet<KeyType> memoryKeySet = new TreeSet<>(memoryCache.getMostFrequencyUsedKeys());

        for (KeyType key : memoryKeySet){
            try {
                if (memoryCache.getFrequencyOfCallingObject(key) > boundFrequency){
                    ramCache.cache(key, memoryCache.removedObject(key));
                }
            } catch (IOException e){
                memoryCache.deleteObject(key);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void cache(KeyType key, ValueType value) {
        ramCache.cache(key, value);
    }

    @Override
    public ValueType getObject(KeyType key) throws IOException, ClassNotFoundException {
        if (ramCache.containsKey(key)) {
            numberOfRequests++;
            if (numberOfRequests > numberOfRequestsForRecache) {
                this.recache();
                numberOfRequests = 0;
            }
            return ramCache.getObject(key);
        }
        if (memoryCache.containsKey(key)){
            numberOfRequests++;
            if (numberOfRequests > numberOfRequestsForRecache) {
                this.recache();
                numberOfRequests = 0;
            }
            return memoryCache.getObject(key);
        }
        return null;
    }

    @Override
    public void deleteObject(KeyType key) {
        if (ramCache.containsKey(key)) {
            ramCache.deleteObject(key);
        }
        if (memoryCache.containsKey(key)) {
            memoryCache.deleteObject(key);
        }
    }

    @Override
    public void clearCache() {
        ramCache.clearCache();
        memoryCache.clearCache();
    }

    @Override
    public ValueType removedObject(KeyType key) throws IOException, ClassNotFoundException {
        if (ramCache.containsKey(key)) {
            return ramCache.removedObject(key);
        }
        if (memoryCache.containsKey(key)) {
            return memoryCache.removedObject(key);
        }
        return null;
    }

    @Override
    public boolean containsKey(KeyType key) {
        if (ramCache.containsKey(key)) {
            return true;
        }
        return memoryCache.containsKey(key);
    }

    @Override
    public int size() {
        return ramCache.size() + memoryCache.size();
    }

    @Override
    public Set<KeyType> getMostFrequencyUsedKeys() {
        TreeSet<KeyType> set = new TreeSet<>(ramCache.getMostFrequencyUsedKeys());
        set.addAll(memoryCache.getMostFrequencyUsedKeys());
        return set;
    }

    @Override
    public int getFrequencyOfCallingObject(KeyType key) {
        if (ramCache.containsKey(key)){
            return ramCache.getFrequencyOfCallingObject(key);
        }
        if (memoryCache.containsKey(key)){
            return memoryCache.getFrequencyOfCallingObject(key);
        }
        return 0;
    }
}
