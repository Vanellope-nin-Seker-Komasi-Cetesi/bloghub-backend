package com.patika.bloghubservice.repository;

import com.patika.bloghubservice.model.UserReadHistory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserReadHistoryRepository {

    List<UserReadHistory> userReadHistories=new ArrayList<>();
    public List<UserReadHistory> getReadHistory() {
        return userReadHistories.stream().toList();
    }

    public void save(UserReadHistory readHistory) {

        userReadHistories.add(readHistory);
    }
}
