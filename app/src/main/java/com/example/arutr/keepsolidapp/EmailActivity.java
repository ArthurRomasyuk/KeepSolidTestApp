package com.example.arutr.keepsolidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailActivity extends AppCompatActivity {
    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.accept)
    Button acceptButton;

    @BindView(R.id.reject)
    Button rejectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
        tvEmail.setText(getIntent().getStringExtra("email"));
    }

    @OnClick(R.id.accept)
    public void accept(View view){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {tvEmail.getText().toString()});
        startActivity(Intent.createChooser(shareIntent, "Send email."));
    }
}
