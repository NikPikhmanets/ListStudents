package com.example.studentslist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.StudentsViewHolder> {

    List<Students> students = new ArrayList<>();
    private Context context;
    private int glbPosition;

    RVAdapter(Context context, List<Students> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public StudentsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_students_rv, viewGroup, false);
        return new StudentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final StudentsViewHolder studentsViewHolder, int i) {
        studentsViewHolder.sdutentName.setText(students.get(i).name);
        studentsViewHolder.studentPhoto.setImageResource(students.get(i).photo);

        glbPosition = i;
//        glbPosition = studentsViewHolder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    private void openGLink(Students info) {

        int pos = students.indexOf(info);
        Uri address = Uri.parse(students.get(pos).gLink);
        Intent link = new Intent(Intent.ACTION_VIEW, address);
        context.startActivity(link);
    }

    private void openGITLink(Students info) {

        int pos = students.indexOf(info);
        glbPosition = students.indexOf(info);
        Uri address = Uri.parse(students.get(pos).gitLink);
        Intent link = new Intent(Intent.ACTION_VIEW, address);
        context.startActivity(link);
    }

    public void removeItem(View v, final int currPosition) {

        final Students info = students.get(currPosition);
        students.remove(currPosition);
        notifyItemRemoved(currPosition);

        Snackbar.make(v, "Студент удален.", Snackbar.LENGTH_LONG)
                .setAction("Отменить!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        students.add(currPosition, info);
                        notifyItemInserted(currPosition);
                        Snackbar.make(v, "Сделано!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }).show();
    }

    public class StudentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        final Students studInfo = students.get(glbPosition);
        TextView sdutentName;
        ImageView studentPhoto;
        Button gitBtn;

        StudentsViewHolder(View itemView) {
            super(itemView);
            sdutentName = (TextView) itemView.findViewById(R.id.studentName);
            studentPhoto = (ImageView) itemView.findViewById(R.id.img);
            gitBtn = (Button) itemView.findViewById(R.id.gitBtn);

            sdutentName.setOnClickListener(this);
            studentPhoto.setOnClickListener(this);
            gitBtn.setOnClickListener(this);
            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == itemView.getId() || v.getId() == sdutentName.getId() || v.getId() == sdutentName.getId()) {

                openGLink(studInfo);

            } else if (v.getId() == gitBtn.getId()) {

                openGITLink(studInfo);
            }
        }

        @Override
        public boolean onLongClick(View v) {

            removeItem(v, glbPosition);
            return true;
        }
    }
}