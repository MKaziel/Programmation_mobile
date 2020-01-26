package com.example.lab5_sd;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

public class ButtonFrag extends Fragment {

    AppCompatActivity app = new AppCompatActivity();
    Context parentActivity = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.button_frag, container, false);

    }

    public void fragTransition(View view){
        Button btn = app.findViewById(view.getId());
        String txt = String.valueOf(btn.getText());
        Intend intend;

        switch (txt){
            case "Home" :
                MainActivity.choice(1);
                break;
            case "Content":
                break;
            case "Setting":
                break;
            default:
                break;

        }
    }

    @Override
    public void onAttach(Context context) {
        parentActivity = context;
        super.onAttach(context);
    }
}
