package com.example.studentslist.recyclerview;

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

import com.example.studentslist.MainActivity;
import com.example.studentslist.showprofile.ShowProfileActivity;
import com.example.studentslist.R;
import com.example.studentslist.Students;

import java.util.ArrayList;
import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.StudentsViewHolder> {

    private List<Students> students = new ArrayList<>();
    private Context context;

    RecyclerViewAdapter(Context context, List<Students> students) {
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
        studentsViewHolder.studentName.setText(students.get(i).name);
        studentsViewHolder.studentPhoto.setImageResource(students.get(i).photo);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class StudentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView studentName;
        ImageView studentPhoto;
        Button gitBtn;

        StudentsViewHolder(View itemView) {
            super(itemView);
            studentName = (TextView) itemView.findViewById(R.id.studentName);
            studentPhoto = (ImageView) itemView.findViewById(R.id.img);
            gitBtn = (Button) itemView.findViewById(R.id.gitBtn);

            gitBtn.setOnClickListener(this);
            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent intent;
            if (v.getId() == itemView.getId()) {

                if(MainActivity.getFlagAPI()){
                    intent = new Intent(context, ShowProfileActivity.class);
                    intent.putExtra("googlePlusID", students.get(getAdapterPosition()).googlePlusID);
                    context.startActivity(intent);
                }
                else
                    openGoogleProfile(getAdapterPosition());

            } else if (v.getId() == gitBtn.getId()) {

                if(MainActivity.getFlagAPI()){
                    intent = new Intent(context, ShowProfileActivity.class);
                    intent.putExtra("gitHubID", students.get(getAdapterPosition()).gitHubID);
                    context.startActivity(intent);
                }
                else
                    openGitHubProfile(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {

            removeItem(v, getAdapterPosition());
            return true;
        }
    }

    private void openGoogleProfile(int pos) {

        Uri address = Uri.parse("https://plus.google.com/" + students.get(pos).googlePlusID);
        Intent link = new Intent(Intent.ACTION_VIEW, address);
        context.startActivity(link);
    }

    private void openGitHubProfile(int pos) {

        Uri address = Uri.parse("https://github.com/" + students.get(pos).gitHubID);
        Intent link = new Intent(Intent.ACTION_VIEW, address);
        context.startActivity(link);
    }

    void removeItem(View v, final int currPosition) {

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
}