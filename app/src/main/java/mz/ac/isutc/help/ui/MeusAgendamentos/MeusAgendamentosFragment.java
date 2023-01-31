package mz.ac.isutc.help.ui.MeusAgendamentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mz.ac.isutc.help.databinding.FragmentMeusagendamentosBinding;
import mz.ac.isutc.help.ui.config.ConfiguracaoFireBase;
import mz.ac.isutc.help.ui.models.Marcacao;
import mz.ac.isutc.help.ui.models.MarcacaoAdapter;


public class MeusAgendamentosFragment extends Fragment {

    private FragmentMeusagendamentosBinding binding;
    private ImageView btn_remover;


    private List<Marcacao> marcacoes;
    RecyclerView recyclerView;
    MarcacaoAdapter marcacaoAdapter;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMeusagendamentosBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = binding.recyclerView1;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        marcacoes = new ArrayList<>();
        databaseReference = ConfiguracaoFireBase.getFirebaseDatabase();

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.child("marcacoes").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dn : snapshot.getChildren()) {

                    if(dn.getValue() != null){
                        Marcacao m = dn.getValue(Marcacao.class);

                        if (m.getId().equals(uid)) {
                            marcacoes.add(m);
                        }


                    }

                }
//
//
//                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//                System.out.println(uid);
//
//                for (int i = 0; i < marcacoes.size(); i++) {
//                    if (!uid.equalsIgnoreCase(marcacoes.get(i).getId())) {
//
//                        marcacoes.remove(marcacoes.get(i));
//
//                    }
//                }

                marcacaoAdapter = new MarcacaoAdapter(marcacoes);
                recyclerView.setAdapter(marcacaoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
