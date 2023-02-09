package mz.ac.isutc.nolinesadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import mz.ac.isutc.nolinesadmin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view =  binding.getRoot();
        setContentView(view);

        binding.cardSectores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Sectores", Toast.LENGTH_SHORT).show();

                binding.cardSectores.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
                startActivity(new Intent(MainActivity.this, SectoresActivity.class));


            }
        });
        binding.cardHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Horarios", Toast.LENGTH_SHORT).show();

                binding.cardHorarios.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
                startActivity(new Intent(MainActivity.this, HorariosActivity.class));
            }
        });
        binding.cardMarcacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Marcacoes", Toast.LENGTH_SHORT).show();

                binding.cardMarcacoes.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
                startActivity(new Intent(MainActivity.this, MarcacoesActivity.class));
            }
        });
        binding.cardPacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Pacientes", Toast.LENGTH_SHORT).show();

                binding.cardPacientes.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
                startActivity(new Intent(MainActivity.this, PacientesActivity.class));
            }
        });

    }
}