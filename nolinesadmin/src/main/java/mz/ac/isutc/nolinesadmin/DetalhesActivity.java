package mz.ac.isutc.nolinesadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mz.ac.isutc.nolinesadmin.R;
import mz.ac.isutc.nolinesadmin.databinding.ActivityDetalhesBinding;

public class DetalhesActivity extends AppCompatActivity {
    private ActivityDetalhesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetalhesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            String id = bundle.getString("id");
            String nome = bundle.getString("nome");
            String email = bundle.getString("email");
            String telefone = bundle.getString("telefone");

            binding.txtVDetalhesId.setText(id);
            binding.txtVDetalhesNome.setText(nome);
            binding.txtVDetalhesEmail.setText(email);
            binding.txtVDetalhesTel.setText(telefone);
        }


    }
}