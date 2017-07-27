package com.example.arutr.keepsolidapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.arutr.keepsolidapp.fragment.AcceptFragment;
import com.example.arutr.keepsolidapp.fragment.EmailInputFragment;
import com.example.arutr.keepsolidapp.fragment.RejectFragment;

public class MainActivity extends AppCompatActivity implements EmailInputFragment.OnClickSendButtonListener, AcceptFragment.OnClickOkButtonListener, RejectFragment.OnClickOkButtonListener {

    private EmailInputFragment emailInputFragment;
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
            currentFragmentEnum = CurrentFragmentEnum.EmailInputFragment;
            emailInputFragment = new EmailInputFragment();
            acceptFragment = new AcceptFragment();
            rejectFragment = new RejectFragment();
            supportFragmentManager = getSupportFragmentManager();
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, emailInputFragment).commit();
        }

    }

    private enum CurrentFragmentEnum {
        EmailInputFragment,
        AcceptFragment,
        RejectFragment
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data.getBooleanExtra("reject", true)) {
            currentFragmentEnum = CurrentFragmentEnum.RejectFragment;
        } else {
            currentFragmentEnum = CurrentFragmentEnum.AcceptFragment;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (currentFragmentEnum) {
            case EmailInputFragment:
                break;
            case AcceptFragment:
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, acceptFragment).commit();
                break;
            case RejectFragment:
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, rejectFragment).commit();
                break;
        }
    }

    @Override
    public void openEmailActivity(String email) {
        Intent emailIntent = new Intent(this, EmailActivity.class);
        emailIntent.putExtra("email", email);
        startActivityForResult(emailIntent, 1);
    }

    @Override
    public void replaceAcceptFragment() {
        Bundle args = new Bundle();
        args.putBoolean("clean", true);
        emailInputFragment.setArguments(args);
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, emailInputFragment).commit();
    }

    @Override
    public void replaceRejectFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, emailInputFragment).commit();
    }
}
