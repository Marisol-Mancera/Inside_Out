package dev.marisol.services;
import dev.marisol.model.Moment;
import java.util.List;
import java.util.ArrayList;

public class MomentService {
    private List<Moment> moments = new ArrayList<>(); 
    
     public List<Moment> getAllMoments() { 
        return this.moments;

    }
    public void addMoment(Moment moment) {
        this.moments.add(moment);
    }
}