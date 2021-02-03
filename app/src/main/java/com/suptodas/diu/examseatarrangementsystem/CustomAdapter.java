package com.suptodas.diu.examseatarrangementsystem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<SaveExamData> {

    Activity activity;
    List<SaveExamData> examData;

    public CustomAdapter(@NonNull Activity activity, List<SaveExamData> examData) {
        super(activity, R.layout.sample_list_view_layout,  examData);
        this.activity = activity;
        this.examData = examData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.sample_list_view_layout, null, true);

        SaveExamData data = examData.get(position);

        TextView slot = view.findViewById(R.id.sample_slot_name);
        TextView levelTerm = view.findViewById(R.id.sample_level_term);
        TextView courseName = view.findViewById(R.id.sample_courseName);

        slot.setText(data.getSlot());
        levelTerm.setText(data.getLevelTerm());
        courseName.setText(data.getCourseName());

        return view;
    }
}
