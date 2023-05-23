package com.example.curso_online.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.curso_online.R;
import com.example.curso_online.entities.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoAdapter extends ListAdapter<Curso, CursoAdapter.CursoHolder> {
    public static final String EXTRA_CURSO_ID = "com.example.curso_online.EXTRA_CURSO_ID";
    private OnItemClickListener listener;

    public CursoAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Curso> DIFF_CALLBACK = new DiffUtil.ItemCallback<Curso>() {
        @Override
        public boolean areItemsTheSame(@NonNull Curso oldItem, @NonNull Curso newItem) {
            return oldItem.getCursoId() == newItem.getCursoId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Curso oldItem, @NonNull Curso newItem) {
            return oldItem.getNomeCurso().equals(newItem.getNomeCurso()) &&
                    oldItem.getQtdeHoras() == newItem.getQtdeHoras();
        }
    };

    @NonNull
    @Override
    public CursoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.curso_item, parent, false);
        return new CursoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoHolder holder, int position) {
        Curso currentCurso = getItem(position);
        holder.textViewTitle.setText(currentCurso.getNomeCurso());
        holder.textViewDescription.setText(String.valueOf(currentCurso.getQtdeHoras()));
    }

    public Curso getCursoAt(int position) {
        return getItem(position);
    }

    class CursoHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;

        public CursoHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Curso curso);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
