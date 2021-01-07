package ru.navodnikov.denis.locationtracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.databinding.FragmentTrackingBinding;

public class TrackingFragment extends Fragment {

    private FragmentTrackingBinding fragmentTrackingBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        fragmentTrackingBinding = FragmentTrackingBinding.inflate(inflater, container, false);
        View view = fragmentTrackingBinding.getRoot();
        return view;

    }

    public TrackingFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentTrackingBinding.buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_trackingFragment_to_loginFragment);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentTrackingBinding = null;
    }
}