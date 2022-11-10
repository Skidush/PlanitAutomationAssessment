package com.planit.hooks;

import org.testng.ITestResult;

import java.io.IOException;

public interface Test {
    public void initialize();
    public void tearDown(ITestResult result) throws IOException;
}
