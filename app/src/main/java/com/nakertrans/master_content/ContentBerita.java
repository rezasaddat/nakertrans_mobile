package com.nakertrans.master_content;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentBerita {

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
        return new ItemList(String.valueOf(position), "Berita Nakertrans "+position,
                "Gambar", convertDate(position), makeDetails(position));
    }

    private static String convertDate(int position){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append(df.format(date)).append(position);;
        return builder.toString();
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("<h3>Lorem Ipsum</h3> \n");
        for (int i = 0; i < 10; i++) {
            builder.append("is simply dummy text of the printing and typesetting " +
                    "industry. Lorem Ipsum has been the industry's standard dummy text ever since " +
                    "the 1500s, when an unknown printer took a galley of type and scrambled it to " +
                    "make a type specimen book. It has survived not only five centuries, but also " +
                    "the leap into electronic typesetting, remaining essentially unchanged. It was " +
                    "popularised in the 1960s with the release of Letraset sheets containing Lorem " +
                    "Ipsum passages, and more recently with desktop publishing software like Aldus " +
                    "PageMaker including versions of Lorem Ipsum.\n");
        }
        return builder.toString();
    }

    public static class ItemList {
        public final String id;
        public final String beritaJudul;
        public final String beritaGambar;
        public final String beritaCreated;
        public final String beritaDetails;

        public ItemList(String id, String judul, String gambar, String created, String detail) {
            this.id = id;
            this.beritaJudul = judul;
            this.beritaGambar = gambar;
            this.beritaCreated = created;
            this.beritaDetails = detail;
        }
    }
}
