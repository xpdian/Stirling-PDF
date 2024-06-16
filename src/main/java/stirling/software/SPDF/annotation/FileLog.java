package stirling.software.SPDF.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 文件操作记录
 *
 * @author 29443
 * @date 2022/4/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface FileLog {

    /** 操作内容 */
    String opContent() default "";
}
