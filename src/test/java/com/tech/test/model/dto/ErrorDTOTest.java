package com.tech.test.model.dto;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ErrorDTOTest {

    @Test
    public void noArgsConstructorTest() {
        ErrorDTO errorDTO = new ErrorDTO();
        assertNotNull(errorDTO);
    }

    @Test
    public void allARgsConstructorTest() {
        ErrorDTO errorDTO = new ErrorDTO(500, "internal error", "ERROR");
        assertNotNull(errorDTO);
        assertNotNull(errorDTO.getMessage());
        assertNotNull(errorDTO.getCode());
        assertNotNull(errorDTO.getLevel());
    }

    @Test
    public void getterAndSetterTest() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(400);
        errorDTO.setMessage("Bad request");
        errorDTO.setLevel("ERROR");
        assertNotNull(errorDTO);
        assertNotNull(errorDTO.getMessage());
        assertNotNull(errorDTO.getCode());
        assertNotNull(errorDTO.getLevel());
    }


    @Test
    public void builderTest() {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(500)
                .message("internal error")
                .level("ERROR")
                .build();
        assertNotNull(errorDTO);
        assertNotNull(errorDTO.getMessage());
        assertNotNull(errorDTO.getCode());
        assertNotNull(errorDTO.getLevel());
    }
}
