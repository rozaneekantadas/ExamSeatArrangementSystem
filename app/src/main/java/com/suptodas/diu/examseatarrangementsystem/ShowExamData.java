package com.suptodas.diu.examseatarrangementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowExamData extends AppCompatActivity {

    Spinner semesterSpinner, dateSpinner, slotSpinner, programSpinner, examSpinner, levelTermSpinner;
    Button searchButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<String> semesterName = new ArrayList<>();
    List<String> examName = new ArrayList<>();
    List<String> programName = new ArrayList<>();
    List<String> dateName = new ArrayList<>();
    List<String> slotName = new ArrayList<>();
    List<String> levelTermName = new ArrayList<>();
    ArrayList<String> dataFromFirebase = new ArrayList<>();

    DatabaseReference databaseReference1;
    DatabaseReference databaseReference2;
    DatabaseReference databaseReference3;
    DatabaseReference databaseReference4;
    DatabaseReference databaseReference5;
    DatabaseReference databaseReference6;
    DatabaseReference databaseReferenceExamData;

    ArrayAdapter<String> semesterAdapter;
    ArrayAdapter<String> examAdapter;
    ArrayAdapter<String> programAdapter;
    ArrayAdapter<String> dateAdapter;
    ArrayAdapter<String> slotAdapter;
    ArrayAdapter<String> levelTermAdapter;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exam_data);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Exam");

        semesterSpinner = findViewById(R.id.semesterDataSpinner);
        dateSpinner = findViewById(R.id.dateDataSpinner);
        slotSpinner = findViewById(R.id.slotDataSpinner);
        programSpinner = findViewById(R.id.programDataSpinner);
        examSpinner = findViewById(R.id.examDataSpinner);
        levelTermSpinner = findViewById(R.id.levelTermSpinner);
        searchButton = findViewById(R.id.searchButton);
        progressBar = findViewById(R.id.progressBarId2);


        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                databaseReference1 = databaseReference.child(adapterView.getItemAtPosition(i).toString());
                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        examName.clear();
                        for (DataSnapshot ds1 : dataSnapshot.getChildren()){
                            examName.add(ds1.getKey());
                        }
                        examAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, examName);;
                        examAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        examSpinner.setAdapter(examAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        examSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                databaseReference2 = databaseReference1.child(adapterView.getItemAtPosition(i).toString());
                databaseReference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        programName.clear();
                        for (DataSnapshot ds1 : dataSnapshot.getChildren()){
                            programName.add(ds1.getKey());
                        }
                        programAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, programName);;
                        programAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        programSpinner.setAdapter(programAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        programSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                databaseReference3 = databaseReference2.child(adapterView.getItemAtPosition(i).toString());
                databaseReference3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dateName.clear();
                        for (DataSnapshot ds1 : dataSnapshot.getChildren()){
                            dateName.add(ds1.getKey());
                        }
                        dateAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, dateName);;
                        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dateSpinner.setAdapter(dateAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReferenceExamData = databaseReference.child(semesterSpinner.getSelectedItem().toString())
                        .child(examSpinner.getSelectedItem().toString())
                        .child(programSpinner.getSelectedItem().toString())
                        .child(dateSpinner.getSelectedItem().toString());

                databaseReferenceExamData.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataFromFirebase.clear();

                        for (DataSnapshot dsData : dataSnapshot.getChildren()){
                            dataFromFirebase.add(dsData.getKey());
                        }

                        Intent intent = new Intent(getApplicationContext(), ShowExamTimeTable.class);
                        Bundle extras = new Bundle();
                        extras.putStringArrayList("slotArray", dataFromFirebase);
                        extras.putString("semesterName", semesterSpinner.getSelectedItem().toString());
                        extras.putString("examName", examSpinner.getSelectedItem().toString());
                        extras.putString("programName", programSpinner.getSelectedItem().toString());
                        extras.putString("dateName", dateSpinner.getSelectedItem().toString());
                        intent.putExtras(extras);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

//        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                databaseReference4 = databaseReference3.child(adapterView.getItemAtPosition(i).toString());
//                databaseReference4.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        slotName.clear();
//
//                        for (DataSnapshot ds1 : dataSnapshot.getChildren()){
//                            slotName.add(ds1.getKey());
//                        }
//                        slotAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, slotName);;
//                        slotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        slotSpinner.setAdapter(slotAdapter);
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        slotSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                databaseReference5 = databaseReference4.child(adapterView.getItemAtPosition(i).toString());
//                databaseReference5.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        levelTermName.clear();
//
//                        for (DataSnapshot ds1 : dataSnapshot.getChildren()){
//                            levelTermName.add(ds1.getKey());
//                        }
//                        levelTermAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, levelTermName);;
//                        levelTermAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        levelTermSpinner.setAdapter(levelTermAdapter);
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                semesterName.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    semesterName.add(dataSnapshot1.getKey());
                }
                semesterAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, semesterName);
                semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                semesterSpinner.setAdapter(semesterAdapter);
                progressBar.setVisibility(View.GONE);
                searchButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}