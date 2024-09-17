package com.longthph30891.note_mvp.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.longthph30891.note_mvp.adapter.NoteAdapter;
import com.longthph30891.note_mvp.database.NoteDao;
import com.longthph30891.note_mvp.databinding.AddAndUpdateLayoutBinding;
import com.longthph30891.note_mvp.model.Note;
import com.longthph30891.note_mvp.view.interfaceView.MainInterface;

import java.util.ArrayList;

public class MainPresenter {
    private MainInterface mainInterface;
    private NoteDao noteDao;
    private ArrayList<Note> list = new ArrayList<>();
    private NoteAdapter adapter;

    public MainPresenter(MainInterface mainInterface) {
        this.mainInterface = mainInterface;
    }

    public void loadData(Context context, RecyclerView recyclerView, MainPresenter mainPresenter) {
        noteDao = new NoteDao(context);
        list = noteDao.selectAll();
        adapter = new NoteAdapter(context, list, mainPresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }
    public void addNote(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainInterface.getContext());
        LayoutInflater inflater = LayoutInflater.from(mainInterface.getContext());
        AddAndUpdateLayoutBinding binding = AddAndUpdateLayoutBinding.inflate(inflater);
        View view = binding.getRoot();
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        binding.btnBack.setOnClickListener(view1 -> {
            alertDialog.dismiss();
        });

        binding.btnSave.setOnClickListener(view1 -> {
            String name = binding.edName.getText().toString().trim();
            String description = binding.edDescription.getText().toString().trim();
            Note note = new Note(name,description);
            if (noteDao.insert(note)) {
                list.clear();
                list.addAll(noteDao.selectAll());
                adapter.notifyDataSetChanged();
                mainInterface.addSuccess();
                alertDialog.dismiss();
            } else {
                mainInterface.addFailure();
            }
        });
    }
    public void updateNote(Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainInterface.getContext());
        LayoutInflater inflater = LayoutInflater.from(mainInterface.getContext());
        AddAndUpdateLayoutBinding binding = AddAndUpdateLayoutBinding.inflate(inflater);
        View view = binding.getRoot();
        builder.setView(view);

        binding.edName.setText(note.getName());
        binding.edDescription.setText(note.getDescription());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        binding.btnBack.setOnClickListener(view1 -> {
            alertDialog.dismiss();
        });

        binding.btnSave.setOnClickListener(view1 -> {
            note.setName(binding.edName.getText().toString().trim());
            note.setDescription(binding.edDescription.getText().toString().trim());

            if (noteDao.update(note)) {
                list.clear();
                list.addAll(noteDao.selectAll());
                adapter.notifyDataSetChanged();
                mainInterface.updateSuccess();
                alertDialog.dismiss();
            }else {
                mainInterface.updateFailure();
            }
        });
    }

    public void deleteNote(Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainInterface.getContext());
        builder.setTitle("Thong Bao!");
        builder.setMessage("Bạn có muốn xóa sám phẩm này không ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(noteDao.delete(note.getId())){
                    list.clear();
                    list.addAll(noteDao.selectAll());
                    adapter.notifyDataSetChanged();
                    mainInterface.deleteSuccess();
                }else {
                   mainInterface.deleteFailure();
                }
            }
        });
        builder.setNegativeButton("No",null);
        builder.create().show();
    }
}
