package com.asdc.pawpals.Enums;

import org.junit.Assert;
import org.junit.Test;

public class StatusTest {

    @Test
    public void testGetLabel() {
        Assert.assertEquals("CONFIRMED", AppointmentStatus.CONFIRMED.getLabel());
        Assert.assertEquals("REJECTED", AppointmentStatus.REJECTED.getLabel());
        Assert.assertEquals("PENDING", AppointmentStatus.PENDING.getLabel());
        Assert.assertEquals("COMPLETED", AppointmentStatus.COMPLETED.getLabel());
    }

    @Test
    public void testValueOf() {
        Assert.assertEquals(AppointmentStatus.CONFIRMED, AppointmentStatus.valueOf("CONFIRMED"));
        Assert.assertEquals(AppointmentStatus.REJECTED, AppointmentStatus.valueOf("REJECTED"));
        Assert.assertEquals(AppointmentStatus.PENDING, AppointmentStatus.valueOf("PENDING"));
        Assert.assertEquals(AppointmentStatus.COMPLETED, AppointmentStatus.valueOf("COMPLETED"));
    }
}
