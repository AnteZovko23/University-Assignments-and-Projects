package ch.qos.logback.classic.gaffer;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import groovy.lang.*;
import groovy.util.*;

public class ComponentDelegate
  extends ch.qos.logback.core.spi.ContextAwareBase  implements
    groovy.lang.GroovyObject {
;
public ComponentDelegate
(java.lang.Object component) {}
public  groovy.lang.MetaClass getMetaClass() { return (groovy.lang.MetaClass)null;}
public  void setMetaClass(groovy.lang.MetaClass mc) { }
public  java.lang.Object invokeMethod(java.lang.String method, java.lang.Object arguments) { return null;}
public  java.lang.Object getProperty(java.lang.String property) { return null;}
public  void setProperty(java.lang.String property, java.lang.Object value) { }
public final  java.lang.Object getComponent() { return null;}
public final  java.util.List getFieldsToCascade() { return (java.util.List)null;}
public  java.lang.String getLabel() { return (java.lang.String)null;}
public  java.lang.String getLabelFistLetterInUpperCase() { return (java.lang.String)null;}
public  void methodMissing(java.lang.String name, java.lang.Object args) { }
public  void cascadeFields(ch.qos.logback.classic.gaffer.ComponentDelegate subDelegate) { }
public  void injectParent(java.lang.Object subComponent) { }
public  void propertyMissing(java.lang.String name, java.lang.Object value) { }
public  java.lang.Object analyzeArgs(java.lang.Object... args) { return null;}
public  java.lang.Class parseClassArgument(java.lang.Object arg) { return (java.lang.Class)null;}
public  java.lang.String parseNameArgument(java.lang.Object arg) { return (java.lang.String)null;}
public  java.lang.String getComponentName() { return (java.lang.String)null;}
}
