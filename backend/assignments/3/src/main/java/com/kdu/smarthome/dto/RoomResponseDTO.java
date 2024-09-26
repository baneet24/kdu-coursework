package com.kdu.smarthome.dto;

import com.kdu.smarthome.model.Room;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RoomResponseDTO {

    private String message;

    private Room room;

    private HttpStatus httpStatus;

}
