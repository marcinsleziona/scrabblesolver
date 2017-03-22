package pl.ms.scrabblesolver.infrastructure;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.*;

/*
 * Created by Marcin on 2017-03-22.
 */
@Conditional(ConditionalOnDictionary.ConditionImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ConditionalOnDictionary {
    class ConditionImpl implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return Boolean.TRUE.equals(context.getEnvironment().getProperty("dictionary.complete.enabled", Boolean.class));
        }
    }
}
