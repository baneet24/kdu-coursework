package kdu.backend.spring.jdbc.dao;

import kdu.backend.spring.jdbc.exception.customexceptions.CustomException;
import kdu.backend.spring.jdbc.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public class ShiftDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * method to save data with the query
     * @param shift
     */
    public void saveShift(Shift shift) {
        String sql = "INSERT INTO shifts (id, shift_type_id, name, date_start, date_end, " +
                "time_start, time_end, created_at, updated_at, time_zone, tenant_id,created_by,updated_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

        jdbcTemplate.update(sql,
                UUID.randomUUID(),
                shift.getShiftTypeId(),
                shift.getName(),
                shift.getDateStart(),
                shift.getDateEnd(),
                shift.getTimeStart(),
                shift.getTimeEnd(),
                shift.getCreatedAt(),
                shift.getUpdatedAt(),
                shift.getTimeZone(),
                shift.getTenantId(),
                shift.getCreatedBy(),
                shift.getUpdatedBy()
        );
    }

    /**
     * using jdbc template and applying query to get shift using id
     * @param shiftId
     * @return
     * @throws CustomException
     */
    public Shift getShiftById(UUID shiftId) throws CustomException {
        String sql = "SELECT * FROM shifts WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{shiftId}, new BeanPropertyRowMapper<>(Shift.class));
        } catch (Exception e) {
            throw new CustomException("Exception Occurs");
        }
    }
}