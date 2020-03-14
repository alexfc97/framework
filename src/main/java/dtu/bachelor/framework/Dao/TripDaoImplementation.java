package dtu.bachelor.framework.Dao;

import dtu.bachelor.framework.Mapper.TripRowMapper;
import dtu.bachelor.framework.Model.Trip;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TripDaoImplementation implements TripDao{


    public TripDaoImplementation(NamedParameterJdbcTemplate template) {
        this.template = template;
    }
    NamedParameterJdbcTemplate template;

    @Override
    public List<Trip> findAll() {
        String sql = "SELECT * FROM trips";
        return template.query(sql, new TripRowMapper());
    }

    @Override
    public void insertTrip(Trip trip) {
        final String sql = "insert into trips(id, time , type, value) values(:id,:time,:type,:value)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", trip.getId())
                .addValue("time", trip.getTime())
                .addValue("type", trip.getType())
                .addValue("value", trip.getValue());
        template.update(sql,param, holder);
    }

    @Override
    public void updateTrip(Trip trip) {
        final String sql = "update trips set time=:time, type=:type, value=:value where id=:id";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", trip.getId())
                .addValue("time", trip.getTime())
                .addValue("type", trip.getType())
                .addValue("value", trip.getValue());
        template.update(sql,param, holder);
    }

    @Override
    public void deleteTrip(Trip trip) {
        final String sql = "delete from trips where id=:id and type=:type and value=:value";
        Map<String,Object> map= new HashMap<>();
        map.put("id", trip.getId());
        map.put("type", trip.getType());
        map.put("value",trip.getValue());
        template.execute(sql,map, (PreparedStatementCallback<Object>) PreparedStatement::executeUpdate);
    }
}
