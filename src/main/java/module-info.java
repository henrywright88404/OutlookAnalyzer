/**
 *
 */
open module outlookanalyzer{
//    requires javafx.controls;
//    requires javafx.web;
//    requires javafx.base;
//    requires javafx.graphics;
//    requires javafx.media;
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
    requires javafx.graphics;
    requires javafx.controls;
    requires gson;

    exports outlookanalyzer;
    exports outlookanalyzer.models;
}