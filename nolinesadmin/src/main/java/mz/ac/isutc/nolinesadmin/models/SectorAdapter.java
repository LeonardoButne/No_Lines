package mz.ac.isutc.nolinesadmin.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import mz.ac.isutc.nolinesadmin.R;

public class SectorAdapter extends RecyclerView.Adapter {
    List<SectorModel> sectores;

    public SectorAdapter(List<SectorModel> sectores) {
        this.sectores = sectores;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sector, parent, false);
        ViewHolderClass vhClass = new ViewHolderClass(view);

        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        ViewHolderClass vhClass = (SectorAdapter.ViewHolderClass) holder;
        SectorModel sector = sectores.get(position);



        vhClass.txtV_nome.setText(sector.getNome());
        vhClass.txtV_id.setText(sector.getId());



        //sectores.remove(position);

    }

    @Override
    public int getItemCount() {
        return sectores.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {

        TextView txtV_nome, txtV_id;
        ImageView btn_remover;

        private FirebaseDatabase db = FirebaseDatabase.getInstance();;
        private DatabaseReference root = db.getReference().child("sectores");

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            btn_remover = itemView.findViewById(R.id.btn_apagar_sector);
            txtV_nome = itemView.findViewById(R.id.txt_nome_sector);
            txtV_id = itemView.findViewById(R.id.txt_id_sector);

            btn_remover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "removendo..." + txtV_nome.getText(), Toast.LENGTH_LONG).show();

                    String id_sector = txtV_id.getText().toString();

                    SectorModel sectorModel = new SectorModel();

                    root.child(id_sector).removeValue();
                }
            });

        }
    }
}
