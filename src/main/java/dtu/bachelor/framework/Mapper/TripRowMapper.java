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
        trip.setId(rs.getInt("employeeId"));
        trip.setTime(rs.getObject(2, LocalDateTime.class));
        trip.setType(rs.getString("employeeEmail"));
        trip.setValue(rs.getInt("employeeEmail"));
        return trip;
    }
}