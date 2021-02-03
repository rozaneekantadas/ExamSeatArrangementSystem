package com.suptodas.diu.examseatarrangementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCourseInfo extends AppCompatActivity {

    EditText levelTerm, courseName, programName;
    Button save;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_info);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Course");

        levelTerm = findViewById(R.id.levelTerm);
        courseName = findViewById(R.id.courseName);
        save = findViewById(R.id.saveButton);
        programName = findViewById(R.id.programNameId);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String lt = levelTerm.getText().toString();
                String name = courseName.getText().toString();
                String program = programName.getText().toString().toLowerCase();
                String key = databaseReference.push().getKey();

                if(lt.isEmpty() || name.isEmpty() || program.isEmpty()){
                    Toast.makeText(getApplicationContext(), "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else {
                    CourseSaveData value = new CourseSaveData(name);

                    databaseReference.child(program).child(lt).child(key).setValue(value);
                }
            }
        });

    }
}