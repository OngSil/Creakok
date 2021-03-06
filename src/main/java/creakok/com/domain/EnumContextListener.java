package creakok.com.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.extern.log4j.Log4j;

@Log4j
public class EnumContextListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent sce) {
    }

    public void contextInitialized(ServletContextEvent sce) {
        String param = sce.getServletContext().getInitParameter("enumServletContextConfig");
        String[] params = param.split(",");

        for(String className : params) {
            className = className.trim();
            this.loadEnum(className, sce.getServletContext());
        }
    }

    /**
     * Enum 인스턴스를 찾아서 Map<name, Enum<?>> 맵으로 변환하고
     * ServletContext에 넣습니다.
     *
     * @param className
     */
    @SuppressWarnings("unchecked")
    private void loadEnum(String className, ServletContext context) {
        Map<String, Integer> ret = null;

        try {
            Class<Enum> clazz = (Class<Enum>)Class.forName(className);
            Enum[] enums = clazz.getEnumConstants();

            ret = new HashMap<String, Integer>(enums.length);
            for(Enum e : enums) {
                ret.put(e.name(), e.ordinal() );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ret = Collections.unmodifiableMap(ret);
        context.setAttribute(this.getAlias(className), ret);
    }

    /**
     * 클래스 첫 문자를 소문자로 바꿔서 alias를 만듭니다.
     *
     * @param className
     * @return
     */
    private String getAlias(String className) {
        String[] token = className.split("\\.");
        String alias = token[token.length - 1].trim();
        String initial = alias.substring(0, 1);
        String suffix = alias.substring(1);

        initial = initial.toLowerCase();
        alias = initial + suffix;

        return alias;
    }
}
