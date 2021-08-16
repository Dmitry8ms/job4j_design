package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added;
        int changed = 0;
        int deleted = 0;

        Map<Integer, String> idNameCur = current.stream().collect(
                Collectors.toMap(User::getId, User::getName));
        for (User u : previous) {
            int id = u.getId();
            if (!idNameCur.containsKey(id)) {
                deleted++;
            } else {
                if (!u.getName().equals(idNameCur.get(id))) {
                    changed++;
                }
            }
        }
        added = idNameCur.size() + deleted - previous.size();
        return new Info(added, changed, deleted);
    }

}
