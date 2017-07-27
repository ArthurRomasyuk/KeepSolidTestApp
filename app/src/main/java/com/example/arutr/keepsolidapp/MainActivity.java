package com.example.arutr.keepsolidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.arutr.keepsolidapp.fragment.EmailInputFragment;

public class MainActivity extends AppCompatActivity implements EmailInputFragment.OnClickSendButtonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            EmailInputFragment emailInputFragment = new EmailInputFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, emailInputFragment).commit();

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data.getBooleanExtra("reject", true)){
            Toast.makeText(MainActivity.this, "Пользователь не подтвердил адрес.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Пользователь подтвердил адрес.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void openEmailActivity(String email) {
        Intent emailIntent = new Intent(this, EmailActivity.class);
        emailIntent.putExtra("email", email);
        startActivityForResult(emailIntent,1);
    }
}
