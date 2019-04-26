package org.tyaa.javaee.gae.objectify.pasd2817;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.tyaa.javaee.gae.objectify.pasd2817.model.Category;

import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class ObjectifyListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ObjectifyService.init();
		
		 /* ObjectifyService.init(new ObjectifyFactory(
			    DatastoreOptions.newBuilder()
			        .setHost("http://localhost:8484")
			        .setProjectId("psd2817-objectify")
			        .build()
			        .getService()
			)); */
		 
        ObjectifyService.register(Category.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
