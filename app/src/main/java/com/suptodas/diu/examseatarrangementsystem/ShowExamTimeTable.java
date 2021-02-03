package com.suptodas.diu.examseatarrangementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowExamTimeTable extends AppCompatActivity {

    ArrayList<String> slotname = new ArrayList<>();
    String semesterName, programName, dateName, examName;
    ListView listView;
    TextView sName, pName, dName, eName;
    CustomAdapter customAdapter;
    List<SaveExamData> data = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exam_time_table);

        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        slotname = intent.getStringArrayListExtra("slotArray");
        semesterName = intent.getStringExtra("semesterName");
        programName = intent.getStringExtra("programName");
        dateName = intent.getStringExtra("dateName");
        examName = intent.getStringExtra("examName");

        databaseReference = firebaseDatabase.getReference("Exam").child(semesterName).child(examName).child(programName).child(dateName);

        sName = findViewById(R.id.sNameId);
        pName = findViewById(R.id.pNameId);
        dName = findViewById(R.id.dNameId);
        eName = findViewById(R.id.eNameId);
        listView = findViewById(R.id.listViewData);

        sName.setText(semesterName);
        pName.setText(programName);
        dName.setText(dateName);
        eName.setText(examName);

        customAdapter = new CustomAdapter(ShowExamTimeTable.this, data);

//        databaseReference.child(slotname.get(0)).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                    SaveExamData saveExamData = dataSnapshot1.getValue(SaveExamData.class);
//                    data.add(saveExamData);
//                }
//
//                listView.setAdapter(customAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        for (int i = 0; i < slotname.size(); i++){

            databaseReference.child(slotname.get(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        SaveExamData value = dataSnapshot1.getValue(SaveExamData.class);
                        data.add(value);
                    }
                    listView.setAdapter(customAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

}