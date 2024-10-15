package com.search.utils;

import com.search.entry.Items;
import javafx.collections.ObservableList;

import java.util.List;

public class Listutils {
    public static ObservableList<String> ListItemsToObservableList(ObservableList<String> list, List<Items> listItems) {
        for (Items listItem : listItems) {
            list.add(listItem.getTitle()+"               "+listItem.getDate());
        }
        return list;
    }
}
