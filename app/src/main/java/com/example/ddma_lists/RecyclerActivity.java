package com.example.ddma_lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

public class RecyclerActivity extends AppCompatActivity{

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler);

        // Инициализация RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Подготовка данных
        ArrayList<Notebook> notebooks = new ArrayList<>();
        notebooks.add(new Notebook("Apple", "M1", R.drawable.ic_apple_m1));
        notebooks.add(new Notebook("Honor", "Ryzen 5",R.drawable.ic_honor_5));
        notebooks.add(new Notebook("Msi", "Core i7", R.drawable.ic_msi_i7));
        notebooks.add(new Notebook("Apple", "M2 pro", R.drawable.ic_apple_m2pro));
        notebooks.add(new Notebook("Apple", "M4 max",R.drawable.ic_apple_m4max));

        // Установка адаптера
        ReAdapter adapter = new ReAdapter(notebooks);
        recyclerView.setAdapter(adapter);
    }
}

class ReAdapter extends RecyclerView.Adapter<ReAdapter.ReItemView> {
    private final ArrayList<Notebook> notebook;

    ReAdapter(ArrayList<Notebook> x) {
        this.notebook = x;
    }

    @NonNull
    @Override
    public ReItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list, parent, false);
        return new ReItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReItemView holder, int position) {
        holder.tvName.setText(notebook.get(position).getName());
        holder.tvCpu.setText(notebook.get(position).getCpuName());
        holder.itemView.setOnClickListener(v ->
                Snackbar.make(v, "Position " + holder.getAdapterPosition(), Snackbar.LENGTH_LONG).show()
        );
    }

    @Override
    public int getItemCount() {
        return notebook.size();
    }

    static class ReItemView extends RecyclerView.ViewHolder {
        final TextView tvName;
        final TextView tvCpu;

        public ReItemView(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textName);
            tvCpu = itemView.findViewById(R.id.textCpu);
        }
    }
}







