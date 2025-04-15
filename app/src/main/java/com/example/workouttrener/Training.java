package com.example.workouttrener;

public class Training {
    private String name;
    private String type;
    private int duration;
    private String difficulty;

    public Training(String name, String type, int duration, String difficulty) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.difficulty = difficulty;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
