package cache;

import java.io.IOException;

public interface ICache <KeyType, ValueType> {

    void cache(KeyType key, ValueType value) throws IOException, ClassNotFoundException;

    ValueType getObject(KeyType key) throws IOException, ClassNotFoundException;

    void deleteObject(KeyType key);

    void clearCache();

    ValueType removedObject(KeyType key) throws IOException, ClassNotFoundException;

    boolean containsKey(KeyType key);

    int size();
}
