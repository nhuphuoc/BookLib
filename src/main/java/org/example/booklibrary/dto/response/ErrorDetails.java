package org.example.booklibrary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private String requestId;
    private String errorCode;
    private String errorMessage;
}
