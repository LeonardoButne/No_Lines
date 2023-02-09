package mz.ac.isutc.nolinesadmin.models;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import mz.ac.isutc.nolinesadmin.R;
import mz.ac.isutc.nolinesadmin.DetalhesActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PacienteAdapter extends RecyclerView.Adapter {

    List<PacienteModel> pacientes;

    public PacienteAdapter(List<PacienteModel> pacientes) {
        this.pacientes = pacientes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paciente, parent, false);
        ViewHolderClass vhClass = new ViewHolderClass(view);
        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass vhClass = (ViewHolderClass) holder;
        PacienteModel paciente = pacientes.get(position);

        vhClass.txtV_nome.setText(paciente.getNome());
        vhClass.txtV_uid.setText(paciente.getId());
    }

    @Override
    public int getItemCount() {
        return pacientes.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{

        TextView txtV_nome, txtV_uid;
        ImageView btn_remover;

        private FirebaseDatabase db = FirebaseDatabase.getInstance();;
        private DatabaseReference root = db.getReference().child("usuarios");

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            txtV_nome = itemView.findViewById(R.id.txtV_nome);
            txtV_uid = itemView.findViewById(R.id.txtV_uid);

            btn_remover = itemView.findViewById(R.id.btn_remover_paciente);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrir();

                }

                public void abrir(){

                    Intent intent = new Intent(itemView.getContext(), DetalhesActivity.class);
                    int position = getAdapterPosition();


                    intent.putExtra("id", pacientes.get(position).getId());

                    intent.putExtra("nome", pacientes.get(position).getNome());

                    intent.putExtra("email", pacientes.get(position).getEmail());

                    intent.putExtra("telefone", pacientes.get(position).getTelefone());


                    itemView.getContext().startActivity(intent);
                }


            });

            btn_remover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "removendo..." + txtV_nome.getText(), Toast.LENGTH_LONG).show();

                    String id_paciente = txtV_uid.getText().toString();

                    root.child(id_paciente).removeValue();

                    notifyDataSetChanged();
                }
            });


        }
    }
}
