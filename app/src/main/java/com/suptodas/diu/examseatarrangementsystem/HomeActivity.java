package com.suptodas.diu.examseatarrangementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button goToPrintButton, goToSaveExamInfoButton, goToAddCourseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        goToPrintButton = findViewById(R.id.printActivity);
        goToSaveExamInfoButton = findViewById(R.id.saveExamActivity);
        goToAddCourseInfo = findViewById(R.id.addCourseInfo);

        goToPrintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowExamData.class);
                startActivity(intent);
            }
        });

        goToSaveExamInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TimeTableSelection.class);
                startActivity(intent);
            }
        });

        goToAddCourseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCourseInfo.class);
                startActivity(intent);
            }
        });
    }
}