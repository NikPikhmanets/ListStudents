package com.example.studentslist_taskhome;

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

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.StudentsViewHolder>{

    private Context context;
    private int glbPosition;

    public class StudentsViewHolder extends RecyclerView.ViewHolder {

        TextView sdutentName;
        ImageView studentPhoto;
        private Button gitBtn;

        StudentsViewHolder(View itemView) {
            super(itemView);
            sdutentName = (TextView) itemView.findViewById(R.id.studentName);
            studentPhoto = (ImageView) itemView.findViewById(R.id.img);
            gitBtn = (Button) itemView.findViewById(R.id.gitBtn);
        }
    }

    List<Students> students = new ArrayList<>();

    RVAdapter(Context contex, List<Students> students) {
        this.context = contex;
        this.students = students;
    }

    @Override
    public StudentsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_students, viewGroup, false);
        return new StudentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final StudentsViewHolder studentsViewHolder, int i) {
        studentsViewHolder.sdutentName.setText(students.get(i).name);
        studentsViewHolder.studentPhoto.setImageResource(students.get(i).photo);

        final int position = i;//studentsViewHolder.getAdapterPosition();
        final Students studInfo = students.get(i);

        studentsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                openGLink(position);
                openGLink(studInfo);
            }
        });

        studentsViewHolder.sdutentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                openGLink(position);
                openGLink(studInfo);
            }
        });

        studentsViewHolder.gitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                openGITLink(position);
                openGITLink(studInfo);
            }
        });

        studentsViewHolder.sdutentName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                removeItem(studInfo);
                Snackbar.make(v, "Cтудент удален.", Snackbar.LENGTH_LONG)
                        .setAction("Вернуть!", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addItem(position, studInfo);
                                Snackbar.make(v, "Сделано!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            }
                        }).show();
                return true;
            }
        });

        studentsViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                removeItem(studInfo);
                Snackbar.make(v, "Cтудент удален.", Snackbar.LENGTH_LONG)
                        .setAction("Вернуть!", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addItem(position, studInfo);
                                Snackbar.make(v, "Сделано!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            }
                        }).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    private void openGLink(/*int pos*/Students info) {

        int pos = students.indexOf(info);
        Uri address = Uri.parse(students.get(pos).gLink);
        Intent link = new Intent(Intent.ACTION_VIEW, address);
        context.startActivity(link);
    }

    private void openGITLink(/*int pos*/Students info) {

        int pos = students.indexOf(info);
        glbPosition = students.indexOf(info);
        Uri address = Uri.parse(students.get(pos).gitLink);
        Intent link = new Intent(Intent.ACTION_VIEW, address);
        context.startActivity(link);
    }

    private void removeItem(Students info) {

//        int currPosition = students.indexOf(info);
//        students.remove(currPosition);
//        notifyItemRemoved(currPosition);

        glbPosition = students.indexOf(info);
        students.remove(glbPosition);
        notifyItemRemoved(glbPosition);
    }

    private void addItem(int pos, Students info) {

//        students.add(pos, info);
//        notifyItemInserted(pos);

        students.add(glbPosition, info);
        notifyItemInserted(glbPosition);
    }

    public void remove(View v, final int currPosition) {

        final Students info = students.get(currPosition);
        students.remove(currPosition);
        notifyItemRemoved(currPosition);

        Snackbar.make(v, "Cтудент удален.", Snackbar.LENGTH_LONG)
                .setAction("Вернуть!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        students.add(currPosition, info);
                        notifyItemInserted(currPosition);
                        Snackbar.make(v, "Сделано!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }).show();
    }
}