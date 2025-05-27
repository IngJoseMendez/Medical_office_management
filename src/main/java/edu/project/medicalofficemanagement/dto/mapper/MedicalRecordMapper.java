package edu.project.medicalofficemanagement.dto.mapper;

import edu.project.medicalofficemanagement.dto.MedicalRecordDTO;
import edu.project.medicalofficemanagement.model.MedicalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")  // <--- agregar componentModel = "spring"
public interface MedicalRecordMapper {
    @Mapping(source = "appointment.id", target = "appointmentId")
    @Mapping(source = "patient.id", target = "patientId")
    MedicalRecordDTO toDTO(MedicalRecord medicalRecord);

    @Mapping(source = "appointmentId", target = "appointment.id")
    @Mapping(source = "patientId", target = "patient.id")
    MedicalRecord toEntity(MedicalRecordDTO medicalRecordDTO);
}
