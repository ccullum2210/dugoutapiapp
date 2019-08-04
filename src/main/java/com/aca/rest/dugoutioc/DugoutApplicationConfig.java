package com.aca.rest.dugoutioc;

import org.glassfish.jersey.server.ResourceConfig;

public class DugoutApplicationConfig extends ResourceConfig {
	
		public DugoutApplicationConfig() {
			System.out.println("....Calling consturtor DugoutApplicationConfig");
			this.register(new DugoutDependencyBinder());
			this.packages(true, "com.aca.rest");
		}

}
