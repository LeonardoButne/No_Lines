package mz.ac.isutc.help.ui.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    }

    @Override
    public int getItemCount() {
        return marcacoes.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{

        TextView txtV_sector, txtV_data, txtV_hora;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            txtV_sector = itemView.findViewById(R.id.txtV_sector);
            txtV_data = itemView.findViewById(R.id.txtV_data);
            txtV_hora = itemView.findViewById(R.id.txtV_hora);


        }
    }
}
