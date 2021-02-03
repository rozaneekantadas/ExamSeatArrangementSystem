package com.suptodas.diu.examseatarrangementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class TimeTableSelection extends AppCompatActivity {

    Spinner programSpinner, semesterSpinner, yearSpinner, examSpinner, slotSpinner;
    Toolbar toolbar;
    String program, year, semester, exam, slot;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_selection);

        programSpinner = findViewById(R.id.programId);
        semesterSpinner = findViewById(R.id.semesterId);
        yearSpinner = findViewById(R.id.yearrId);
        examSpinner = findViewById(R.id.examId);
        toolbar = findViewById(R.id.toolBar);
        slotSpinner = findViewById(R.id.slotId);
        nextBtn = findViewById(R.id.nextButton);
        setSupportActionBar(toolbar);

        ArrayAdapter<CharSequence> programAdapter = ArrayAdapter.createFromResource(this,
                R.array.program_string, android.R.layout.simple_spinner_item);
        programAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        programSpinner.setAdapter(programAdapter);

        ArrayAdapter<CharSequence> semesterAdapter = ArrayAdapter.createFromResource(this,
                R.array.semester_string, android.R.layout.simple_spinner_item);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(semesterAdapter);

        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.year_string, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        ArrayAdapter<CharSequence> examAdapter = ArrayAdapter.createFromResource(this,
                R.array.exam_string, android.R.layout.simple_spinner_item);
        examAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        examSpinner.setAdapter(examAdapter);

        examSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                exam = adapterView.getItemAtPosition(i).toString();
                program = programSpinner.getSelectedItem().toString();

                if(i == 0){
                    if(program.equals("Day")){
                        ArrayAdapter<CharSequence> slotAdapter = ArrayAdapter.createFromResource(TimeTableSelection.this,
                                R.array.slotMidDay_string, android.R.layout.simple_spinner_item);
                        slotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        slotSpinner.setAdapter(slotAdapter);
                    }
                    if(program.equals("Evening")){
                        ArrayAdapter<CharSequence> slotAdapter = ArrayAdapter.createFromResource(TimeTableSelection.this,
                                R.array.slotMidEve_string, android.R.layout.simple_spinner_item);
                        slotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        slotSpinner.setAdapter(slotAdapter);
                    }
                }

                if(i == 1){
                    if(program.equals("Day")){
                        ArrayAdapter<CharSequence> slotAdapter = ArrayAdapter.createFromResource(TimeTableSelection.this,
                                R.array.slotFinalDay_string, android.R.layout.simple_spinner_item);
                        slotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        slotSpinner.setAdapter(slotAdapter);
                    }
                    if(program.equals("Evening")){
                        ArrayAdapter<CharSequence> slotAdapter = ArrayAdapter.createFromResource(TimeTableSelection.this,
                                R.array.slotFinalEve_string, android.R.layout.simple_spinner_item);
                        slotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        slotSpinner.setAdapter(slotAdapter);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                program = programSpinner.getSelectedItem().toString();
                year = yearSpinner.getSelectedItem().toString();
                semester = semesterSpinner.getSelectedItem().toString();
                slot = slotSpinner.getSelectedItem().toString();
                exam = examSpinner.getSelectedItem().toString();

                Intent intent = new Intent(getApplicationContext(), CourseTimeSelection.class);
                Bundle extras = new Bundle();
                extras.putString("program", program);
                extras.putString("year", year);
                extras.putString("semester", semester);
                extras.putString("slot", slot);
                extras.putString("exam", exam);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}