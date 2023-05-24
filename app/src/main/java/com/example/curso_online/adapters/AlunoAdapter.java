
package com.example.curso_online.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.curso_online.R;
import com.example.curso_online.entities.Aluno;
import com.example.curso_online.viewmodel.CursoViewModel;

import java.util.ArrayList;
import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoHolder> {

    private List<Aluno> alunos = new ArrayList<>();
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;
    private CursoViewModel cursoViewModel;
    private LifecycleOwner lifecycleOwner;

    public AlunoAdapter(CursoViewModel cursoViewModel, LifecycleOwner lifecycleOwner) {
        this.cursoViewModel = cursoViewModel;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public AlunoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aluno_item, parent, false);
        return new AlunoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoHolder holder, int position) {
        Aluno currentAluno = alunos.get(position);
        holder.textViewTitle.setText(currentAluno.getNomeAluno());

        // Agora, busque o nome do curso assincronamente
        cursoViewModel.getCursoById(currentAluno.getCursoId()).observe(lifecycleOwner, curso -> {
            if (curso != null) {
                holder.textViewDescription.setText(curso.getNomeCurso());
            } else {
                holder.textViewDescription.setText("Curso não encontrado");  // ou qualquer texto padrão
            }
        });
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
        notifyDataSetChanged();
    }

    class AlunoHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private ImageButton buttonDelete;

        public AlunoHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            buttonDelete = itemView.findViewById(R.id.button_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(alunos.get(position));
                    }
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (longClickListener != null && position != RecyclerView.NO_POSITION) {
                        longClickListener.onItemLongClick(alunos.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Aluno aluno);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Aluno aluno);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }
}
