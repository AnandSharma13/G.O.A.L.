package com.ph.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ph.R;
import com.ph.Utils.DateOperations;
import com.ph.Utils.Dateutils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Anup on 1/15/2016.
 */
public class ActivityEntryMain extends AppCompatActivity {
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener datePicker;
    private EditText activityDate;
    private DateOperations dateOperations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_main);

        calendar = Calendar.getInstance();
        activityDate = (EditText) findViewById(R.id.activity_entry_date_text_view);



        dateOperations = new DateOperations(this);

        activityDate.setText(dateOperations.getUniformDateFormat().format(new Date()));



        datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        activityDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityEntryMain.this, datePicker, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                Dateutils dateutils = new Dateutils(ActivityEntryMain.this);

                datePickerDialog = dateutils.setGoalPeriodWeek(datePickerDialog);

                datePickerDialog.show();
            }
        });


    }

    private void updateLabel()
    {
        activityDate.setText(dateOperations.getUniformDateFormat().format(calendar.getTime()));
        Log.i("updateLabel", "Time has been set to the Edit Text");
    }

    public void activityClick(View view)
    {

        Intent intent = new Intent(ActivityEntryMain.this, ActivityEntryCreate.class);
        intent.putExtra("date",dateOperations.getMysqlDateFormat().format(calendar.getTime()));
        switch (view.getId())
        {
            case R.id.cardio_layout:
                intent.putExtra("key", "Cardio");
                startActivity(intent);
                break;
            case R.id.strength_layout:
                intent.putExtra("key", "Strength");
                startActivity(intent);
                break;
            case R.id.lifestyle_layout:
                intent.putExtra("key","Lifestyle");
                startActivity(intent);
                break;
        }
    }
}