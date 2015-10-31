package com.safetyzone.safetyzone;

import com.safetyzone.safetyzone.SafeService;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mitchellbusby on 31/10/2015.
 */
public class SafeServiceTest extends TestCase {
    public SafeServiceTest() {
        super(SafeService.class);
    }
    @Test
    public void testGetData() throws Exception {
        SafeService service = new SafeService();
        String string = service.getData(151.202128, -33.859983);
        assertNotNull(string);
    }
}