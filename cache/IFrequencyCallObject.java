package cache;

import java.util.Set;

public interface IFrequencyCallObject<KeyType> {
    Set<KeyType> getMostFrequencyUsedKeys();
    int getFrequencyOfCallingObject(KeyType key);
}
