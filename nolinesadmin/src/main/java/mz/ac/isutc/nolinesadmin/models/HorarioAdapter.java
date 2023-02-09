package mz.ac.isutc.nolinesadmin.models;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import mz.ac.isutc.nolinesadmin.R;

    public class HorarioAdapter extends RecyclerView.Adapter {
        List<HorarioModel> horarios;
        public HorarioAdapter(List<HorarioModel> horarios) {
            this.horarios = horarios;
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horario, parent, false);
            HorarioAdapter.ViewHolderClass vhClass = new HorarioAdapter.ViewHolderClass(view);

            return vhClass;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            ViewHolderClass vhClass = (HorarioAdapter.ViewHolderClass) holder;
            HorarioModel horario = horarios.get(position);

            vhClass.txtV_nome.setText(horario.getNome());
            vhClass.txtV_id.setText(horario.getId());
        }

        @Override
        public int getItemCount() {
            return horarios.size();
        }

        public class ViewHolderClass extends RecyclerView.ViewHolder {

            TextView txtV_nome, txtV_id;
            ImageView btn_remover;

            private FirebaseDatabase db = FirebaseDatabase.getInstance();;
            private DatabaseReference root = db.getReference().child("horarios");

            public ViewHolderClass(@NonNull View itemView) {
                super(itemView);

                btn_remover = itemView.findViewById(R.id.btn_apagar_horario);
                txtV_nome = itemView.findViewById(R.id.txt_nome_horario);
                txtV_id = itemView.findViewById(R.id.txt_id_horario);

                btn_remover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();

                        Toast.makeText(itemView.getContext(), "removendo..." + txtV_nome.getText(), Toast.LENGTH_LONG).show();

                        String id_horario = txtV_id.getText().toString();

                        root.child(id_horario).removeValue();

                        notifyDataSetChanged();
                    }
                });
            }
        }
    }
