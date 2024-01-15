package bolu.ajileye.authfinal.service.implementation;

import bolu.ajileye.authfinal.event.RegisterEvent;
import bolu.ajileye.authfinal.utils.AuthHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;

@Slf4j
public class BaseService {
    private RegisterEvent registerEvent;
    private AsyncTaskExecutor asyncTaskExecutor;
    protected void logger(String message){
        log.info(message);
    }
}
