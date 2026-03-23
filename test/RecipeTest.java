import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.*;

import java.util.List;

public class RecipeTest {

    @Test
    public void testMatchesPreferences() {
        Recipe r = new Recipe("Salad", List.of("lettuce"), 10, List.of());
        UserPreferences prefs = new UserPreferences(15);

        assertTrue(r.matchesPreferences(prefs));
    }

    @Test
    public void testFailsPreferences() {
        Recipe r = new Recipe("Steak", List.of("beef"), 30, List.of());
        UserPreferences prefs = new UserPreferences(15);

        assertFalse(r.matchesPreferences(prefs));
    }
}
