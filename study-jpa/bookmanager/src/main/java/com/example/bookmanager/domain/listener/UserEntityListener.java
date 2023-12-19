package com.example.bookmanager.domain.listener;


import com.example.bookmanager.domain.User;
import com.example.bookmanager.domain.UserHistory;
import com.example.bookmanager.repository.UserHistoryRepository;
import com.example.bookmanager.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

// @Component // Bean에서 Autowired가 되어야하기 때문에..
public class UserEntityListener {

    //@Autowired
    //private UserHistoryRepository userHistoryRepository;

    @PostPersist // PrePersist일 경우, userId가 저장돼있지 않아 null값 발생
    @PostUpdate
    public void prePersistAndPreUpdate(Object o){

        // Bean을 @Component해서 주입하는 것이 아닌 BeanUtils에서 Bean을 가져온다.
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
