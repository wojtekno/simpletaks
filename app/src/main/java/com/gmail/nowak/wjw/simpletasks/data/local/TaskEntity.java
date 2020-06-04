package com.gmail.nowak.wjw.simpletasks.data.local;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")
public class TaskEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    @Ignore
    public TaskEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TaskEntity(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
