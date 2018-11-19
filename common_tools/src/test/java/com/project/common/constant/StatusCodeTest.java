package com.project.common.constant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Wang Zheng
 */
public class StatusCodeTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testVal() throws Exception {
        System.out.println(StatusCode.SC_200.val());
    }

    @Test
    public void testValueOf() throws Exception {
        StatusCode.valueOf(200).equals(StatusCode.SC_200);
        StatusCode.valueOf(201).equals(StatusCode.SC_201);
        StatusCode.valueOf(400).equals(StatusCode.SC_400);
        StatusCode.valueOf(401).equals(StatusCode.SC_401);
        StatusCode.valueOf(404).equals(StatusCode.SC_404);
        StatusCode.valueOf(405).equals(StatusCode.SC_405);
        StatusCode.valueOf(422).equals(StatusCode.SC_422);
        StatusCode.valueOf(500).equals(StatusCode.SC_500);
        StatusCode.valueOf(505).equals(StatusCode.SC_500);
    }
}