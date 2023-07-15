package com.husain.vaccination.Dto.RequestDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UpdateEmailDto {
    int userId;
    String newEmail;
}
