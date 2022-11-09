package com.planit.pageobjects;

public interface BaseComponent {
    public boolean isLoaded(boolean suppressExceptions) throws Exception;
    public boolean isLoaded() throws Exception;
    public void waitToLoad() throws Exception;
}
