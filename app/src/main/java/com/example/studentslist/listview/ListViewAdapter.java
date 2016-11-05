package com.example.studentslist.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentslist.R;
import com.example.studentslist.Students;

import java.util.List;

import static com.example.studentslist.R.id.gitBtn;


class ListViewAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private LayoutInflater lInflater;
    private List<Students> students;

    private  int itemPos;
    ListViewAdapter(Context context, List<Students> students) {

        this.context = context;
        this.students = students;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_item_students_lv, parent, false);
        }

        ((ImageView) view.findViewById(R.id.img)).setImageResource(students.get(position).photo);
        ((TextView) view.findViewById(R.id.studentName)).setText(students.get(position).name);

        Button gitButton = (Button) view.findViewById(gitBtn);
        gitButton.setOnClickListener(this);

        itemPos = position;
        return view;
    }


    @Override
    public void onClick(View v) {

//        Button gitButton = (Button) v.findViewById(gitBtn);
//        LinearLayout ll = (LinearLayout)v.findViewById(R.id.item_layout);
//        if (v.getId() == ll.getId()) {
//
//            Uri address = Uri.parse("https://plus.google.com/" + students.get(itemPos).googlePlusID);
//            Intent link = new Intent(Intent.ACTION_VIEW, address);
//            context.startActivity(link);
//
//        } else if (v.getId() == gitButton.getId()) {
//
//            Uri address = Uri.parse("https://github.com/" + students.get(itemPos).gitHubID);
//            Intent link = new Intent(Intent.ACTION_VIEW, address);
//            context.startActivity(link);
//        }
    }
}
