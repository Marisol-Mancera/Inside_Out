package dev.marisol.model;

    public enum Emotion {
    // 1. Cada constante ahora llama al constructor con su traducción
    HAPPINESS("Alegría"),
    SADNESS("Tristeza"),
    ANGER("Ira"),
    DISGUST("Asco"),
    FEAR("Miedo"),
    ANXIETY("Ansiedad"),
    ENVY("Envidia"),
    SHAME("Vergüenza"),
    BOREDOM("Aburrimiento"),
    NOSTALGIA("Nostalgia");

    // 2. Un campo privado para guardar la traducción
    private final String displayName;

    // 3. Un constructor para inicializar el campo
    Emotion(String displayName) {
        this.displayName = displayName;
    }

    // 4. Un método público para obtener la traducción
    public String getDisplayName() {
        return displayName;
    }
    }
