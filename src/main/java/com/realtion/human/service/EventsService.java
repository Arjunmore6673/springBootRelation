package com.realtion.human.service;

import com.realtion.human.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventsService {
    public static final Logger logger = LoggerFactory.getLogger("ScheduledTasks.class");

    public Response getEvents(Long communityId, String searchName){
            return new Response();
    }

    public Response getEvents2(Long communityId, String searchName){
            return new Response();
    }

}
