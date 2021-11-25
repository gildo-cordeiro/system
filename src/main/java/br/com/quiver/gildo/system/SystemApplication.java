package br.com.quiver.gildo.system;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import br.com.quiver.gildo.system.config.WebConfig;

/**
 * Classe de inicialização do servidor e criação do contexto
 * @author gildo_cordeiro
 *
 */
@SpringBootApplication
public class SystemApplication implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		 AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	     ctx.register(WebConfig.class);
	     ctx.setServletContext(servletContext);

	     ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
	     servlet.setLoadOnStartup(1);
	     servlet.addMapping("/*");
	}
}
