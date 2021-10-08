package se.yg.test.dummyservice.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Aspect
@Log4j2
public class RequestLoggingAspect {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Pointcut("within(se.yg.test.dummyservice.controller..*)")
    public void onRequest() {

    }
    
    @Around("se.yg.test.dummyservice.aop.RequestLoggingAspect.onRequest()")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;



        try {
            return pjp.proceed(pjp.getArgs());
        } finally {

            printHttpServletRequest(request, cachingRequest);
        }
    }

    private void printHttpServletRequest(HttpServletRequest req, ContentCachingRequestWrapper cachingRequest){
        log.info("Request: {} {} <<<IN<<< {}", req.getMethod(), req.getRequestURI(), req.getRemoteHost());
        Enumeration<String> headerNames = req.getHeaderNames();

        StringBuilder headerString = new StringBuilder();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerString.append(headerName).append(": ").append(req.getHeader(headerName)).append("\n\r");
        }
        log.info("header--- : \n" + headerString.toString());
        Map<String, String[]> paramMap = req.getParameterMap();
        if (!paramMap.isEmpty()){
            log.info("request Param " +  paramMap.toString());
        }

        if (req.getMethod().equals("POST") || req.getMethod().equals("PUT")){
            try {
                //String str = cachingRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                log.info("body----: \n" + objectMapper.readTree(cachingRequest.getContentAsByteArray()).toPrettyString());
            } catch (IOException e) {
                log.info("Exception ...... on Body Decode ------------ " + e);
            }
        }



    }


}
