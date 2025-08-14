package dev.marisol.services;

import dev.marisol.model.Emotion;
import dev.marisol.model.Moment;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MomentService {
    private List<Moment> moments = new ArrayList<>();

    public List<Moment> getAllMoments() {
        return this.moments;

    }

    public void addMoment(Moment moment) {
        this.moments.add(moment);
    }

    public void deleteMoment(int id) {
        moments.removeIf(moment -> moment.getId() == id);
    }

    public List<Moment> filterByEmotion(Emotion emotion) {
        return this.moments.stream()
                .filter(moment -> moment.getEmotion() == emotion)
                .collect(Collectors.toList());
    }

    public List<Moment> getMonthMoments(int month, int year) {
        return this.moments.stream()
                .filter(moment -> moment.getMomentDate().getMonthValue() == month
                        && moment.getMomentDate().getYear() == year)
                .collect(Collectors.toList());
    }
}