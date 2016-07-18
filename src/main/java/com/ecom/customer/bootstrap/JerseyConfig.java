/*
 * Copyright (c) 2016
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>http://www.apache.org/licenses/LICENSE-2.0<p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ecom.customer.bootstrap;

import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import io.swagger.jaxrs.config.BeanConfig;

/**
 *
 * @author jcordones13
 *
 */
@ApplicationPath("account")
public class JerseyConfig extends ResourceConfig {

    private Logger logger = LoggerFactory.getLogger(JerseyConfig.class);

    public JerseyConfig() throws ServletException {
        logger.info("\n***************** JERSEY CONFIGURING ********************\n");
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        register(RequestContextFilter.class);
        packages("com.ecom.account");
        register(LoggingFilter.class);

        //Adding swagger providers
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

      //Swagger configuration
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0");
        beanConfig.setTitle( "Private Deal API" );
        beanConfig.setDescription("PrivateDeal RESTful API" );
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/account");
        beanConfig.setResourcePackage("com.ecom.account.resource");
        beanConfig.setScan(true);
    }
}


