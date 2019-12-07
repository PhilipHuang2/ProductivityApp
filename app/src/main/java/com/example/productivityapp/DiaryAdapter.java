package com.example.productivityapp;
//Author Philip Huang
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DiaryAdapter extends ArrayAdapter<Diary> {
    private Context mContext;
    private List<Diary> contactList;

    public DiaryAdapter(Context context, ArrayList<Diary> list)
    {
        super(context, 0, list);
        mContext = context;
        contactList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.diary_view, parent, false);

        Diary currentDiary = contactList.get(position);

        TextView paragraph = listItem.findViewById(R.id.text_view_paragraph);
        paragraph.setText(currentDiary.getDiary());

        TextView date = listItem.findViewById((R.id.text_view_date));
        date.setText(currentDiary.getMonth() + "/" + currentDiary.getDay() + "/" + currentDiary.getYear());

        return listItem;
    }
}
