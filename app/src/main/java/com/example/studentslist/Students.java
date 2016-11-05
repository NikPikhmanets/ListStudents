package com.example.studentslist;

import java.util.ArrayList;
import java.util.List;

public class Students {
    public String name;
    public String googlePlusID;
    public String gitHubID;
    public int photo;

    private List<Students> students;

    public Students() {
    }

    private Students(String name, String googlePlusID, String gitHubID, int photo) {
        this.name = name;
        this.googlePlusID = googlePlusID;
        this.gitHubID = gitHubID;
        this.photo = photo;
    }

    public List<Students> getStudents() {
        initialiseData();
        return students;
    }

    private void initialiseData() {
        students = new ArrayList<>();
        students.add(new Students("Винник Владислав",
                "117765348335292685488",
                "vlads0n",
                R.drawable.vinik));
        students.add(new Students("Теплий Михайло",
                "110313151428733681846",
                "RedGeekPanda",
                R.drawable.tyoply));
        students.add(new Students("Рибак Богдан",
                "103145064185261665176",
                "BogdanRybak1996",
                R.drawable.rybak));
        students.add(new Students("Лещенко Иван",
                "111088051831122657934",
                "ivleshch",
                R.drawable.leshenko));
        students.add(new Students("Сакуров Павло",
                "108482088578879737406",
                "sakurov",
                R.drawable.sakurov));
        students.add(new Students("Воловик Руслан",
                "109719711261293841416",
                "RuslanVolovyk",
                R.drawable.lis));
        students.add(new Students("Кириченко Дар'я",
                "103130382244571139113",
                "dashakdsr",
                R.drawable.kirichenko));
        students.add(new Students("Рябко Андрій",
                "110288437168771810002",
                "RyabkoAndrew",
                R.drawable.ryabko));
        students.add(new Students("Ситник Євгеній",
                "101427598085441575303",
                "YevheniiSytnyk",
                R.drawable.sytnyk));
        students.add(new Students("Химич Эдгар",
                "102197104589432395674",
                "lyfm",
                R.drawable.khimich));
        students.add(new Students("Бочкарьова Альона",
                "107382407687723634701",
                "HelenCool",
                R.drawable.bo4karova));
        students.add(new Students("Мигаль Руслан",
                "106331812587299981536",
                "rmigal",
                R.drawable.migal));
        students.add(new Students("Смалько Ірина",
                "113994208318508685327",
                "IraSmalko",
                R.drawable.smalko));
        students.add(new Students("Губський Валерій",
                "107910188078571144657",
                "gvv-ua",
                R.drawable.gubskyi));
        students.add(new Students("Жданов Євген",
                "113264746064942658029",
                "zhdanov-ek",
                R.drawable.zdanov));
        students.add(new Students("Сергеенко Иван",
                "111389859649705526831",
                "dogfight81",
                R.drawable.segreenko));
        students.add(new Students("Пахаренко Ігор",
                "108231952557339738781",
                "IhorPakharenko",
                R.drawable.pakharenko));
        students.add(new Students("Сторчак Олександр",
                "106553086375805780685",
                "new15",
                R.drawable.storchak));
        students.add(new Students("Піхманець Микола",
                "110087894894730430086",
                "NikPikhmanets",
                R.drawable.pikhmanets));
        students.add(new Students("Лимарь Володимир",
                "109227554979939957830",
                "VovanNec",
                R.drawable.limar));
    }
}



