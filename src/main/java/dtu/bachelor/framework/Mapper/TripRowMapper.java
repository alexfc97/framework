package dtu.bachelor.framework.Mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import dtu.bachelor.framework.Model.Trip;
import org.springframework.jdbc.core.RowMapper;

public class TripRowMapper implements RowMapper<Trip> {

    @Override
    public Trip mapRow(ResultSet rs, int arg1) throws SQLException {
        Trip trip = new Trip();
        trip.setId(rs.getInt("id"));
        trip.setSensor(rs.getString("sensor"));
        trip.setTime(rs.getObject("time", LocalDateTime.class));
        trip.setType(rs.getString("type"));
        trip.setValue(rs.getInt("value"));
        trip.setLatitude(rs.getDouble("latitude"));
        trip.setLongitude(rs.getDouble("longitude"));
        return trip;
    }
}