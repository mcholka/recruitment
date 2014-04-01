package com.recruitment.data.store.control;

import javax.faces.model.SelectItem;
import java.util.Comparator;

/**
 * Created by mcholka on 2014-03-26. Enjoy!
 */
public class SelectItemComparator implements Comparator<SelectItem> {
    @Override
    public int compare(SelectItem o1, SelectItem o2) {
        return o1.getLabel().compareTo(o2.getLabel());
    }
}
