package com.example.studentslist.listview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.studentslist.MainActivity;
import com.example.studentslist.R;
import com.example.studentslist.Students;

import java.util.List;

public class ListViewActivity extends MainActivity {

    ListViewAdapter listViewAdapter;

//    String[] listStud = {
//            "Винник Владислав",
//            "Теплий Михайло",
//            "Рибак Богдан",
//            "Лещенко Иван",
//            "Сакуров Павло",
//            "Воловик Руслан",
//            "Кириченко Дар'я",
//            "Рябко Андрій",
//            "Ситник Євгеній",
//            "Химич Эдгар",
//            "Бочкарьова Альона",
//            "Мигаль Руслан",
//            "Смалько Ірина",
//            "Губський Валерій",
//            "Жданов Євген",
//            "Сергеенко Иван",
//            "Пахаренко Ігор",
//            "Сторчак Олександр",
//            "Піхманець Микола",
//            "Лимарь Володимир"
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_main);

        ListView listV = (ListView) findViewById(R.id.listview_students);

//        ArrayAdapter<String> adapterStud = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, listStud);
        List<Students> students = new Students().getStudents();
        listViewAdapter = new ListViewAdapter(this, students);
        listV.setAdapter(listViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menu_RV) {
            finish();
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
