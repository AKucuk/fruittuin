package com.example.abdullahkucuk.fruittuin.Fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.abdullahkucuk.fruittuin.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlattegrondFragment extends Fragment {

    View view;
    ImageView imageView;


    public PlattegrondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_plattegrond, container, false);

        imageView = (ImageView) view.findViewById(R.id.imgPlattegrond);
        PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);
        photoView.update();

        return view;
    }

}
