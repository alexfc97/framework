package dtu.bachelor.framework.Dao;

import dtu.bachelor.framework.Mapper.TripRowMapper;
import dtu.bachelor.framework.Model.Trip;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TripDaoImplementation implements TripDao {


    public TripDaoImplementation(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    NamedParameterJdbcTemplate template;

    @Override
    public List<Trip> findAll() {
        String sql = "SELECT * " +
                "FROM trips";
        return template.query(sql, new TripRowMapper());
    }

    @Override
    public void insertTrip(Trip trip) {
        final String sql = "INSERT INTO trips (id, sensor, time, type, value, latitude, longitude) " +
                "values(:id,:sensor,:time,:type,:value,:latitude,:longitude)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", trip.getId())
                .addValue("sensor", trip.getSensor())
                .addValue("time", trip.getTime())
                .addValue("type", trip.getType())
                .addValue("value", trip.getValue())
                .addValue("latitude", trip.getLatitude())
                .addValue("longitude", trip.getLongitude());
        template.update(sql, param, holder);
    }

    @Override
    public void updateTrip(Trip trip) {
        final String sql = "UPDATE trips " +
                "SET sensor=:sensor, time=:time, type=:type, value=:value WHERE id=:id";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", trip.getId())
                .addValue("sensor", trip.getSensor())
                .addValue("time", trip.getTime())
                .addValue("type", trip.getType())
                .addValue("value", trip.getValue())
                .addValue("latitude", trip.getLatitude())
                .addValue("longitude", trip.getLongitude());
        template.update(sql, param, holder);
    }

    @Override
    public void deleteTrip(int id) {
        final String sql = "DELETE FROM trips " +
                "WHERE id = :id";
        MapSqlParameterSource ps = new MapSqlParameterSource("id", id);
        template.update(sql, ps);
    }

    @Override
    public List<Trip> findById(int id) {
        String sql = "SELECT * " +
                "FROM trips " +
                "WHERE id = :id";
        MapSqlParameterSource ps = new MapSqlParameterSource("type", id);
        return template.query(
                sql,
                ps,
                (resultSet, i) -> new TripRowMapper().mapRow(resultSet, i));
    }

    @Override
    public List<Trip> findByTimes(LocalDateTime time1, LocalDateTime time2) {
        String sql = "SELECT * " +
                "FROM trips " +
                "WHERE time " +
                "BETWEEN :time1 AND :time2";
        MapSqlParameterSource ps = new MapSqlParameterSource("time", time1);
        return template.query(
                sql,
                ps,
                (resultSet, i) -> new TripRowMapper().mapRow(resultSet,i));
    }


    @Override
    public List<Trip> findByValue(int value) {
        String sql = "SELECT * " +
                "FROM trips " +
                "WHERE value = :value";
        MapSqlParameterSource ps = new MapSqlParameterSource("value", value);
        return template.query(
                sql,
                ps,
                (resultSet, i) -> new TripRowMapper().mapRow(resultSet, i));
    }

    @Override
    public void deleteAllTrips(){
    final String sql = "DELETE FROM trips";
    MapSqlParameterSource ps = new MapSqlParameterSource();
        template.update(sql,ps);
    }

    @Override
    public List<Trip> findByType(String type){
        String sql = "SELECT * " +
                "FROM trips " +
                "WHERE type = :type";
        MapSqlParameterSource ps = new MapSqlParameterSource("type", type);
        return template.query(
                sql,
                ps,
                (resultSet, i) -> new TripRowMapper().mapRow(resultSet,i));
    }
}
