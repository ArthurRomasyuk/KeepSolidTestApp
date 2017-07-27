package com.example.arutr.keepsolidapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arutr.keepsolidapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RejectFragment extends Fragment {

    RejectFragment.OnClickOkButtonListener callback;

    @OnClick(R.id.reject_ok_button)
    public void onClickOkButton(){
        callback.replaceRejectFragment();
    }

    public RejectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reject, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (RejectFragment.OnClickOkButtonListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement RejectFragment.OnClickOkButtonListener");
        }
    }

    public interface OnClickOkButtonListener {
        void replaceRejectFragment ();
    }

}
