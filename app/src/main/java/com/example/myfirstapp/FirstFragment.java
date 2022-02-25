package com.example.myfirstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myfirstapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    /*
        You could call findViewById() in countMe() to find showCountTextView.
        However, countMe() is called every time the button is clicked, and findViewById() is a relatively time consuming method to call.
        So it is better to find the view once and cache it.
    */
    TextView showCountTextView;

    private void countMe(View view) {
        // Get the value of the text view
        String countString = showCountTextView.getText().toString();
        // Convert value to a number and increment it
        Integer count = Integer.parseInt(countString);
        count++;
        // Display the new value in the text view.
        showCountTextView.setText(count.toString());
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // tutorial had issue of out-dated code. fix: https://github.com/google-developer-training/first-android-app/issues/141

        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View fragmentFirstLayout = binding.getRoot();

        // Get the count text view
        showCountTextView = fragmentFirstLayout.findViewById(R.id.textview_first);

        return fragmentFirstLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // code below gave error so I changed it.
        // binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
        binding.randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // default from android studio: NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);

                // find countText view (textview_first)
                int currentCount = Integer.parseInt(showCountTextView.getText().toString());
                // Create an action with currentCount as the argument to actionFirstFragmentToSecondFragment()
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);
                // Add a line to find the nav controller and navigate with the action you created.
                NavHostFragment.findNavController(FirstFragment.this).navigate(action);
            }
        });

        // tutorial had it like this : view.findViewById(R.id.toast_button).setOnClickListener(new View.OnClickListener() {
        // but, android studio seems to follow the convention below: binding.toastButton...
        binding.toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), R.string.toast_button_message, Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        binding.countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countMe(view);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}