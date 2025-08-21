package dev.marisol.service;

import dev.marisol.model.Emotion;
import dev.marisol.model.Moment;

import dev.marisol.repository.MomentsRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MomentService {

    private MomentsRepository repository; 
    private final List<Moment> inMemory = new ArrayList<>();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int idSeq = 0;

    public MomentService() {
    }

    public MomentService(MomentsRepository repository) {
        this.repository = repository;
    }

    public List<Moment> getAllMoments() {
        if (repository != null) {
            return repository.findAll();
        }
        return inMemory;
    }

    public void addMoment(Moment moment) {
        if (moment.getId() == 0) {
            idSeq = Math.max(idSeq, getAllMoments().stream().mapToInt(Moment::getId).max().orElse(0));
            moment.setModificationDate(LocalDate.now());
        }
        if (repository != null) {
            repository.addMoment(moment);
        } else {
            inMemory.add(moment);
        }
    }

    public void deleteMoment(int id) {
        if (repository != null) {
            repository.deleteMoment(id);
        } else {
            inMemory.removeIf(m -> m.getId() == id);
        }
    }

    public List<Moment> filterByEmotion(Emotion emotion) {
        if (repository != null) {
            return repository.filterByEmotion(emotion);
        }
        List<Moment> out = new ArrayList<>();
        for (Moment m : inMemory) {
            if (m.getEmotion() == emotion) out.add(m);
        }
        return out;
    }

    public List<Moment> getMonthMoments(int month, int year) {
        if (repository != null) {
            return repository.findAll().stream()
                    .filter(m -> m.getMomentDate() != null
                            && m.getMomentDate().getMonthValue() == month
                            && m.getMomentDate().getYear() == year)
                    .collect(Collectors.toList());
        }
        List<Moment> out = new ArrayList<>();
        for (Moment m : inMemory) {
            if (m.getMomentDate() != null
                    && m.getMomentDate().getMonthValue() == month
                    && m.getMomentDate().getYear() == year) {
                out.add(m);
            }
        }
        return out;
    }

}
