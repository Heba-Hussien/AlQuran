package com.Heba.alquran;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class TelawaaFragment extends Fragment {
    Spinner sora_spinner1;
    EditText Saf7a_edit;
    String[] ArrayOfSoraPageNum;
    Button Telawa_btn;

    public TelawaaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_telawaa, container, false);

        Saf7a_edit =rootView.findViewById(R.id.Saf7a_edit);
        sora_spinner1 =rootView. findViewById(R.id.Sora_spin1);
        Telawa_btn = rootView.findViewById(R.id.Telawa_btn);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>( getActivity().getBaseContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ArrayOfSoraNames));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sora_spinner1.setAdapter(adapter1);
        sora_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayOfSoraPageNum = getResources().getStringArray(R.array.ArrayOfSoraPagrNum);
                Saf7a_edit.setText(ArrayOfSoraPageNum[i]);
                sora_spinner1.getItemIdAtPosition((i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Telawa_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(Saf7a_edit.getText().toString()) <= 604 && Integer.parseInt(Saf7a_edit.getText().toString()) >= 1) {
                    Intent i = new Intent(getActivity().getBaseContext(), QuranPage.class);
                    i.putExtra("pageNum", Integer.parseInt(Saf7a_edit.getText().toString()));
                    startActivity(i);
                } else
                    Toast.makeText(getActivity().getBaseContext(), "رقم الصفحه خطأ", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
