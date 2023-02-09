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
import mz.ac.isutc.nolinesadmin.databinding.ActivityHorariosBinding;
import mz.ac.isutc.nolinesadmin.databinding.ActivityPacientesBinding;
import mz.ac.isutc.nolinesadmin.models.PacienteAdapter;
import mz.ac.isutc.nolinesadmin.models.PacienteModel;
import mz.ac.isutc.nolinesadmin.models.SectorAdapter;
import mz.ac.isutc.nolinesadmin.models.SectorModel;

public class PacientesActivity extends AppCompatActivity {
    private ActivityPacientesBinding binding;

    private PacienteModel sector = new PacienteModel();

    public static List<PacienteModel> pacientes;
    RecyclerView recyclerView;
    PacienteAdapter pacienteAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        binding = ActivityPacientesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.recyclerView3;

        recyclerView.setLayoutManager(new LinearLayoutManager(PacientesActivity.this));
        pacientes = new ArrayList<>();
        databaseReference = ConfiguracaoFireBase.getFirebaseDatabase();

        databaseReference.child("usuarios").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dn : snapshot.getChildren()) {

                    if (dn.getValue() != null) {
                        PacienteModel h = dn.getValue(PacienteModel.class);
                        pacientes.add(h);

                    }
                }
                pacienteAdapter = new PacienteAdapter(pacientes);
                recyclerView.setAdapter(pacienteAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}