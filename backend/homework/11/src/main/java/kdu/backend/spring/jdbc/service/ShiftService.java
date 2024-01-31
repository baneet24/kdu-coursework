package kdu.backend.spring.jdbc.service;

import kdu.backend.spring.jdbc.dao.ShiftDao;
import kdu.backend.spring.jdbc.exception.customexceptions.CustomException;
import kdu.backend.spring.jdbc.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ShiftService {

    private ShiftDao shiftDao;

    @Autowired
    public ShiftService(ShiftDao shiftDao){
        this.shiftDao=shiftDao;
    }

    /**
     * doing transaction and saving the shift data
     * @param shift
     * @throws CustomException
     */
    @Transactional
    public void saveShift(Shift shift) throws CustomException {
        try {
            shiftDao.saveShift(shift);
        } catch (Exception e) {
            throw new CustomException("Failed to save shift.");
        }
    }

    /**
     * get shift using tenant id
     * @param tenantId
     * @return
     * @throws CustomException
     */
    public Shift getShift(UUID tenantId) throws CustomException {
        return shiftDao.getShiftById(tenantId);
    }
}


