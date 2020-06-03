package com.team.app.backend.service.impl;

import com.team.app.backend.persistance.dao.UserInviteDao;
import com.team.app.backend.persistance.model.UserInvite;
import com.team.app.backend.service.UserInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserInviteServiceImpl implements UserInviteService {
    @Autowired
    private UserInviteDao userInviteDao;

    @Override
    public void sendUserInvite(UserInvite userInvite) {
        userInvite.setActivated(false);
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        userInvite.setInviteDate(date);
        userInviteDao.send(userInvite);
    }

    @Override
    public void acceptUserInvite(Long id) {
        userInviteDao.accept(id);
    }

    @Override
    public List<UserInvite> getUserInvite(Long userId) {
        return userInviteDao.getUserInvite(userId);
    }


    @Override
    public void declineUserInvite(Long id) {
        userInviteDao.decline(id);
    }

    @Override
    public List<UserInvite> getFriendsList(Long userId) {
        return userInviteDao.getFriendsList(userId);
    }

    @Override
    public void deleteUserFromList(Long userId, Long deleteId) {
        userInviteDao.deleteFriendFromList(userId, deleteId);
    }
}
