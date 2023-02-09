package mz.ac.isutc.nolinesadmin.models;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.media.MediaDrm;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mz.ac.isutc.nolinesadmin.DetalhesActivity;
import mz.ac.isutc.nolinesadmin.DetalhesMarcacoesActivity;
import mz.ac.isutc.nolinesadmin.PacientesActivity;
import mz.ac.isutc.nolinesadmin.R;
import mz.ac.isutc.nolinesadmin.config.ConfiguracaoFireBase;

public class MarcacaoAdapter extends RecyclerView.Adapter {
    List<MarcacaoModel> marcacoes;
    private List<PacienteModel> pacientes;

    private DatabaseReference databaseReference;


    public MarcacaoAdapter(List<MarcacaoModel> marcacoes) {
        this.marcacoes = marcacoes;
    }

    public MarcacaoAdapter(List<PacienteModel> pacientes, int nr) {
        this.pacientes = pacientes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marcacao, parent, false);
        ViewHolderClass vhClass = new ViewHolderClass(view);

        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass vhClass = (MarcacaoAdapter.ViewHolderClass) holder;
        MarcacaoModel marcacao = marcacoes.get(position);


        vhClass.txtV_sector.setText(marcacao.getSector());
        vhClass.txtV_id_usuario.setText(marcacao.getId());
        vhClass.txtV_nome_paciente.setText(marcacao.getNome_paciente());
    }

    @Override
    public int getItemCount() {
        return marcacoes.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{

        TextView txtV_sector, txtV_data, txtV_hora, txtV_id,txtV_id_usuario, txtV_nome_paciente;
        ImageView btn_remover;

        private FirebaseDatabase db = FirebaseDatabase.getInstance();;
        private DatabaseReference root = db.getReference().child("marcacoes");

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);


            btn_remover = itemView.findViewById(R.id.btn_remover);

            txtV_id_usuario = itemView.findViewById(R.id.txtV_id_paciente);
            txtV_sector = itemView.findViewById(R.id.txtV_sector);

            txtV_nome_paciente = itemView.findViewById(R.id.txtV_marcacao_nome);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrir();
                }

                public void abrir(){

                    Intent intent = new Intent(itemView.getContext(), DetalhesMarcacoesActivity.class);
                    int position = getAdapterPosition();


                    intent.putExtra("id_agenda", marcacoes.get(position).getId_agenda());
                    intent.putExtra("id_paciente", marcacoes.get(position).getId());
                    intent.putExtra("nome_paciente", marcacoes.get(position).getNome_paciente());

                    intent.putExtra("sector", marcacoes.get(position).getSector());
                    intent.putExtra("data", marcacoes.get(position).getData());
                    intent.putExtra("horario", marcacoes.get(position).getHora());



                    itemView.getContext().startActivity(intent);
                }
            });

            btn_remover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "removendo..." + txtV_sector.getText(), Toast.LENGTH_LONG).show();

                    String id_agenda = txtV_id.getText().toString();

                    root.child(id_agenda).removeValue();

                }
            });


        }
    }
}
