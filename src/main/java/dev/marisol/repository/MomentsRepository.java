package dev.marisol.repository;

import java.util.ArrayList;
import java.util.List;
import dev. marisol.model.Moment;

public class MomentsRepository {
    private List<Moment> momentList= new ArrayList<>();

    public void addMoment(Moment moment){
        momentList.add(moment);
    }
    public List<Moment> findAll() {
        return momentList;
    }
}
