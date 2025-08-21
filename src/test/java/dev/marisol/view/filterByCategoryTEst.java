import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FilterByCategoryViewTest {

    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
    }

    private FilterByCategoryView createWithInput(String simulatedInput) {
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        return new FilterByCategoryView(new Scanner(System.in));
    }

    @Test
    void shouldReturnTrueWhenUserSelectsPositive() {
        // Simula que el usuario teclea "1"
        FilterByCategoryView view = createWithInput("1\n");
        boolean result = view.filterCategory();
        assertTrue(result, "Debe devolver true cuando el usuario selecciona positivo");
    }

   
}
