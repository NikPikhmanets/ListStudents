package com.example.studentslist.realm.data;

import com.example.studentslist.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private int photo;

    private String linkGoogle;
    private String linkGit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getLinkGoogle() {
        return linkGoogle;
    }

    public void setLinkGoogle(String linkGoogle) {
        this.linkGoogle = linkGoogle;
    }

    public String getLinkGit() {
        return linkGit;
    }

    public void setLinkGit(String linkGit) {
        this.linkGit = linkGit;
    }

    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();;
        initialiseRealmData(userList);
        return userList;
    }

    private void initialiseRealmData(List<User> userList) {

        int id = 1;
        String lGoogle = "G+ - ";//https://plus.google.com/";
        String lGit = "GitHub - ";//https://github.com/";

        User user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.vinik);
        user.setName("Винник Владислав");
        user.setLinkGoogle(lGoogle + "117765348335292685488");
        user.setLinkGit(lGit + "vlads0n");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.tyoply);
        user.setName("Теплий Михайло");
        user.setLinkGoogle(lGoogle + "110313151428733681846");
        user.setLinkGit(lGit + "RedGeekPanda");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.rybak);
        user.setName("Рибак Богдан");
        user.setLinkGoogle(lGoogle + "103145064185261665176");
        user.setLinkGit(lGit + "BogdanRybak1996");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.leshenko);
        user.setName("Лещенко Иван");
        user.setLinkGoogle(lGoogle + "111088051831122657934");
        user.setLinkGit(lGit + "ivleshch");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.sakurov);
        user.setName("Сакуров Павло");
        user.setLinkGoogle(lGoogle + "108482088578879737406");
        user.setLinkGit(lGit + "sakurov");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.lis);
        user.setName("Воловик Руслано");
        user.setLinkGoogle(lGoogle + "109719711261293841416");
        user.setLinkGit(lGit + "RuslanVolovyk");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.kirichenko);
        user.setName("Кириченко Дар'я");
        user.setLinkGoogle(lGoogle + "103130382244571139113");
        user.setLinkGit(lGit + "dashakdsr");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.ryabko);
        user.setName("Рябко Андрій");
        user.setLinkGoogle(lGoogle + "110288437168771810002");
        user.setLinkGit(lGit + "RyabkoAndrew");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.sytnyk);
        user.setName("Ситник Євгеній");
        user.setLinkGoogle(lGoogle + "101427598085441575303");
        user.setLinkGit(lGit + "YevheniiSytnyk");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.khimich);
        user.setName("Химич Эдгар");
        user.setLinkGoogle(lGoogle + "102197104589432395674");
        user.setLinkGit(lGit + "lyfm");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.bo4karova);
        user.setName("Бочкарьова Альона");
        user.setLinkGoogle(lGoogle + "107382407687723634701");
        user.setLinkGit(lGit + "HelenCool");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.migal);
        user.setName("Мигаль Руслан");
        user.setLinkGoogle(lGoogle + "106331812587299981536");
        user.setLinkGit(lGit + "rmigal");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.smalko);
        user.setName("Смалько Ірина");
        user.setLinkGoogle(lGoogle + "113994208318508685327");
        user.setLinkGit(lGit + "IraSmalko");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.gubskyi);
        user.setName("Губський Валерій");
        user.setLinkGoogle(lGoogle + "107910188078571144657");
        user.setLinkGit(lGit + "gvv-ua");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.zdanov);
        user.setName("Жданов Євген");
        user.setLinkGoogle(lGoogle + "113264746064942658029");
        user.setLinkGit(lGit + "zhdanov-ek");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.segreenko);
        user.setName("Сергеенко Иван");
        user.setLinkGoogle(lGoogle + "111389859649705526831");
        user.setLinkGit(lGit + "dogfight81");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.pakharenko);
        user.setName("Пахаренко Ігор");
        user.setLinkGoogle(lGoogle + "108231952557339738781");
        user.setLinkGit(lGit + "IhorPakharenko");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.storchak);
        user.setName("Сторчак Олександр");
        user.setLinkGoogle(lGoogle + "106553086375805780685");
        user.setLinkGit(lGit + "new15");
        userList.add(user);

        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.pikhmanets);
        user.setName("Піхманець Микола");
        user.setLinkGoogle(lGoogle + "110087894894730430086");
        user.setLinkGit(lGit + "NikPikhmanets");
        userList.add(user);


        user = new User();
        user.setId(id++);
        user.setPhoto(R.drawable.limar);
        user.setName("Лимарь Володимир");
        user.setLinkGoogle(lGoogle + "109227554979939957830");
        user.setLinkGit(lGit + "VovanNec");
        userList.add(user);
    }
}
