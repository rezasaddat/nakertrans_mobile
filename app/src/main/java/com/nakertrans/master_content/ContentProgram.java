package com.nakertrans.master_content;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentProgram {

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
        return new ItemList(String.valueOf(position), "Program "+position,
                "Sumber Data " + position, convertDate(position),
                "pencapaian "+(position / 100)+"%");
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
        public final String content;
        public final String sumberdata;
        public final String pelaksanaan;
        public final String pencapaian;

        public ItemList(String id, String content, String sumberdata, String pelaksanaan,
                        String pencapaian) {
            this.id = id;
            this.content = content;
            this.sumberdata = sumberdata;
            this.pelaksanaan = pelaksanaan;
            this.pencapaian = pencapaian;
        }
    }
}
