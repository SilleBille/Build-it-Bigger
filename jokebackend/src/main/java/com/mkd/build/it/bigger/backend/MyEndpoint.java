/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.mkd.build.it.bigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.mkd.build.it.bigger.jokes.provider.JokeFactory;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.bigger.it.build.mkd.com",
                ownerName = "backend.bigger.it.build.mkd.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that retrieves jokes from JokeFactory
     */
    @ApiMethod(name = "retrieveJoke")
    public MyBean retrieveJoke() {
        MyBean response = new MyBean();
        JokeFactory jf = new JokeFactory();
        response.setData(jf.getAJoke());

        return response;
    }

}
