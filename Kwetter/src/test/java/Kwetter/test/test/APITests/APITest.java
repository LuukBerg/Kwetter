package Kwetter.test.test.APITests;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class APITest {

        @Deployment(testable = false)
        public static WebArchive createDeployment(){
       
            return null;
        }
}


