package cache;

import java.util.Comparator;
import java.util.Map;

public class SpecialComparator implements Comparator {

    Map map;

    public <KeyType> SpecialComparator(Map map) {
        this.map = map;
    }



    @Override
    public int compare(Object o1, Object o2) {
        if ((Integer)map.get(o1) <= (Integer)map.get(o2)) {
            return 1;
        } else return -1;
    }
}
