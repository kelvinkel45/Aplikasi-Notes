package com.example.knote;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private Context context;
    private List<Note> noteList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_note, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Note note = noteList.get(i) ;

        holder.note.setText((CharSequence) note.getNote());
        holder.titik.setText(Html.fromHtml("&#8826"));
        holder.waktu.setText(formatDate(note.getWaktu()));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView note;
        public TextView titik;
        public TextView waktu;

        public MyViewHolder(View view){
            super(view);
            note = view.findViewById(R.id.note);
            titik = view.findViewById(R.id.titik);
            waktu = view.findViewById(R.id.waktu);
        }
    }

    public NoteAdapter (Context context, List<Note>noteList){
        this.context = context;
        this.noteList = noteList;
    }

    private String formatDate(String dateStr){
        try{
            SimpleDateFormat  sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateStr);
            SimpleDateFormat sdfOut = new SimpleDateFormat("MMM d HH:mm");
            return sdfOut.format(date);
        } catch (ParseException e) {


        }
        return "";
    }


}

