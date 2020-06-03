package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.UserInviteDao;
import com.team.app.backend.persistance.dao.mappers.UserInviteRowMapper;
import com.team.app.backend.persistance.model.UserInvite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class UserInviteDaoImpl implements UserInviteDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Environment env;

    private UserInviteRowMapper userInviteRowMapper = new UserInviteRowMapper();

    public UserInviteDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void send(UserInvite userInvite) {
        jdbcTemplate.update(
                env.getProperty("invite.send"),
                userInvite.isActivated(),
                userInvite.getInviteText(),
                userInvite.getInviteDate(),
                userInvite.getUserIdFrom(),
                userInvite.getUserIdTo());
    }

    @Override
    public List<UserInvite> getUserInvite(Long userId) {
        return jdbcTemplate.query(env.getProperty("invite.get")
                , new Object[] { userId },
                (resultSet, i) -> {
                    UserInvite userInvite = new UserInvite();
                    userInvite.setId(resultSet.getLong("id"));
                    userInvite.setInviteText(resultSet.getString("invite_text"));
                    userInvite.setUsernameFrom(resultSet.getString("username"));
                return userInvite;
        });
    }

    @Override
    public List<UserInvite> getFriendsList(Long userId) {
        return jdbcTemplate.query(env.getProperty("invite.get_friends")
                , new Object[] { userId, userId },
                (resultSet, i) -> {
                    UserInvite userInvite = new UserInvite();
                    userInvite.setUserIdFrom(resultSet.getLong("id"));
                    userInvite.setUsernameFrom(resultSet.getString("username"));
                    return userInvite;
                });
    }

    @Override
    public void accept(Long id) {
        jdbcTemplate.update(
                env.getProperty("invite.accept"),
                true,
                id);
    }

    @Override
    public void decline(Long id) {
        jdbcTemplate.update(
                env.getProperty("invite.decline"),
                id
        );
    }

    @Override
    public void deleteFriendFromList(Long userId, Long userIdDelete) {
        jdbcTemplate.update(
                env.getProperty("invite.delete_friend"),
                userId, userIdDelete, userId, userIdDelete
        );
    }
}
