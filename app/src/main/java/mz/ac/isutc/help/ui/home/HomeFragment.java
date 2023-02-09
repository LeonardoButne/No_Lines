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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import mz.ac.isutc.help.databinding.FragmentHomeBinding;
import mz.ac.isutc.help.ui.Meusdados.MeusDadosFragment;
import mz.ac.isutc.help.ui.config.ConfiguracaoFireBase;
import mz.ac.isutc.help.ui.models.Horario;
import mz.ac.isutc.help.ui.models.Marcacao;
import mz.ac.isutc.help.ui.models.Sector;
import mz.ac.isutc.help.ui.models.Usuario;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Marcacao marcacao = new Marcacao();
    private ArrayList <Usuario> pacientes = new ArrayList<>();

    private Vector <Horario> horarios = new Vector<>();
    private Vector <Sector> sectores = new Vector<>();

    private FirebaseDatabase db = FirebaseDatabase.getInstance();;

    private DatabaseReference root = db.getReference().child("marcacoes");
    private DatabaseReference root2 = db.getReference().child("usuarios");
    private DatabaseReference root3 = db.getReference().child("horarios");
    private DatabaseReference root4 = db.getReference().child("sectores");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


       // sectores.removeAllElements();
        listarSectores();
        String [] sectores_array = new String[sectores.size()];


        for (int i = 0; i < sectores.size(); i++){
            sectores_array[i] = sectores.get(i).getNome();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, sectores_array );
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.spinner.setAdapter(adapter);

        listarHorarios();

        String [] horaraios_array = new String[horarios.size()];

        for (int i = 0; i < horarios.size(); i++){
            horaraios_array[i] = horarios.get(i).getNome();
        }


        ArrayAdapter<String> adapter_horarios = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, horaraios_array );
        adapter_horarios.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.spinnerTime.setAdapter(adapter_horarios);

        sectores.removeAllElements();
        horarios.removeAllElements();

        listarPacientes();


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

//        binding.btnHora.setOnClickListener(new View.OnClickListener() {//para escolher a hora
//            @Override
//            public void onClick(View v) {
//                Calendar cal = Calendar.getInstance();
//                int hora = cal.get(Calendar.HOUR_OF_DAY);
//                int minutos = cal.get(Calendar.MINUTE);
//
//
//                TimePickerDialog tpd = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        if(minute < 10){
//                            binding.edtTime.setText(hourOfDay + " : 0" + minute);
//
//                        }else{
//                            binding.edtTime.setText(hourOfDay + " : " + minute);
//                        }
//                    }
//                },7,30, true);
//                tpd.show();
//            }
//        });

    binding.btnAgendar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(recuperarDados() == true){
                criarMarcacao();
            }
           // recuperarDados();

        }
    });

    return root;
    }
    private void listarHorarios() {

        root3.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dn : snapshot.getChildren()) {

                    if (dn.getValue() != null) {
                        Horario h = dn.getValue(Horario.class);
                        horarios.add(h);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void listarSectores() {

        root4.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dn : snapshot.getChildren()) {

                    if (dn.getValue() != null) {
                        Sector m = dn.getValue(Sector.class);
                        sectores.add(m);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void listarPacientes() {
        root2.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dn : snapshot.getChildren()) {

                    if (dn.getValue() != null) {
                        Usuario m = dn.getValue(Usuario.class);
                        pacientes.add(m);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void criarMarcacao() {
        HashMap<String, String> marcacaoMap = new HashMap<>();

        root.child(marcacao.getId_agenda()).setValue(marcacao).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        root.push().setValue(marcacaoMap);

    }
    private boolean recuperarDados(){
        String sector = binding.spinner.getSelectedItem().toString();
        String data = binding.edtData.getText().toString();
        String time = binding.spinnerTime.getSelectedItem().toString();

        if(sector.isEmpty() || data.isEmpty() || time.isEmpty()){
            Toast.makeText( getContext(),"Preencha todos os campos primeiro", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid ();
            marcacao = new Marcacao();


            String id_agenda = db.getReference().push().getKey();

            marcacao.setId_agenda(id_agenda);
            marcacao.setId(uid);
            marcacao.setData(data);
            marcacao.setHora(time);
            marcacao.setSector(sector);

            for (int i = 0; i < pacientes.size(); i++){
                if (marcacao.getId().equals(pacientes.get(i).getId())){
                    marcacao.setNome_paciente(pacientes.get(i).getNome());
                }
            }

            return true;
        }
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