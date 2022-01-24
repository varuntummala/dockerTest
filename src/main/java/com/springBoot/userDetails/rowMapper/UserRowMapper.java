package com.springBoot.userDetails.rowMapper;

import com.springBoot.userDetails.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            User user = new User();

            user.setUserId(rs.getString("USERID"));
            user.setUserName(rs.getString("USERNAME"));
            user.setOnlineStatus(rs.getString("ONLINESTATUS1"));
            user.setCreationDate(rs.getTimestamp("CREATION_DATE"));
            user.setBirthDate(rs.getDate("BIRTHDATE"));
            user.setPassword(rs.getString("PASSWORD"));


        return user;
    }
}
