package com.asdc.pawpals.dto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MedicalHistoryDtoInlineTest {

    @Test
    public void testGettersAndSetters() {
        VetDto vetDto = new VetDto();
        MedicalHistoryDtoInline medicalHistoryDtoInline = new MedicalHistoryDtoInline();

        medicalHistoryDtoInline.setVet(vetDto);
        Assert.assertEquals(vetDto, medicalHistoryDtoInline.getVet());
    }


}
