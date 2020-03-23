package dtu.bachelor.framework.Mapper;

import dtu.bachelor.framework.Model.Sensor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorRowMapper implements RowMapper<Sensor> {

    @Override
    public Sensor mapRow(ResultSet rs, int arg1) throws SQLException {
        Sensor sensor = new Sensor();
        sensor.setId(rs.getString("id"));
        sensor.setType(rs.getString("type"));
        return sensor;
    }
}
