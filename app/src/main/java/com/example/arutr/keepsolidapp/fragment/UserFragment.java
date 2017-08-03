package com.example.arutr.keepsolidapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arutr.keepsolidapp.R;
import com.example.arutr.keepsolidapp.adapters.UserAdapter;
import com.example.arutr.keepsolidapp.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserFragment extends Fragment {

    private OnUserListFragmentInteractionListener mListener;
    private List<User> users;

    public UserFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users = new ArrayList<>();
        users.add(new User("Павел", 0, true, "pavel@gmail.com", User.Category.FAMILY));
        users.add(new User("Анастасия", 43, false, "stacy@gmail.com", User.Category.WORK));
        users.add(new User("Гастон", 231, true, "gaston@gmail.com", User.Category.OTHER));
        users.add(new User("Джон", 44, true, "jhon@gmail.com", User.Category.FRIENDS));
        users.add(new User("Карина", 45, false, "cara@gmail.com", User.Category.FRIENDS));
        users.add(new User("Катерина", 0, true, "kate@gmail.com", User.Category.FAMILY));
        users.add(new User("Артур", 321, false, "arthur@gmail.com", User.Category.WORK));
        users.add(new User("Ринго", 211, true, "ringo@gmail.com", User.Category.OTHER));
        users.add(new User("Курт", 454, true, "curt@gmail.com", User.Category.FRIENDS));
        users.add(new User("Александр", 425, false, "alex@gmail.com", User.Category.FRIENDS));
        Collections.sort(users,userNameComparator);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new UserAdapter(users, mListener, getContext()));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserListFragmentInteractionListener) {
            mListener = (OnUserListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnUserListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnUserListFragmentInteractionListener {
        void onListFragmentInteraction(User user);
    }

    static Comparator<User> userNameComparator = new Comparator<User>() {

        public int compare(User o1, User o2) {
            return o1.getUsername().compareTo(o2.getUsername());
        }
    };
}
