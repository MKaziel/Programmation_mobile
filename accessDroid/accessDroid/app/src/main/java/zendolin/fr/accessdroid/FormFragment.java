package zendolin.fr.accessdroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FormFragment extends Fragment {


    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_form, container, false);

        Button btnCreate = (Button)v.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button", "TEST DE BOUTON");
                creation(view);
            }
        });
        return v;


    }

    public void creation(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Vous avez créé votre compte.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();







    }

}
