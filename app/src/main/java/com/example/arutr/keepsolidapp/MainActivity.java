package com.example.arutr.keepsolidapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.arutr.keepsolidapp.fragment.AcceptFragment;
import com.example.arutr.keepsolidapp.fragment.EmailInputFragment;
import com.example.arutr.keepsolidapp.fragment.RejectFragment;
import com.example.arutr.keepsolidapp.fragment.UserFragment;
import com.example.arutr.keepsolidapp.models.User;

public class MainActivity extends AppCompatActivity implements UserFragment.OnUserListFragmentInteractionListener, AcceptFragment.OnClickOkButtonListener, RejectFragment.OnClickOkButtonListener {

    private UserFragment userFragment;
    private AcceptFragment acceptFragment;
    private RejectFragment rejectFragment;
    private FragmentManager supportFragmentManager;
    private CurrentFragmentEnum currentFragmentEnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            currentFragmentEnum = CurrentFragmentEnum.UserFragment;
            userFragment = new UserFragment();
            supportFragmentManager = getSupportFragmentManager();
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, userFragment).commit();
        }

    }


    private enum CurrentFragmentEnum {
        UserFragment,
        AcceptFragment,
        RejectFragment
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean reject = false;
        try {
            reject = data.getBooleanExtra("reject", true);
        } catch (NullPointerException e) {
            e.printStackTrace();
            reject = true;
        }
        if (reject) {
            currentFragmentEnum = CurrentFragmentEnum.RejectFragment;
        } else {
            currentFragmentEnum = CurrentFragmentEnum.AcceptFragment;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentFragmentEnum==null){
            currentFragmentEnum = CurrentFragmentEnum.UserFragment;
        }
        supportFragmentManager = getSupportFragmentManager();
        switch (currentFragmentEnum) {
            case UserFragment:
                break;
            case AcceptFragment:
                acceptFragment = new AcceptFragment();
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, acceptFragment).commit();
                break;
            case RejectFragment:
                rejectFragment = new RejectFragment();
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, rejectFragment).commit();
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        userFragment = new UserFragment();
        supportFragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onListFragmentInteraction(User user) {
        Intent userIntent = new Intent(this, EmailActivity.class);
        userIntent.putExtra("user", user);
        userIntent.putExtra("categoryDescription", user.getCategory().getDescription());
        startActivityForResult(userIntent, 1);
    }

    @Override
    public void replaceAcceptFragment() {
//        userFragment = new UserFragment();
        Bundle args = new Bundle();
        args.putBoolean("clean", true);
        userFragment.setArguments(args);
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, userFragment).commit();
    }

    @Override
    public void replaceRejectFragment() {
//        userFragment = new UserFragment();
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, userFragment).commit();
    }
}
