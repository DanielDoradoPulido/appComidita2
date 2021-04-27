package com.example.appcomidita2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appcomidita2.R;
import com.example.appcomidita2.loginActivity;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    TextView bienvenida;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        bienvenida = root.findViewById(R.id.textViewLoL);

        Intent intent = getActivity().getIntent();
        String mensaje = intent.getStringExtra(loginActivity.enviar);


        bienvenida.setText("¡Bienvenido de nuevo , " + mensaje + "!");


        return root;
    }
}