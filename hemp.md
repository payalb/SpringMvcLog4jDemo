<!-- Log4j -->
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>${log4j.version}</version>
		</dependency>
		
		<properties>
		<spring.version>4.1.6.RELEASE</spring.version>
		<log4j.version>1.2.17</log4j.version>
	</properties>
	
	
	log4j.properties
Create a log4j.properties file, and put it in the resources. folder, refer to the above project directory structure.

log4j.properties
# Root logger option
log4j.rootLogger=DEBUG, stdout, file

# Redirect log messages to console
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n

# Redirect log messages to a log file
log4j.appender.file=org.apache.log4j.RollingFileAppender
#outputs to Tomcat home
log4j.appender.file.File=${catalina.home}/logs/myapp.log
log4j.appender.file.MaxFileSize=5MB
log4j.appender.file.MaxBackupIndex=10
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n


package com.mkyong.common.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	private static final Logger logger = Logger.getLogger(WelcomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getWelcome() {

		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("getWelcome is executed!");
		}
		
		//logs exception
		logger.error("This is Error message", new Exception("Testing"));
		
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("msg", "Hello Spring MVC + Log4j");
		return model;

	}

}



<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd" >
<log4j:configuration debug="false">
 
    <!--Console appender-->
    <appender name="stdout" class="org.apache.log4j.ConsoleAppender">
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern"
              value="%d{yyyy-MM-dd HH:mm:ss} %p %m%n" />
        </layout>
    </appender>
 
    <root>
        <level value="INFO" />
        <appender-ref ref="stdout" />
    </root>
 
</log4j:configuration>



Jakarta Commons Logging (JCL) API
Alternatively you can use Jakarta Commons Logging (JCL) API to generate a log in your Spring application. JCL can be downloaded from the https://jakarta.apache.org/commons/logging/. The only file we technically need out of this package is the commons-logging-x.y.z.jar file, which needs to be placed in your classpath in a similar way as you had put log4j-x.y.z.jar in the above example.



fatal(Object message)
error(Object message)
warn(Object message)
info(Object message)
debug(Object message)
trace(Object message)
Following is the replacement of MainApp.java, which makes use of JCL API

package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.commons.logging. Log;
import org.apache.commons.logging. LogFactory;

public class MainApp {
   static Log log = LogFactory.getLog(MainApp.class.getName());

   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext(Beans.xml");
      log.info("Going to create HelloWord Obj");
      HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
      obj.getMessage();

      log.info("Exiting the program");
   }
}
You have to make sure that you have included commons-logging-x.y.z.jar file in your project, before compiling and running the program.


By default, Spring (spring-core) is using the JCL (commons-logging) for logging, and the JCL has a runtime discovery algorithm to find out for other logging frameworks in well known places on the project classpath.


The guard statement (checking isDebugEnabled()) is there to prevent potentially expensive computation of the log message when it involves invocation of the toString() methods of various objects and concatenating the results



What to print	Conversion character	Performance
Category Name (or Logger Name)	c	Fast
Fully Qualified Class Name	C	Slow
Date and Time	d
d{format}	Slow if using JDK’s Formatters.
Fast if using Log4j Formatters.
Filename of Java class	F	Extremely slow
Location (Class, Method, and line number)	l	Extremely slow
Line Number only	L	Extremely slow
Log Message	m	Fast
Method Name	M	Extremely slow
Priority (level)	p	Fast
New Line Separator	n	Fast
Thread Name	t	Fast
Time Elapsed (in milliseconds)	r	Fast
Thread’s Nested Diagnostic Context	x	Fast
Thread’s Mapped Diagnostic Context	X	Fast
Percent Sign	%%	Fast