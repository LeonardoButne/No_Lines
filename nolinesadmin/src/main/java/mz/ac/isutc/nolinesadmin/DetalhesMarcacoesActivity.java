package mz.ac.isutc.nolinesadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mz.ac.isutc.nolinesadmin.databinding.ActivityDetalhesBinding;
import mz.ac.isutc.nolinesadmin.databinding.ActivityDetalhesMarcacoesBinding;

public class DetalhesMarcacoesActivity extends AppCompatActivity {
    private ActivityDetalhesMarcacoesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetalhesMarcacoesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            String id_agenda = bundle.getString("id_agenda");
            String id_paciente = bundle.getString("id_paciente");
            String nome_paciente = bundle.getString("nome_paciente");
            String sector = bundle.getString("sector");
            String data = bundle.getString("data");
            String horario = bundle.getString("horario");


            binding.txtData.setText(data);
            binding.txtHorario.setText(horario);
            binding.txtIdAgenda.setText(id_agenda);

            binding.txtIdPaciente.setText(id_paciente);
            binding.txtNomePaciente.setText(nome_paciente);
            binding.txtSector.setText(sector);

        }


    }
}