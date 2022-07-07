package com.alexlis.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

public class Config {

    @BeforeEach
    void browserSizeConfigBeforeTest() {
        Configuration.startMaximized = true;
    }
}