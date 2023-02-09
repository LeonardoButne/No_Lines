package mz.ac.isutc.nolinesadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.ac.isutc.nolinesadmin.config.ConfiguracaoFireBase;
import mz.ac.isutc.nolinesadmin.databinding.ActivityHorariosBinding;
import mz.ac.isutc.nolinesadmin.models.HorarioAdapter;
import mz.ac.isutc.nolinesadmin.models.HorarioModel;

public class HorariosActivity extends AppCompatActivity {

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("horarios");

    private ActivityHorariosBinding binding;
    private HorarioModel horario = new HorarioModel();

    private List<HorarioModel> horarios;
    RecyclerView recyclerView;
    HorarioAdapter horarioAdapter;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHorariosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.recyclerView;

        recyclerView.setLayoutManager(new LinearLayoutManager(HorariosActivity.this));
        horarios = new ArrayList<>();
        databaseReference = ConfiguracaoFireBase.getFirebaseDatabase();

//        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


        binding.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recuperarDados() == true) {
                    Toast.makeText(HorariosActivity.this, "Salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
                    binding.edtHorario.setText(null);
                    criarMarcacao();

                }

            }
        });

        databaseReference.child("horarios").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dn : snapshot.getChildren()) {

                    if (dn.getValue() != null) {
                        HorarioModel h = dn.getValue(HorarioModel.class);
                        horarios.add(h);

                    }

                }

                horarioAdapter = new HorarioAdapter(horarios);
                recyclerView.setAdapter(horarioAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void criarMarcacao() {
        HashMap<String, String> marcacaoMap = new HashMap<>();

        root.child(horario.getId()).setValue(horario).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        root.push().setValue(marcacaoMap);

    }

    private boolean recuperarDados(){


        String novo_horario = binding.edtHorario.getText().toString();

        if(novo_horario.isEmpty()){
            Toast.makeText(HorariosActivity.this,"Preencha todos os campos primeiro", Toast.LENGTH_SHORT).show();
            return false;
        }else{

            horario = new HorarioModel();

            String id_horario = db.getReference().push().getKey();

            horario.setId(id_horario);
            horario.setNome(novo_horario);

            return true;
        }
    }
}