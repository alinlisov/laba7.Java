package workshop;
import java.util.*;
import java.util.stream.*;
public final class DeveloperService {
    // Task 2.1: flatten every developer's list, remove duplicates, keep input order.
    public List<String> getAllLanguages(List<Developer> team) {
        Objects.requireNonNull(team);
        return team.stream()
                .map(Developer::getLanguages)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }
    // Task 2.2:
    public List<String> getDevelopersByLanguage(List<Developer> team, String language) {
        Objects.requireNonNull(team); Objects.requireNonNull(language);
        return team.stream().filter(d -> d.getLanguages().contains(language))
                .map(Developer::getName).collect(Collectors.toList());
    }
    public Map<String, Long> countDevelopersByLanguage(List<Developer> team) {
        Objects.requireNonNull(team);
        return team.stream().flatMap(d -> d.getLanguages().stream().distinct())
                .collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()));
    }
    public Map<String, List<String>> groupDevelopersByLanguage(List<Developer> team) {
        Objects.requireNonNull(team);
        return team.stream().flatMap(d -> d.getLanguages().stream().distinct()
                .map(lang -> new AbstractMap.SimpleEntry<>(lang, d.getName())))
                .collect(Collectors.groupingBy(Map.Entry::getKey, LinkedHashMap::new,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }
}
