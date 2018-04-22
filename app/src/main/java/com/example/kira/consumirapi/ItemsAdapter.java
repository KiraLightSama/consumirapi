package com.example.kira.consumirapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter <ItemsAdapter.ViewHolder> {

    private List<Item> dataset;
    private Context context;

    public ItemsAdapter(Context context) {
        this.dataset = new ArrayList<>();
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitulo;
        private TextView tvBody;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_articulo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item listaItem = dataset.get(position);
        holder.tvTitulo.setText(listaItem.getTitle());
        holder.tvBody.setText(listaItem.getBody());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaItems (List<Item> listaItems) {
        dataset.addAll(listaItems);
        notifyDataSetChanged();
    }
}
