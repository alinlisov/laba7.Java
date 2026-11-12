package workshop;
import java.util.*;
public final class Developer {
    private final String name;
    private final List<String> languages;
    public Developer(String name, List<String> languages) {
        this.name = Objects.requireNonNull(name);
        this.languages = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(languages)));
    }
    public String getName() { return name; }
    public List<String> getLanguages() { return languages; }
    @Override public String toString() { return name + " " + languages; }
}
