package dev.marisol.model;

import java.time.LocalDate;

public class Moment {
    private int id;
    private String title;
    private String description;
    private Emotion emotion;
    private boolean isPositive;
    private LocalDate momentDate;
    private LocalDate createdDate;
    private LocalDate modifiedDate;

    public Moment(int id, String title, String description, Emotion emotion, LocalDate momentDate, boolean isPositive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.emotion = emotion;
        this.momentDate = momentDate;
        this.createdDate = LocalDate.now();
        this.modifiedDate = LocalDate.now();
        this.isPositive = isPositive;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public LocalDate getMomentDate() {
        return momentDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public void setMomentDate(LocalDate momentDate) {
        this.momentDate = momentDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setPositive(boolean isPositive) {
        this.isPositive = isPositive;
    }
}
