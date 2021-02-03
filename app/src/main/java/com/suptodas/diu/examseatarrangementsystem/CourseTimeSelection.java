package com.suptodas.diu.examseatarrangementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseTimeSelection extends AppCompatActivity {

    Spinner levelSpinner, termSpinner, courseSpinner;
    TextView datePick;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String program, slot, semester, year, semesterNameText, exam, course, level, term;
    TextView semesterName, programName, slotName, examName;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReferenceExamInfo;
    List<String> courseString = new ArrayList<>();
    ArrayAdapter<String> courseAdapter;
    ProgressBar progressBarId;
    Button saveDataExamInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_time_selection);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Course");
        databaseReferenceExamInfo = firebaseDatabase.getReference("Exam");

        Intent intent = getIntent();
        program = intent.getStringExtra("program");
        slot = intent.getStringExtra("slot");
        semester = intent.getStringExtra("semester");
        year = intent.getStringExtra("year");
        exam = intent.getStringExtra("exam");
        semesterNameText = semester + " - " +year;

        databaseReferenceExamInfo = databaseReferenceExamInfo.child(semesterNameText).child(exam).child(program);

        datePick = findViewById(R.id.dateId);
        levelSpinner = findViewById(R.id.levelId);
        termSpinner = findViewById(R.id.termId);
        semesterName = findViewById(R.id.semesterName);
        programName = findViewById(R.id.programName);
        slotName = findViewById(R.id.slotName);
        examName = findViewById(R.id.examName);
        courseSpinner = findViewById(R.id.courseId);
        progressBarId = findViewById(R.id.progressBarId);
        saveDataExamInfo = findViewById(R.id.saveExamData);

        programName.setText(program);
        semesterName.setText(semesterNameText);
        slotName.setText(slot);
        examName.setText(exam);

        ArrayAdapter<CharSequence> levelAdapter = ArrayAdapter.createFromResource(this,
                R.array.level_string, android.R.layout.simple_spinner_item);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);

        ArrayAdapter<CharSequence> termAdapter = ArrayAdapter.createFromResource(this,
                R.array.term_string, android.R.layout.simple_spinner_item);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);

        termSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                level = levelSpinner.getSelectedItem().toString();
                String programLow = program.toLowerCase();

                if(i == 0){

                    if(level.equals("Level 1")){
                        showCourse(programLow, "L1T1");
                    }else if (level.equals("Level 2")){
                        showCourse(programLow, "L2T1");
                    }else if(level.equals("Level 3")){
                        showCourse(programLow, "L3T1");
                    }else {
                        showCourse(programLow, "L4T1");
                    }
                }
                else if (i == 1){

                    if(level.equals("Level 1")){
                        showCourse(programLow, "L1T2");
                    }else if (level.equals("Level 2")){
                        showCourse(programLow, "L2T2");
                    }else if(level.equals("Level 3")){
                        showCourse(programLow, "L3T2");
                    }else {
                        showCourse(programLow, "L4T2");
                    }
                }
                else {

                    if(level.equals("Level 1")){
                        showCourse(programLow, "L1T3");
                    }else if (level.equals("Level 2")){
                        showCourse(programLow, "L2T3");
                    }else if(level.equals("Level 3")){
                        showCourse(programLow, "L3T3");
                    }else {
                        showCourse(programLow, "L4T3");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CourseTimeSelection.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String day1, month1;

                if (day > 0 && day < 10){
                    day1 = "0"+day;
                }
                else {
                    day1 = String.valueOf(day);
                }

                if (month > 0 && month < 10){
                    month1 = "0" + month;
                }
                else{
                    month1 = String.valueOf(month);
                }

                String date = day1 + "-" + month1 + "-" + year;
                datePick.setText(date);
            }
        };

    }

    public void showCourse(String programChild, final String levelTermChild){

        databaseReference.child(programChild).child(levelTermChild).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                courseString.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    CourseSaveData value = dataSnapshot1.getValue(CourseSaveData.class);
                    courseString.add(value.getName());
                }

                courseAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, courseString);
                courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                courseSpinner.setAdapter(courseAdapter);
                progressBarId.setVisibility(View.GONE);
                saveDataExamInfo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveDataExamInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBarId.setVisibility(View.VISIBLE);

                String levelName = levelSpinner.getSelectedItem().toString();
                String termName = termSpinner.getSelectedItem().toString();
                String levelTermName = levelName +" "+termName;
                String dateName = datePick.getText().toString();

                if (dateName.equals("Select Date")){
                    progressBarId.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Select Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    SaveExamData saveExamData = new SaveExamData(
                            program,
                            exam,
                            semesterNameText,
                            datePick.getText().toString(),
                            slot,
                            courseSpinner.getSelectedItem().toString(),
                            levelTermName
                    );

                    databaseReferenceExamInfo.child(datePick.getText().toString()).child(slot).child(levelTermName).setValue(saveExamData);
                    progressBarId.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Exam Information Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}