package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> idNamePrev = previous.stream().collect(
                Collectors.toMap(u -> u.getId(), u -> u.getName()));
        Map<Integer, String> idNameCur = current.stream().collect(
                Collectors.toMap(u -> u.getId(), u -> u.getName()));
        for (Map.Entry<Integer, String> entry : idNamePrev.entrySet()) {
            int id = entry.getKey();
            if (!idNameCur.containsKey(id)) {
                deleted++;
            } else {
                if (!idNamePrev.get(id).equals(idNameCur.get(id))) {
                    changed++;
                }
            }
        }
        added = idNameCur.size() + deleted - idNamePrev.size();
        return new Info(added, changed, deleted);
    }

}
