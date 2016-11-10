package com.example.studentslist.listview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentslist.R;
import com.example.studentslist.Students;
import com.example.studentslist.showprofile.ShowProfileActivity;

import java.util.List;

import static com.example.studentslist.R.id.gitBtn;


class ListViewAdapter extends BaseAdapter {

    private LayoutInflater lInflater;
    private List<Students> students;
    private boolean api;

    ListViewAdapter(Context context, List<Students> students, boolean api) {
        this.students = students;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.api = api;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_item_students_lv, parent, false);
        }

        ((ImageView) view.findViewById(R.id.img)).setImageResource(students.get(position).photo);
        ((TextView) view.findViewById(R.id.studentName)).setText(students.get(position).name);

        Button gitButton = (Button)view.findViewById(gitBtn);

        gitButton.setOnClickListener(new View.OnClickListener() {

            Intent intent;
            @Override
            public void onClick(View v) {

                if(api){
                    intent = new Intent(v.getContext(), ShowProfileActivity.class);
                    intent.putExtra("googlePlusID", students.get(position).googlePlusID);
                    v.getContext().startActivity(intent);
                }
                else {
                    Uri address = Uri.parse("https://github.com/" + students.get(position).gitHubID);
                    Intent link = new Intent(Intent.ACTION_VIEW, address);
                    v.getContext().startActivity(link);
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {

            Intent intent;
            @Override
            public void onClick(View v) {

                if (api) {

                    intent = new Intent(v.getContext(), ShowProfileActivity.class);
                    intent.putExtra("gitHubID", students.get(position).gitHubID);
                    v.getContext().startActivity(intent);
                } else {

                    Uri address = Uri.parse("https://plus.google.com/" + students.get(position).googlePlusID);
                    Intent link = new Intent(Intent.ACTION_VIEW, address);
                    v.getContext().startActivity(link);
                }
            }
        });

        return view;
    }
}
