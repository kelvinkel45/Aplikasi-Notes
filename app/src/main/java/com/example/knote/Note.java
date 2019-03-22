package com.example.knote;

public class Note {
    public static final String TABLE_NAME = "note";
    public static final String COL1 = "id";
    public static final String COL2 = "catatan";
    public static final String COL3 = "waktu";

    private int id;
    private String note;
    private String waktu;

    public static final  String create = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL2+" TEXT,"+ COL3 +" DATETIME DEFAULT CURRENT_TIMESTAMP)";

    public Note(){

    }

    public Note(int id, String note, String waktu){
        this.id =id;
        this.note = note;
        this.waktu = waktu;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getWaktu(){
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

}

