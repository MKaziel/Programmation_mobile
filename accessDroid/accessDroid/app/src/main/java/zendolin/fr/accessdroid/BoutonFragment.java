package zendolin.fr.accessdroid;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class BoutonFragment extends Fragment implements View.OnClickListener{
    ImageView iv;

    public BoutonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bouton, container, false);

        Button btnRed = (Button)v.findViewById(R.id.btnRed);
        Button btnGreen = (Button)v.findViewById(R.id.btnGreen);
        Button btnBlue = (Button)v.findViewById(R.id.btnBlue);


        btnRed.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnBlue.setOnClickListener(this);

        iv = (ImageView)v.findViewById(R.id.img);


        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnRed :
                Log.d("btnRed", "BOUTON RED");
                iv.setImageResource(R.color.redColor);
                break;

            case R.id.btnGreen :
                Log.d("btnGreen", "BOUTON GREEN");
                iv.setImageResource(R.color.greenColor);
                break;

            case R.id.btnBlue :
                Log.d("btnBlue", "BOUTON BLUE");
                iv.setImageResource(R.color.blueColor);
                break;

            default:
                break;
        }
    }



}


