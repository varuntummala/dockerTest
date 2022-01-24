package com.springBoot.userDetails.repository;

import com.springBoot.userDetails.exception.BadUserDetailsException;
import com.springBoot.userDetails.model.User;
import com.springBoot.userDetails.rowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class User_Repository {
//    @Autowired
//    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> retrieveAllUsers(){
        String sql = "select * from user_table";
//        ArrayList<User> userList = new ArrayList<User>();
        List<User> users = jdbcTemplate.query(sql,new UserRowMapper());
//        userList.add(users);
        return users;
    }

    public List<User> getOnlineUsers(String OnlineStatus){
        String sql = " select * from user_table where ONLINESTATUS1 = ?";
        List<User> users = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,OnlineStatus);
            }
        },new UserRowMapper());
        return users;
    }

    public void addUser(User user) throws BadUserDetailsException{
        String sql = "insert into user_table (USERID,USERNAME,ONLINESTATUS1,PASSWORD,BIRTHDATE,CREATION_DATE) values(?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, user.getUserId());
                    ps.setString(2, user.getUserName());
                    ps.setString(3, user.getOnlineStatus());
                    ps.setString(4, user.getPassword());
                    ps.setDate(5, user.getBirthDate());
                    ps.setTimestamp(6, user.getCreationDate());
                }
            });
        }
        catch(Exception e) {
            throw new BadUserDetailsException("Please check input details");
        }
    }

    public void updateUser(User user){
        String sql = "update user_table set USERNAME = ?,ONLINESTATUS1 = ?,PASSWORD = ?,BIRTHDATE = ?,CREATION_DATE = ?  where USERID = ?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

                ps.setString(1,user.getUserName());
                ps.setString(2,user.getOnlineStatus());
                ps.setString(3,user.getPassword());
                ps.setDate(4,user.getBirthDate());
                ps.setTimestamp(5,user.getCreationDate());
                ps.setString(6,user.getUserId());
            }
        });
    }

    public void deleteUser(User user){
        String sql = "delete from user_table where USERID = ?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

                ps.setString(1,user.getUserId());
            }
        });
    }

}
