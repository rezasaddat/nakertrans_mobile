package com.nakertrans.detail_content;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentDetailProgram {

    public static final List<ProgramDetailItem> DETAIL_ITEM_LIST = new ArrayList<ProgramDetailItem>();
    public static final Map<String, ProgramDetailItem> DETAIL_ITEM_MAP = new HashMap<String, ProgramDetailItem>();

    public static void populateData(int prevCount, int nextCount){
        for (int i = prevCount; i < nextCount; i++){
            addItem(createItem(i));
        }
    }

    private static void addItem(ProgramDetailItem detailItem){
        DETAIL_ITEM_LIST.add(detailItem);
        DETAIL_ITEM_MAP.put(detailItem.progid, detailItem);
    }

    private static ProgramDetailItem createItem(int position){
        return new ProgramDetailItem(String.valueOf(position), "123456789",
                "nama penduduk "+position, "bandung", convertDate(position),
                (position % 2 == 0 )?"Laki - laki":"Perempuan", (position % 2 == 0 )?"Tercapai":"Tidak Tercapai");
    }

    private static String convertDate(int position){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append(df.format(date));
        return builder.toString();
    }

    public static class ProgramDetailItem {
        public final String progid, prognik, prognama, progtempat, progtanggal_lahir,
                progjenis_kelamin, progtercapai;

        public ProgramDetailItem(String id, String nik, String nama, String tempat,
                          String tanggal_lahir, String jenis_kelamin, String tercapai) {
            this.progid = id;
            this.prognik = nik;
            this.prognama = nama;
            this.progtempat = tempat;
            this.progtanggal_lahir = tanggal_lahir;
            this.progjenis_kelamin = jenis_kelamin;
            this.progtercapai = tercapai;
        }
    }
}
