package com.longthph30891.note_mvp.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.longthph30891.note_mvp.database.NoteDao;
import com.longthph30891.note_mvp.databinding.ItemNoteBinding;
import com.longthph30891.note_mvp.model.Note;
import com.longthph30891.note_mvp.presenter.MainPresenter;
import com.longthph30891.note_mvp.view.interfaceView.MainInterface;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{
    private final Context context;
    private ArrayList<Note>list;
    private MainPresenter mainPresenter;
    NoteDao noteDao;

    public NoteAdapter(Context context, ArrayList<Note> list,MainPresenter mainPresenter) {
        this.context = context;
        this.list = list;
        this.mainPresenter = mainPresenter;
        noteDao = new NoteDao(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteBinding binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = list.get(position);
        holder.setData(note);
        holder.binding.btnEdit.setOnClickListener(view -> {
            mainPresenter.updateNote(note);
        });
        holder.itemView.setOnLongClickListener(view -> {
            mainPresenter.deleteNote(note);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        private ItemNoteBinding binding;
        NoteViewHolder(ItemNoteBinding itemNoteBinding){
            super(itemNoteBinding.getRoot());
            binding = itemNoteBinding;
        }
        void setData(Note note){
            binding.tvName.setText(note.getName());
            binding.tvDescription.setText(note.getDescription());
        }
    }
}
