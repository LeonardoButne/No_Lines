package mz.ac.isutc.help.ui.models;

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

import java.util.List;

import mz.ac.isutc.help.R;

public class MarcacaoAdapter extends RecyclerView.Adapter {
    List<Marcacao> marcacoes;

    public MarcacaoAdapter(List<Marcacao> marcacoes) {
        this.marcacoes = marcacoes;
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
        Marcacao marcacao = marcacoes.get(position);


        vhClass.txtV_sector.setText(marcacao.getSector());
        vhClass.txtV_data.setText(marcacao.getData());
        vhClass.txtV_hora.setText(marcacao.getHora());
        vhClass.txtV_id.setText(marcacao.getId_agenda());

    }

    @Override
    public int getItemCount() {
        return marcacoes.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{

        TextView txtV_sector, txtV_data, txtV_hora, txtV_id;
        ImageView btn_remover;

        private FirebaseDatabase db = FirebaseDatabase.getInstance();;
        private DatabaseReference root = db.getReference().child("marcacoes");

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);


            btn_remover = itemView.findViewById(R.id.btn_remover);
            txtV_sector = itemView.findViewById(R.id.txtV_sector);
            txtV_data = itemView.findViewById(R.id.txtV_data);
            txtV_hora = itemView.findViewById(R.id.txtV_hora);
            txtV_id = itemView.findViewById(R.id.txtV_id);

            btn_remover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "removendo..." + txtV_sector.getText(), Toast.LENGTH_LONG).show();

                    String id_agenda = txtV_id.getText().toString();
                    String data = txtV_data.getText().toString();
                    String hora = txtV_hora.getText().toString();
                    String sector = txtV_sector.getText().toString();

                    String id_usuario = FirebaseAuth.getInstance().getCurrentUser().getUid ();

                    Marcacao marcacao = new Marcacao();

                    root.child(id_agenda).removeValue();
                    //root.removeValue();

                }
            });


        }
    }
}
