package com.longthph30891.note_mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.longthph30891.note_mvp.R;
import com.longthph30891.note_mvp.adapter.NoteAdapter;
import com.longthph30891.note_mvp.database.NoteDao;
import com.longthph30891.note_mvp.databinding.ActivityMainBinding;
import com.longthph30891.note_mvp.model.Note;
import com.longthph30891.note_mvp.presenter.MainPresenter;
import com.longthph30891.note_mvp.view.interfaceView.MainInterface;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainInterface {
    private ActivityMainBinding binding;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainPresenter = new MainPresenter(this);
        mainPresenter.loadData(this,binding.rcvNotes,mainPresenter);
        binding.btnAdd.setOnClickListener(view -> {
            mainPresenter.addNote();
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void addSuccess() {
        Toast.makeText(this, "Add note success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addFailure() {
        Toast.makeText(this, "Add note fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateSuccess() {
        Toast.makeText(this, "Update note success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateFailure() {
        Toast.makeText(this, "Update note fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteSuccess() {
        Toast.makeText(this, "Delete note success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteFailure() {
        Toast.makeText(this, "Delete note fail", Toast.LENGTH_SHORT).show();
    }
}