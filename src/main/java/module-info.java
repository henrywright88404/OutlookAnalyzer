module org.example {
    requires javafx.controls;
    requires javafx.web;
    requires maven.model;
    requires plexus.utils;
    requires msal4j;
    requires logback.core;
    requires logback.classic;
    requires org.slf4j;
    requires org.json;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires microsoft.graph;

    exports outlookanalyzer;
}