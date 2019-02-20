package Kwetter.model.Models;

import java.util.Comparator;

public class KweetComparator implements Comparator<Kweet> {

    @Override
    public int compare(Kweet o1, Kweet o2) {
        return o1.compareTo(o2);
    }
}
