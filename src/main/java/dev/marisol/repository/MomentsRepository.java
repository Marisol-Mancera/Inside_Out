package dev.marisol.repository;

import dev.marisol.model.Emotion;
import dev.marisol.model.Moment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MomentsRepository {
    private List<Moment> momentList = new ArrayList<>();

    public void addMoment(Moment moment) {
        momentList.add(moment);
    }

    public List<Moment> findAll() {
        return momentList;
    }

    public boolean deleteMoment(int id) {
        return momentList.removeIf(m -> m.getId() == id);
    }

    public List<Moment> filterByEmotion(Emotion emotion) {
        return momentList.stream()
                .filter(m -> m.getEmotion() == emotion)
                .toList();
    }

    public List<Moment> filterByDate(LocalDate date) {
        return momentList.stream()
                .filter(m -> m.getMomentDate().equals(date))
                .toList();
    }

    public List<Moment> filterByCategory(boolean category) {
        return momentList.stream()
                .filter(m -> m.isPositive() == category)
                .toList();
    }
}
