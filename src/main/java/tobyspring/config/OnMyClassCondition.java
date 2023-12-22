package tobyspring.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.util.Map;

public class OnMyClassCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attr = metadata.getAnnotationAttributes(ConditionalOnMyClass.class.getName());
        return ClassUtils.isPresent((String) attr.get("value"), context.getClassLoader());
    }
}
