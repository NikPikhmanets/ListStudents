package com.example.studentslist.realm;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.studentslist.R;
import com.example.studentslist.realm.data.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmActivity extends AppCompatActivity {

    List<User> usersList = new User().getUserList();
    private RecyclerView mRecyclerView;
    private RealmResults<User> resaultUser;

    private Realm realm;

    private final RealmChangeListener<RealmResults<User>> changeListener = new RealmChangeListener<RealmResults<User>>() {
        @Override
        public void onChange(RealmResults<User> element) {
            updateUI(element);
        }
    };

    private void updateUI(RealmResults<User> elements) {
        if (mRecyclerView .getAdapter() == null) {
            mRecyclerView.setAdapter(new UserAdapter(elements));
        } else {
            UserAdapter adapter = (UserAdapter) mRecyclerView.getAdapter();
            adapter.notifyDataSetChanged();;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mActionBarToolbar.setTitle("Realm");
        setSupportActionBar(mActionBarToolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvRealmUsers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        realm = Realm.getDefaultInstance();
        loadUsersToList(usersList);
        final RealmResults<User>users = realm.where(User.class)
                .findAllAsync();
        users.addChangeListener(changeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_realm, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchItem = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchItem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                resaultUser = searchUser(s);
                UserAdapter rva = new UserAdapter(resaultUser);
                mRecyclerView.setAdapter(rva);
                return false;
            }
        });
        return true;
    }

    private RealmResults<User> searchUser(String name){
        Realm realm = Realm.getDefaultInstance();

        RealmResults<User> searchUsers = realm
                .where(User.class)
                .contains("name", name)
                .findAllSorted("name", Sort.ASCENDING);
        realm.close();
        return searchUsers;
    }

    private void loadUsersToList(final List<User> users) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.commitTransaction();
        realm.close();

        realm.executeTransactionAsync(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.insertOrUpdate(users);
                    }
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
