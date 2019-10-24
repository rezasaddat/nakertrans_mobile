package com.nakertrans.detail_content;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentDetailSumberData {

    public static final List<DetailItem> DETAIL_ITEM_LIST = new ArrayList<DetailItem>();
    public static final Map<String, DetailItem> DETAIL_ITEM_MAP = new HashMap<String, DetailItem>();

    public static void populateData(int prevCount, int nextCount){
        for (int i = prevCount; i < nextCount; i++){
            addItem(createItem(i));
        }
    }

    private static void addItem(DetailItem detailItem){
        DETAIL_ITEM_LIST.add(detailItem);
        DETAIL_ITEM_MAP.put(detailItem.id, detailItem);
    }

    private static DetailItem createItem(int position){
        return new DetailItem(String.valueOf(position), "123456789",
                "nama penduduk "+position, "bandung", convertDate(position),
                (position % 2 == 0 )?"Laki - laki":"Perempuan");
    }

    private static String convertDate(int position){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append(df.format(date));
        return builder.toString();
    }

    public static class DetailItem {
        public final String id, nik, nama, tempat, tanggal_lahir, jenis_kelamin;

        public DetailItem(String id, String nik, String nama, String tempat,
                          String tanggal_lahir, String jenis_kelamin) {
            this.id = id;
            this.nik = nik;
            this.nama = nama;
            this.tempat = tempat;
            this.tanggal_lahir = tanggal_lahir;
            this.jenis_kelamin = jenis_kelamin;
        }
    }
}
