package kanatovm.bestclinic.api.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

/**
 * @author Kanatov Marat
 * @package_name kanatovm.bestclinic.api.soap
 * @date 29.11.2022
 */
@EnableWs
@Configuration
public class SoapConfiguration extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(messageDispatcherServlet, "/kanatovm/bestclinic/*");
    }

    @Bean(name = "doctorSoap")
    public Wsdl11Definition wsdl11Definition(){
        SimpleWsdl11Definition simpleWsdl11Definition = new SimpleWsdl11Definition();
        simpleWsdl11Definition.setWsdl(new ClassPathResource("/wsdl/doctor.wsdl"));
        return simpleWsdl11Definition;
    }
}
