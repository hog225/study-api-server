package se.yg.test.dummyservice.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Log4j2
@Component
@Aspect
public class ExecutionAspect {

    @Around("@annotation(ExecutionTime)")
    public Object ExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch  stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = joinPoint.proceed();

        stopWatch.stop();

        log.info("Total Running time of " + joinPoint.getSignature().toShortString() + ":  "
                + stopWatch.getTotalTimeSeconds() + " sec");
        log.info(stopWatch.prettyPrint());
        return proceed;
    }

}
