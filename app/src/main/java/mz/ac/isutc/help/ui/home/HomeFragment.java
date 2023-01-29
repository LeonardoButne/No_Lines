package mz.ac.isutc.help.ui.home;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

import mz.ac.isutc.help.databinding.FragmentHomeBinding;
import mz.ac.isutc.help.ui.models.Marcacao;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Marcacao marcacao = new Marcacao();

    private FirebaseDatabase db = FirebaseDatabase.getInstance();;
    private DatabaseReference root = db.getReference().child("marcacoes");;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        String [] values =
                {"Maternidade","Estomatologia","Oftamologia","Cirurgia","Terapia"};
        Spinner spinner = (Spinner) binding.spinner;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);



        binding.btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fecha = dayOfMonth + "/" + (month+1) + "/" + year;

                        binding.edtData.setText(fecha);
                    }
                }, 2023, mes,dia);
                dpd.show();
            }
        });

        binding.btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hora = cal.get(Calendar.HOUR_OF_DAY);
                int minutos = cal.get(Calendar.MINUTE);


                TimePickerDialog tpd = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(minute < 10){
                            binding.edtTime.setText(hourOfDay + " : 0" + minute);

                        }else{
                            binding.edtTime.setText(hourOfDay + " : " + minute);
                        }
                    }
                },7,30, true);
                tpd.show();
            }
        });




    binding.btnAgendar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recuperarDados();
            criarMarcacao();
        }
    });

    return root;
    }

    public void criarMarcacao() {
        HashMap<String, String> marcacaoMap = new HashMap<>();
        String uid = FirebaseAuth.getInstance ().getCurrentUser ().getUid ();

        marcacaoMap.put("id_usuario", uid);
        marcacaoMap.put("sector", marcacao.getSector());
        marcacaoMap.put("data", marcacao.getData());
        marcacaoMap.put("hora", marcacao.getHora());

        root.push().setValue(marcacaoMap);

    }
    private void recuperarDados(){

        String sector = binding.spinner.getSelectedItem().toString();
        String data = binding.edtData.getText().toString();
        String time = binding.edtTime.getText().toString();

            marcacao = new Marcacao();

            marcacao.setData(data);
            marcacao.setHora(time);
            marcacao.setSector(sector);
    }


//    public void abrirCalendario(View view){
//        Calendar cal = Calendar.getInstance();
//        int ano = cal.get(Calendar.YEAR);
//        int mes = cal.get(Calendar.MONTH);
//        int dia = cal.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                String fecha = dayOfMonth + "/" + month + "/" + year;
//
//                binding.edtData.setText(fecha);
//            }
//        }, dia, mes,ano);
//    }
}