package ch.qos.logback.classic.gaffer;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import groovy.lang.*;
import groovy.util.*;

public class AppenderDelegate
  extends ch.qos.logback.classic.gaffer.ComponentDelegate  implements
    groovy.lang.GroovyObject {
;
public AppenderDelegate
(ch.qos.logback.core.Appender appender) {
super (null);
}
public AppenderDelegate
(ch.qos.logback.core.Appender appender, java.util.List<ch.qos.logback.core.Appender<?>> appenders) {
super (null);
}
public  groovy.lang.MetaClass getMetaClass() { return (groovy.lang.MetaClass)null;}
public  void setMetaClass(groovy.lang.MetaClass mc) { }
public  java.lang.Object invokeMethod(java.lang.String method, java.lang.Object arguments) { return null;}
public  java.lang.Object getProperty(java.lang.String property) { return null;}
public  void setProperty(java.lang.String property, java.lang.Object value) { }
public  java.util.Map<java.lang.String, ch.qos.logback.core.Appender<?>> getAppendersByName() { return (java.util.Map<java.lang.String, ch.qos.logback.core.Appender<?>>)null;}
public  void setAppendersByName(java.util.Map<java.lang.String, ch.qos.logback.core.Appender<?>> value) { }
public  java.lang.String getLabel() { return (java.lang.String)null;}
public  void appenderRef(java.lang.String name) { }
}
