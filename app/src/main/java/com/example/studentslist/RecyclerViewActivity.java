package com.example.studentslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.studentslist.R.id.recyclerView;


public class RecyclerViewActivity extends MainActivity {

    RVAdapter adapter;
    private List<Students> students;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_main);
        rv = (RecyclerView) findViewById(recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initialiseData();

        adapter = new RVAdapter(this, students);
        rv.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(rv);
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return true;
                    }

                    @Override
                    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {

                        adapter.removeItem(viewHolder.itemView, viewHolder.getAdapterPosition());

                    }
                };
        return simpleItemTouchCallback;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_LV:
                Intent intent = new Intent(this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.refreshMain:
                initialiseData();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initialiseData() {
        students = new ArrayList<>();
        students.add(new Students("Винник Владислав",
                "https://plus.google.com/117765348335292685488",
                "https://github.com/vlads0n",
                R.drawable.vinik));
        students.add(new Students("Теплий Михайло",
                "https://plus.google.com/110313151428733681846",
                "https://github.com/RedGeekPanda",
                R.drawable.tyoply));
        students.add(new Students("Рибак Богдан",
                "https://plus.google.com/103145064185261665176",
                "https://github.com/BogdanRybak1996",
                R.drawable.rybak));
        students.add(new Students("Лещенко Иван",
                "https://plus.google.com/111088051831122657934",
                "https://github.com/ivleshch",
                R.drawable.leshenko));
        students.add(new Students("Сакуров Павло",
                "https://plus.google.com/108482088578879737406",
                "https://github.com/sakurov",
                R.drawable.sakurov));
        students.add(new Students("Воловик Руслан",
                "https://plus.google.com/109719711261293841416",
                "https://github.com/RuslanVolovyk",
                R.drawable.lis));
        students.add(new Students("Кириченко Дар'я",
                "https://plus.google.com/103130382244571139113",
                "https://github.com/dashakdsr",
                R.drawable.kirichenko));
        students.add(new Students("Рябко Андрій",
                "https://plus.google.com/110288437168771810002",
                "https://github.com/RyabkoAndrew",
                R.drawable.ryabko));
        students.add(new Students("Ситник Євгеній",
                "https://plus.google.com/101427598085441575303",
                "https://github.com/YevheniiSytnyk",
                R.drawable.sytnyk));
        students.add(new Students("Химич Эдгар",
                "https://plus.google.com/102197104589432395674",
                "https://github.com/lyfm",
                R.drawable.khimich));
        students.add(new Students("Бочкарьова Альона",
                "https://plus.google.com/107382407687723634701",
                "https://github.com/HelenCool",
                R.drawable.bo4karova));
        students.add(new Students("Мигаль Руслан",
                "https://plus.google.com/106331812587299981536",
                "https://github.com/rmigal",
                R.drawable.migal));
        students.add(new Students("Смалько Ірина",
                "https://plus.google.com/113994208318508685327",
                "https://github.com/IraSmalko",
                R.drawable.smalko));
        students.add(new Students("Губський Валерій",
                "https://plus.google.com/107910188078571144657",
                "https://github.com/gvv-ua",
                R.drawable.gubskyi));
        students.add(new Students("Жданов Євген",
                "https://plus.google.com/113264746064942658029",
                "https://github.com/zhdanov-ek",
                R.drawable.zdanov));
        students.add(new Students("Сергеенко Иван",
                "https://plus.google.com/111389859649705526831",
                "https://github.com/dogfight81",
                R.drawable.segreenko));
        students.add(new Students("Пахаренко Ігор",
                "https://plus.google.com/108231952557339738781",
                "https://github.com/IhorPakharenko",
                R.drawable.pakharenko));
        students.add(new Students("Сторчак Олександр",
                "https://plus.google.com/106553086375805780685",
                "https://github.com/new15",
                R.drawable.storchak));
        students.add(new Students("Піхманець Микола",
                "https://plus.google.com/110087894894730430086",
                "https://github.com/NikPikhmanets",
                R.drawable.pikhmanets));
        students.add(new Students("Лимарь Володимир",
                "https://plus.google.com/109227554979939957830",
                "https://github.com/VovanNec",
                R.drawable.limar));
    }
}
