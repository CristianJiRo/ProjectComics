package com.example.kamelot.projectcomics;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.kamelot.projectcomics.databinding.FragmentStartBinding;
/**
 * A placeholder fragment containing a simple view.
 */
public class StartActivityFragment extends Fragment {

    private FragmentStartBinding binding;

    public StartActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_start, container, false);

        View view = binding.getRoot();

        binding.btSeries0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);

            }
        });

        binding.btFav0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), FavoritasActivity.class);
                startActivityForResult(intent, 0);
            }
        });



        return view;
    }
}
