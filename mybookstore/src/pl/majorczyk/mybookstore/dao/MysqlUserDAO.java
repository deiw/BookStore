package pl.majorczyk.mybookstore.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.majorczyk.mybookstore.model.User;
import pl.majorczyk.mybookstore.util.ConnectionProvider;

import java.util.List;

public class MysqlUserDAO implements UserDAO {

    private static final String CREATE="INSERT INTO user(firstname,lastname,password,email,street,city) " +
            "VALUES(:firstname,:lastname,:password,:email,:street,:city);";
    private static final String READ_USER_BY_EMAIL="SELECT* FROM user WHERE email=:email;";
    private static final String READ_ALL_USERS="SELECT*FROM user;";

    private NamedParameterJdbcTemplate template;

    public MysqlUserDAO(){
        template=new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public User create(User user) {
        User resultUser=new User(user);
        KeyHolder keyHolder=new GeneratedKeyHolder();
        SqlParameterSource param=new BeanPropertySqlParameterSource(user);
        int result=template.update(CREATE,param,keyHolder);
        if(result>0){
            resultUser.setId((long)keyHolder.getKey());
            setPrivileges(resultUser);
        }
        return resultUser;
    }
    private void setPrivileges(User user){
        final String roleQuery="INSERT INTO user_role(email) VALUES(:email);";
        SqlParameterSource param=new BeanPropertySqlParameterSource(user);
        template.update(roleQuery,param);
    }

    @Override
    public List<User> readAllUsers() {
        List<User> users=template.query(READ_ALL_USERS,BeanPropertyRowMapper.newInstance(User.class));
        return users;
    }

    @Override
    public User readUserByEmail(String email) {
        User resultUser=null;
        SqlParameterSource param=new MapSqlParameterSource("email",email);
        resultUser=template.queryForObject(READ_USER_BY_EMAIL,param,BeanPropertyRowMapper.newInstance(User.class));
        return resultUser;
    }
}
