package mz.ac.isutc.nolinesadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import mz.ac.isutc.nolinesadmin.databinding.ActivitySectoresBinding;
import mz.ac.isutc.nolinesadmin.models.HorarioAdapter;
import mz.ac.isutc.nolinesadmin.models.HorarioModel;
import mz.ac.isutc.nolinesadmin.models.SectorAdapter;
import mz.ac.isutc.nolinesadmin.models.SectorModel;

public class SectoresActivity extends AppCompatActivity {

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("sectores");

    private ActivitySectoresBinding binding;
    private SectorModel sector = new SectorModel();

    private List<SectorModel> sectores;
    RecyclerView recyclerView;
    SectorAdapter sectorAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySectoresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.recyclerView2;

        recyclerView.setLayoutManager(new LinearLayoutManager(SectoresActivity.this));
        sectores = new ArrayList<>();
        databaseReference = ConfiguracaoFireBase.getFirebaseDatabase();

        binding.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recuperarDados() == true) {
                    Toast.makeText(SectoresActivity.this, "Salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
                    binding.edtSector.setText(null);
                    criarMarcacao();
                    sectorAdapter = new SectorAdapter(sectores);


                }

            }
        });

        databaseReference.child("sectores").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dn : snapshot.getChildren()) {

                    if (dn.getValue() != null) {
                        SectorModel h = dn.getValue(SectorModel.class);
                        sectores.add(h);
                    }
                }
                sectorAdapter = new SectorAdapter(sectores);
                recyclerView.setAdapter(sectorAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void criarMarcacao() {
        HashMap<String, String> marcacaoMap = new HashMap<>();

        root.child(sector.getId()).setValue(sector).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        root.push().setValue(marcacaoMap);

    }

    private boolean recuperarDados(){


        String novo_sector = binding.edtSector.getText().toString();

        if(novo_sector.isEmpty()){
            Toast.makeText(SectoresActivity.this,"Preencha todos os campos primeiro", Toast.LENGTH_SHORT).show();
            return false;
        }else{

            sector = new SectorModel();

            String id_sector = db.getReference().push().getKey();

            sector.setId(id_sector);
            sector.setNome(novo_sector);

            return true;
        }
    }
}