package com.nakertrans.processing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.nakertrans.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class ProgramsForm extends AppCompatActivity {
    public EditText namaProgram, sumberValueKriteria, tanggalMulai, tanggalSelesai;
    public Spinner sumberData, sumberKriteria;
    public ImageButton btnTglMulai, btnTglSelesai;
    public Button submitButton;
    public DatePickerDialog datePickerDialog;
    public SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs_form);

        tanggalMulai = findViewById(R.id.progTanggalMulai);
        tanggalSelesai = findViewById(R.id.progTanggalSelesai);
        btnTglMulai = findViewById(R.id.btnTanggalMulai);
        btnTglSelesai = findViewById(R.id.btnTanggalSelesai);

        btnTglMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(tanggalMulai);
            }
        });

        btnTglSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(tanggalSelesai);
            }
        });

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
        return true;
    }

    private void showDateDialog(final EditText editText){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                dateFormatter = new SimpleDateFormat("dd-MM-YYYY");
                editText.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
