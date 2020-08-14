package ch.qos.logback.classic.gaffer;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import groovy.lang.*;
import groovy.util.*;

public class ConfigurationDelegate
  extends ch.qos.logback.core.spi.ContextAwareBase  implements
    groovy.lang.GroovyObject {
;
public  void scan() { }
public  void root(ch.qos.logback.classic.Level level) { }
public  void logger(java.lang.String name, ch.qos.logback.classic.Level level, java.util.List<java.lang.String> appenderNames) { }
public  void logger(java.lang.String name, ch.qos.logback.classic.Level level) { }
public  void appender(java.lang.String name, java.lang.Class clazz) { }
public  void receiver(java.lang.String name, java.lang.Class aClass) { }
public  void turboFilter(java.lang.Class clazz) { }
public  java.lang.String timestamp(java.lang.String datePattern) { return (java.lang.String)null;}
public  void jmxConfigurator() { }
public  groovy.lang.MetaClass getMetaClass() { return (groovy.lang.MetaClass)null;}
public  void setMetaClass(groovy.lang.MetaClass mc) { }
public  java.lang.Object invokeMethod(java.lang.String method, java.lang.Object arguments) { return null;}
public  java.lang.Object getProperty(java.lang.String property) { return null;}
public  void setProperty(java.lang.String property, java.lang.Object value) { }
public  java.util.List<ch.qos.logback.core.Appender> getAppenderList() { return (java.util.List<ch.qos.logback.core.Appender>)null;}
public  void setAppenderList(java.util.List<ch.qos.logback.core.Appender> value) { }
public  java.lang.Object getDeclaredOrigin() { return null;}
public  void scan(java.lang.String scanPeriodStr) { }
public  void statusListener(java.lang.Class listenerClass) { }
public  void conversionRule(java.lang.String conversionWord, java.lang.Class converterClass) { }
public  void root(ch.qos.logback.classic.Level level, java.util.List<java.lang.String> appenderNames) { }
public  void logger(java.lang.String name, ch.qos.logback.classic.Level level, java.util.List<java.lang.String> appenderNames, java.lang.Boolean additivity) { }
public  void appender(java.lang.String name, java.lang.Class clazz, groovy.lang.Closure closure) { }
public  void receiver(java.lang.String name, java.lang.Class aClass, groovy.lang.Closure closure) { }
public  void turboFilter(java.lang.Class clazz, groovy.lang.Closure closure) { }
public  java.lang.String timestamp(java.lang.String datePattern, long timeReference) { return (java.lang.String)null;}
public  void jmxConfigurator(java.lang.String name) { }
}
