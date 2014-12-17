/*
 * Copyright (c) 2007-2014 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.concurrentinc.com/
 */

package bios;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

/**
 * An alternative packaging might be nice: embed tomcat and add a bash script for starting a main program.
 */
public class MainContainer
  {
  public static void main(String[] args) throws Exception
    {
    String webappDirLocation = "src/main/webapp/";
    Tomcat tomcat = new Tomcat();

    tomcat.setPort( 8080 );

    String basePath = "build/tomcat";
    File baseDir = new File(basePath);
    baseDir.mkdirs();
    tomcat.setBaseDir( basePath );

    tomcat.addWebapp( "/", new File( webappDirLocation ).getAbsolutePath() );

    tomcat.setBaseDir( "build/tomcat" );

    tomcat.start();
    tomcat.getServer().await();
    }
  }


