package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.UserDao;
import com.team.app.backend.persistance.dao.mappers.UserRowMapper;
import com.team.app.backend.persistance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRowMapper userRowMapper;

    @Autowired
    private Environment env;

    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<User> searchByString(int roleId, String searchstring) {
        String search="%"+searchstring+"%";
		if (roleId > 1) {
			String sql = env.getProperty("user.search-1");
			return jdbcTemplate.query(sql,new Object[]{search,search,search,roleId}, userRowMapper);
		} else {
			String sql = env.getProperty("user.search-2");
			return jdbcTemplate.query(sql,new Object[]{search,search,search}, userRowMapper);
		}
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(
                env.getProperty("user.save"),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getImage(),
                user.getRegistr_date(),
                user.getActivate_link(),
                user.getStatus().getId(),
                user.getRole().getId()
        );
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                env.getProperty("user.update"),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getImage(),
                user.getRegistr_date(),
                user.getActivate_link(),
                user.getStatus().getId(),
                user.getRole().getId(),
                user.getId()
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                env.getProperty("user.del"),
                id
        );
    }


    @Override
    public User get(Long id) {
        return jdbcTemplate.queryForObject(
                env.getProperty("user.get"),
                new Object[]{id},
                userRowMapper);
    }

    @Override
    public User findByUsername(String username) {
        String sql = env.getProperty("user.find");
        List<User> userslist=jdbcTemplate.query(sql,
                new Object[]{username},
                userRowMapper);
        if(userslist.size()==0){
            return null;
        }else{
            return userslist.get(0);
        }

    }

    @Override
    public String getUserPasswordByUsername(String username) {
        return jdbcTemplate.queryForObject(
                env.getProperty("user.get_password"),
                new Object[]{username},String.class
        );
    }

    @Override
    public User getUserByToken(String token) {
        return jdbcTemplate.queryForObject(
                env.getProperty("user.get_by_token"),
                new Object[]{token},
                userRowMapper);    }



    @Override
    public void activateByToken(String token) {
        jdbcTemplate.update(
                env.getProperty("user.activate"),
                token
        );
    }

    @Override
    public boolean checkTokenAvailability(String token) {
        return jdbcTemplate.queryForObject(
                env.getProperty("user.check_token"),
                new Object[]{token},Boolean.class
        );
    }

    @Override
    public void setStatus(Long statusId, Long userId) {
        jdbcTemplate.update(env.getProperty("set.user.status"),
                statusId, userId
        );
    }

}
