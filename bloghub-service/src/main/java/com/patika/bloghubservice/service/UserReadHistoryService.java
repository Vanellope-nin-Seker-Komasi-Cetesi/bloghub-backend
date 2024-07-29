package com.patika.bloghubservice.service;

import com.patika.bloghubservice.model.Blog;
import com.patika.bloghubservice.model.UserReadHistory;
import com.patika.bloghubservice.repository.UserReadHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserReadHistoryService {

    private final UserReadHistoryRepository userReadHistoryRepository;

    public UserReadHistoryService(UserReadHistoryRepository userReadHistoryRepository) {
        this.userReadHistoryRepository = userReadHistoryRepository;
    }

    public List<UserReadHistory> getReadHistory() {

        return userReadHistoryRepository.getReadHistory();
    }

    public void addReadHistory(Blog blog) {
        UserReadHistory readHistory = new UserReadHistory();
        readHistory.setTitle(blog.getTitle());
        readHistory.setText(blog.getText());
        readHistory.setImage(blog.getImage());
        readHistory.setAuthor(blog.getUser().getEmail());
        readHistory.setReadTime(LocalDateTime.now());
        userReadHistoryRepository.save(readHistory);
    }
}
