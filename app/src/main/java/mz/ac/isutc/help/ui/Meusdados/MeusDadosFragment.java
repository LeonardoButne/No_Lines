package mz.ac.isutc.help.ui.Meusdados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import mz.ac.isutc.help.databinding.FragmentMeusdadosBinding;
import mz.ac.isutc.help.ui.config.ConfiguracaoFireBase;
import mz.ac.isutc.help.ui.models.Usuario;
import mz.ac.isutc.help.ui.models.UsuarioAdapter;

public class MeusDadosFragment extends Fragment {

    private FragmentMeusdadosBinding binding;

    private List<Usuario> usuarios;
    RecyclerView recyclerView;
    UsuarioAdapter usuarioAdapter;
    DatabaseReference databaseReference;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMeusdadosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        usuarios = new ArrayList<>();
        databaseReference = ConfiguracaoFireBase.getFirebaseDatabase();

        String uid = FirebaseAuth.getInstance().getCurrentUser ().getUid ();

        databaseReference.child("usuarios").orderByChild("id").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn: snapshot.getChildren()){
                    Usuario u = dn.getValue(Usuario.class);
                    usuarios.add(u);
                }

                usuarioAdapter = new UsuarioAdapter(usuarios);
                recyclerView.setAdapter(usuarioAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}