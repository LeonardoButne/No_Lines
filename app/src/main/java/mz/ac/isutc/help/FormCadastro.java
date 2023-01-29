package mz.ac.isutc.help;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import mz.ac.isutc.help.databinding.ActivityFormCadastroBinding;
import mz.ac.isutc.help.ui.config.ConfiguracaoFireBase;
import mz.ac.isutc.help.ui.models.Usuario;


public class FormCadastro extends AppCompatActivity {

    private ActivityFormCadastroBinding binding;
    private FirebaseAuth auth;
    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(FormCadastro.this);

        binding = ActivityFormCadastroBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth =  FirebaseAuth.getInstance();

        binding.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recuperarDados();
                criarLogin();
            }
        });
    }

    private void recuperarDados(){

        String nome = binding.edtNome.getText().toString();
        String telefone = binding.edtTelefone.getText().toString();
        String email = binding.edtEmailCadastro.getText().toString();
        String senha = binding.edtSenha.getText().toString();
        String senha2 = binding.edtSenha2.getText().toString();

        if(email.isEmpty() || senha.isEmpty() || senha2.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }else if(!senha.equals(senha2)){
            Toast.makeText(this, "A primeira senha e diferente da segunda", Toast.LENGTH_SHORT).show();
        }else{
            usuario = new Usuario();

            usuario.setNome(nome);
            usuario.setTelefone(telefone);
            usuario.setEmail(email);
            usuario.setSenha(senha2);
        }
    }
    public void criarLogin() {
        auth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(FormCadastro.this , "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();


                    FirebaseUser user = auth.getCurrentUser();
                    usuario.setId(user.getUid());
                    usuario.salvarDados();

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(FormCadastro.this , "O Cadastro falou", Toast.LENGTH_SHORT).show();

                }
            }
        } );

    }


}