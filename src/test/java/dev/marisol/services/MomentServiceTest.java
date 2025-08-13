package dev.marisol.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import dev.marisol.model.Moment;

public class MomentServiceTest {

    @Test
    public void shouldReturnEmptyListWhenNoMoments() {

        MomentService service = new MomentService();

        List<Moment> moments = service.moments();

        assertNotNull(moments);
        assertTrue(moments.isEmpty());

    }
}
