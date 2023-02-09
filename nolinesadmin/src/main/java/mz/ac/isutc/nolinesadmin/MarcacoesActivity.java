package mz.ac.isutc.nolinesadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mz.ac.isutc.nolinesadmin.config.ConfiguracaoFireBase;
import mz.ac.isutc.nolinesadmin.databinding.ActivityMarcacoesBinding;
import mz.ac.isutc.nolinesadmin.databinding.ActivityPacientesBinding;
import mz.ac.isutc.nolinesadmin.models.MarcacaoAdapter;
import mz.ac.isutc.nolinesadmin.models.MarcacaoModel;
import mz.ac.isutc.nolinesadmin.models.PacienteAdapter;
import mz.ac.isutc.nolinesadmin.models.PacienteModel;

public class MarcacoesActivity extends AppCompatActivity {

    private ActivityMarcacoesBinding binding;

    private MarcacaoModel marcacao = new MarcacaoModel();

    private List<MarcacaoModel> marcacoes;
    private List<PacienteModel> pacientes;

    RecyclerView recyclerView;
    MarcacaoAdapter marcacaoAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        binding = ActivityMarcacoesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.recyclerView4;

        recyclerView.setLayoutManager(new LinearLayoutManager(MarcacoesActivity.this));
        marcacoes = new ArrayList<>();

        databaseReference = ConfiguracaoFireBase.getFirebaseDatabase();


        databaseReference.child("marcacoes").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dn : snapshot.getChildren()) {

                    if (dn.getValue() != null) {
                        MarcacaoModel m = dn.getValue(MarcacaoModel.class);
                        marcacoes.add(m);

                    }

                }

                marcacaoAdapter = new MarcacaoAdapter(marcacoes);
                recyclerView.setAdapter(marcacaoAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}