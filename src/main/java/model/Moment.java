package model;

import java.time.LocalDate;

public class Moment {

    private int id; //¿porque final? R// el valor no puede ser modificado despues de usarse.
    private String title;
    private String description;
    private Emotion emotion;
    private LocalDate momentDate;
    private LocalDate creationDate;
    private LocalDate modificationDate;

    public Moment(int id, String title, String description, Emotion emotion, LocalDate momentDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.momentDate = momentDate;
        this.emotion = emotion;
        this.creationDate = LocalDate.now();
        this.modificationDate = this.creationDate;

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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

}
