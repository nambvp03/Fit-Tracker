package com.codingminds.fittracker;

public class FoodEntry {
    private String name;
    private int serving;
    private long timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCalTotal() {
        Integer cal = FoodDB.DB.get(name);
        if (cal == null) {
            cal = 0;
        }
        return cal * serving;
    }
}
