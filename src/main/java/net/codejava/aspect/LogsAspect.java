package net.codejava.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogsAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* net.codejava.controller.ResultController.saveResult(..))")
    public void saveResultPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @AfterReturning(pointcut = "saveResultPointcut()", returning = "result")
    public void logAfterSavingResult(JoinPoint joinPoint, Object result) {
        log.info("Result saved successfully: {}", result);
    }
}