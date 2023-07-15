package com.husain.vaccination.Dto.RequestDto;

import lombok.Data;

@Data
public class AssociateDoctorDto {
    private int doctorId;
    private int vaccinationCenterId;
}
