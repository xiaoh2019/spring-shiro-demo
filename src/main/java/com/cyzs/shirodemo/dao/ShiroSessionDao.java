package com.cyzs.shirodemo.dao;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;


/**
 * @Author xiaoh
 * @create 2019-10-08 13:23
 */
@Component
public class ShiroSessionDao extends EnterpriseCacheSessionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Serializable create(Session session) {
        Serializable id = generateSessionId(session);
        assignSessionId(session,id);
        String sql = "insert into sessions(id,session) value(?,?)";
        jdbcTemplate.update(sql,id,SerializableUtils.serialize(session));
        return session.getId();
    }

    @Override
    public Session readSession(Serializable serializable) throws UnknownSessionException {
        String sql = "select session from sessions where id=?";
        String s = jdbcTemplate.queryForObject(sql, String.class);
        return SerializableUtils.deSerialize(s);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        String sql="update sessions set session=? where id=?";
        jdbcTemplate.update(sql,SerializableUtils.serialize(session),session.getId());

    }

    @Override
    public void delete(Session session) {
        String sql="delete from sessions where id=?";
        jdbcTemplate.update(sql,session.getId());
    }


}
