package com.asdc.pawpals.utils;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.asdc.pawpals.exception.InvalidImage;
import com.fasterxml.jackson.core.type.TypeReference;


@SpringBootTest
public class CommonUtilsTest {

    @Test
    public void testIsStrictTypeOfWithValidInput() {
        // Arrange
        String input = "test";
        Class<String> targetType = String.class;

        // Act
        Boolean result = CommonUtils.isStrictTypeOf(input, targetType);

        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    public void testIsStrictTypeOfWithInvalidInput() {
        // Arrange
        String input = "test";
        Class<Integer> targetType = Integer.class;

        // Act
        Boolean result = CommonUtils.isStrictTypeOf(input, targetType);

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void testIsStrictTypeOfWithTypeReference() {
        // Arrange
        String input = "test";
        TypeReference<String> targetType = new TypeReference<String>() {};

        // Act
        Boolean result = CommonUtils.isStrictTypeOf(input, targetType);

        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    public void testGetDayFromDateWithValidInput() {
        // Arrange
        String dateRaw = "2023-04-06";

        // Act
        String result = CommonUtils.getDayFromDate(dateRaw);

        // Assert
        Assertions.assertEquals("Wednesday", result);
    }

    @Test
    public void testGetDayFromDateWithNullInput() {
        // Arrange
        String dateRaw = null;

        // Act
        String result = CommonUtils.getDayFromDate(dateRaw);

        // Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testGetNextSlotTimeWithValidInput() {
        // Arrange
        String currSlotTime = "09:00";

        // Act
        String result = CommonUtils.getNextSlotTime(currSlotTime);

        // Assert
        Assertions.assertEquals("09:30", result);
    }

    @Test
    public void testGetPreviousSlotTimeWithValidInput() {
        // Arrange
        String currSlotTime = "09:30";

        // Act
        String result = CommonUtils.getPreviousSlotTime(currSlotTime);

        // Assert
        Assertions.assertEquals("09:00", result);
    }

    @Test
    public void testGetBytesWithValidImage() throws IOException, InvalidImage {
        // Arrange
        MockMultipartFile image = new MockMultipartFile("image.jpg", "image.jpg", "image/jpeg", new byte[] {12});

        // Act
        Byte[] result = CommonUtils.getBytes(image);

        // Assert
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetBytesWithInvalidImageType() {
        // Arrange
        MockMultipartFile image = new MockMultipartFile("image.mp3", "image.mp3", "audio/mp3", new byte[] {});

        // Act and Assert
        Assertions.assertThrows(InvalidImage.class, () -> {
            CommonUtils.getBytes(image);
        });
    }

    @Test
    public void testGetBytesWithNullImage() {
        // Arrange
        MultipartFile image = null;

        // Act and Assert
        Assertions.assertThrows(InvalidImage.class, () -> {
            CommonUtils.getBytes(image);
        });
    }
}
