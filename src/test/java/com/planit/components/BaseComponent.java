package com.planit.components;

public interface BaseComponent {
    boolean isLoaded(boolean suppressExceptions) throws Exception;
    boolean isLoaded() throws Exception;
    void waitToLoad() throws Exception;
}
