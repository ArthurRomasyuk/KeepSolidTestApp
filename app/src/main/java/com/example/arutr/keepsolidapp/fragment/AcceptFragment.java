package com.example.arutr.keepsolidapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.arutr.keepsolidapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcceptFragment extends Fragment {
    OnClickOkButtonListener callback;

    @OnClick(R.id.accept_ok_button)
    public void onClickOkButton(){
        callback.replaceAcceptFragment();
    }

    public AcceptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accept, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (AcceptFragment.OnClickOkButtonListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement AcceptFragment.OnClickOkButtonListener");
        }
    }

    public interface OnClickOkButtonListener {
        void replaceAcceptFragment ();
    }

}
