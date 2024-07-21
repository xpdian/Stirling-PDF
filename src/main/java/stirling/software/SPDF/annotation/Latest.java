package stirling.software.SPDF.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作记录
 *
 * @author 29443
 * @date 2022/4/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Latest {

    /** 操作 */
    String opName() default "";

    /** 操作对应路由 */
    String router() default "";
}
