package com.asdc.pawpals.Enums;

import org.junit.Assert;
import org.junit.Test;

public class StatusTest {

    @Test
    public void testGetLabel() {
        Assert.assertEquals("CONFIRMED", Status.CONFIRMED.getLabel());
        Assert.assertEquals("REJECTED", Status.REJECTED.getLabel());
        Assert.assertEquals("PENDING", Status.PENDING.getLabel());
        Assert.assertEquals("COMPLETED", Status.COMPLETED.getLabel());
    }

    @Test
    public void testValueOf() {
        Assert.assertEquals(Status.CONFIRMED, Status.valueOf("CONFIRMED"));
        Assert.assertEquals(Status.REJECTED, Status.valueOf("REJECTED"));
        Assert.assertEquals(Status.PENDING, Status.valueOf("PENDING"));
        Assert.assertEquals(Status.COMPLETED, Status.valueOf("COMPLETED"));
    }
}
