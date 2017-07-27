package com.example.arutr.keepsolidapp.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.arutr.keepsolidapp.EmailActivity;
import com.example.arutr.keepsolidapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailInputFragment extends Fragment {

    OnClickSendButtonListener mCallback;

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.clean)
    Button cleanButton;

    @BindView(R.id.send)
    Button sendButton;

    @BindView(R.id.errorMessage)
    TextView errorMessage;

    @BindView(R.id.cbAllow)
    CheckBox cbAllow;

    public EmailInputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email_input, container, false);
        ButterKnife.bind(this, view);
        sendButton.setClickable(false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnClickSendButtonListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnClickSendButtonListener");
        }
    }


    @OnClick(R.id.send)
    public void send (View view){
        if (TextUtils.isEmpty(etEmail.getText())){
            errorMessage.setText("Поле email не может быть пустым");
        } else if (!etEmail.getText().toString().contains("@")){
            errorMessage.setText("Введите правильный email");
        } else {
            mCallback.openEmailActivity(etEmail.getText().toString());
        }
    }
    @OnClick(R.id.clean)
    public void clean (){
        etEmail.setText("");
    }

    @OnClick(R.id.cbAllow)
    public void allow (){
        if (!cbAllow.isChecked()){
            sendButton.setClickable(false);
        } else {
            sendButton.setClickable(true);
        }
    }

    public interface OnClickSendButtonListener {
        public void openEmailActivity(String email);
    }

}
