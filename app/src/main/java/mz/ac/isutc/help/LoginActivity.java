package mz.ac.isutc.help;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mz.ac.isutc.help.databinding.ActivityLoginBinding;
import mz.ac.isutc.help.databinding.FragmentHomeBinding;
import mz.ac.isutc.help.ui.models.Usuario;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FragmentHomeBinding bindingHome;
    private EditText edtEmail, edtSenha;
    private Button btnEntrar;
    private Usuario usuario;
    private FirebaseAuth auth;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth =  FirebaseAuth.getInstance();

        checkBox();

        btnEntrar = findViewById(R.id.btnEntrar);

        binding.edtEmail.requestFocus();

        binding.btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receberDados();
                logar();
            }
        });
        binding.txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, FormCadastro.class));
            }
        });

    }

    private void checkBox(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString("name","");
        if(check.equals("true")){
            Toast.makeText(LoginActivity.this, "Sucesso.",
                    Toast.LENGTH_SHORT).show();
            FirebaseUser user = auth.getCurrentUser();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

            finish();
        }
    }

    private void logar() {

        auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("name", "true");
                            editor.apply();

                            Toast.makeText(LoginActivity.this, "Sucesso.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = auth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        } else {
                            Toast.makeText(LoginActivity.this, "Falhou",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void receberDados(){

        usuario = new Usuario();

        String email = binding.edtEmail.getText().toString();
        String senha = binding.edtSenha.getText().toString();

        usuario.setEmail(email);
        usuario.setSenha(senha);


    }

}