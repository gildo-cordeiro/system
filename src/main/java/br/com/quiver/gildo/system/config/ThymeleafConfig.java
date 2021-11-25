package br.com.quiver.gildo.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

/**
 * Classe de configuração do Thymeleaf
 * @author gildo_cordeiro
 *
 */
@Configuration
public class ThymeleafConfig {
	
    private ApplicationContext applicationContext;
    
    @Autowired
    ThymeleafProperties properties;
    
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    /*
     * Configruando o bean do ViewResolver responsável pelo template do thymeleaf
     */
	@Bean
	public ViewResolver thymeleafViewResolver(ITemplateEngine springTemplateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		
		viewResolver.setTemplateEngine((ISpringTemplateEngine) springTemplateEngine);
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setViewNames(new String[] {"*html"});
		
		return viewResolver;
	}

	@Bean
	public SpringTemplateEngine sprinhTemplateEngine(ITemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(new UrlTemplateResolver());
        templateEngine.addTemplateResolver(templateResolver());	
        templateEngine.getConfiguration();
        
        return templateEngine;
	}
	
	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
			
   		resolver.setPrefix(properties.getPrefix());
   		resolver.setApplicationContext(applicationContext);
        resolver.setSuffix(properties.getSuffix());
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);
        return resolver;
	}

}
