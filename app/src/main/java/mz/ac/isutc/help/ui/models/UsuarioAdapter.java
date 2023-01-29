package mz.ac.isutc.help.ui.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mz.ac.isutc.help.R;

public class UsuarioAdapter extends RecyclerView.Adapter {

    List<Usuario> usuarios;

    public UsuarioAdapter(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        ViewHolderClass vhClass = new ViewHolderClass(view);
        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass vhClass = (ViewHolderClass) holder;
        Usuario usuario = usuarios.get(position);

        vhClass.txtV_nome.setText(usuario.getNome());
        vhClass.txtV_email.setText(usuario.getEmail());
        vhClass.txtV_senha.setText(usuario.getSenha());
        vhClass.txtV_telefone.setText(usuario.getTelefone());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{

        TextView txtV_email, txtV_senha, txtV_nome, txtV_telefone;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            txtV_nome = itemView.findViewById(R.id.txtV_nome);
            txtV_email = itemView.findViewById(R.id.txtV_email);
            txtV_senha = itemView.findViewById(R.id.txtV_senhaa);
            txtV_telefone = itemView.findViewById(R.id.txtV_telefone);

        }
    }
}
