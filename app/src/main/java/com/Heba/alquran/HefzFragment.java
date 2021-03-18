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

import com.Heba.alquran.Modules.Ayahs;
import com.Heba.alquran.Modules.Surahs;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;


public class HefzFragment extends Fragment {
    Spinner  sora_spinner2;
    EditText To_edit, From_edit, ayah_Repetition_edit, fakra_Repetition_edit;
    String[] ArrayOfAhyasNumOfSuhra;
    Button  Hefz_btn;
    int surahNum;
    List<Ayahs> ayahsList;


    public HefzFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hefz, container, false);
        To_edit = rootView.findViewById(R.id.To_edit);
        From_edit =rootView. findViewById(R.id.From_edit);
        ayah_Repetition_edit = rootView.findViewById(R.id.ayah_Repetition_edit);
        fakra_Repetition_edit = rootView.findViewById(R.id.fakra_Repetition_edit);
        sora_spinner2 =rootView. findViewById(R.id.Sora_spin2);
        Hefz_btn =rootView. findViewById(R.id.Hefz_btn);
        ArrayOfAhyasNumOfSuhra = getResources().getStringArray(R.array.ArrayOfAhyasOfSuhraNum);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ArrayOfSoraNames));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sora_spinner2.setAdapter(adapter2);
        sora_spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sora_spinner2.getItemIdAtPosition((i));
                To_edit.setText(ArrayOfAhyasNumOfSuhra[i]);
                surahNum = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Hefz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Surahs> surahs = Paper.book().read("quran-surahs");
                ayahsList= new ArrayList<>();
                // check user entries
                if (Integer.parseInt(From_edit.getText().toString())<=0 )
                    From_edit.setText("1");
                 if(Integer.parseInt(To_edit.getText().toString())>Integer.parseInt(ArrayOfAhyasNumOfSuhra[surahNum-1]))
                    To_edit.setText(ArrayOfAhyasNumOfSuhra[surahNum-1]);
                if (Integer.parseInt(From_edit.getText().toString())>Integer.parseInt(To_edit.getText().toString()))
                {
                    From_edit.setText("1");
                    To_edit.setText(ArrayOfAhyasNumOfSuhra[surahNum-1]);
                }

                    for (Surahs surah : surahs) {
                    if (surah.getNumber() == surahNum) {
                        for (Ayahs ayah : surah.getAyahs()) {
                            if (ayah.getNumberInSurah() >= Integer.parseInt(From_edit.getText().toString()) && ayah.getNumberInSurah() <= Integer.parseInt(To_edit.getText().toString()))
                               ayahsList.add(ayah);
                        }
                        break;
                    }}
                Paper.book().write("quran-ayahs", ayahsList);
                Intent i = new Intent(getActivity().getBaseContext(), PlayAudio.class);
                i.putExtra("fakra_Repetition",fakra_Repetition_edit.getText().toString());
                i.putExtra("ayah_Repetition",ayah_Repetition_edit.getText().toString());
                startActivity(i);
          }
       });
        return rootView;
    }
}
