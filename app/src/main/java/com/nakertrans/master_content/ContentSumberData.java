package com.nakertrans.master_content;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentSumberData {

    public static final List<ItemList> ITEM_LISTS = new ArrayList<ItemList>();
    public static final Map<String, ItemList> ITEM_LIST_MAP = new HashMap<String, ItemList>();

    public static void populateData(int prevCount, int nextCount){
        for (int i = prevCount; i < nextCount; i++){
            addItem(createItem(i));
        }
    }

    private static void addItem(ItemList itemList){
        ITEM_LISTS.add(itemList);
        ITEM_LIST_MAP.put(itemList.id, itemList);
    }

    private static ItemList createItem(int position){
        return new ItemList(String.valueOf(position), "SumberData "+ position,
                "Total record " + (10 * position), convertDate(position));
    }

    private static String convertDate(int position){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append(df.format(date)).append(position);;
        return builder.toString();
    }

    public static class ItemList {
        public final String id;
        public final String table_name;
        public final String date_created;
        public final String total_record;

        public ItemList(String id, String table_name, String total_record, String date_created) {
            this.id = id;
            this.table_name = table_name;
            this.total_record = total_record;
            this.date_created = date_created;
        }
    }
}
