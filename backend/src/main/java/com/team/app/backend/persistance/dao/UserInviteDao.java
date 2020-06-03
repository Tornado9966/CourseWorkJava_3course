package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.UserInvite;

import java.util.List;

public interface UserInviteDao {
    void send(UserInvite userInvite);
    List<UserInvite> getUserInvite(Long userId);
    List<UserInvite> getFriendsList(Long userId);
    void accept(Long id);
    void decline(Long id);
    void deleteFriendFromList(Long userIdDelete, Long userId);
}
