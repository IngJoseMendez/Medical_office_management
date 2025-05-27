package edu.project.medicalofficemanagement.dto.mapper;

import edu.project.medicalofficemanagement.dto.ConsultRoomDTO;
import edu.project.medicalofficemanagement.model.ConsultRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")  // <--- agregar componentModel = "spring"
public interface ConsultRoomMapper {
    ConsultRoomDTO toDto(ConsultRoom consultRoom);
    ConsultRoom toEntity(ConsultRoomDTO consultRoomDTO);
}
