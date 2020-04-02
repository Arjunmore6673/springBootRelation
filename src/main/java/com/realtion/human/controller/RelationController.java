package com.realtion.human.controller;

import com.realtion.human.config.GlobalConstant;
import com.realtion.human.model.Response;
import com.realtion.human.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GlobalConstant.RELATION)
public class RelationController {

    @Autowired
    EventsService eventsService;

    @GetMapping(GlobalConstant.EVENT_LISTS)
    public void listEvents(@RequestParam(name = "communityId", required = true) Long communityId, @RequestParam(name = "searchName", required = false) String searchName) {
        eventsService.getEvents(communityId, searchName);
    }

    @GetMapping("dss")
    public Response listEvents2(@RequestParam(name = "communityId", required = true) Long communityId, @RequestParam(name = "searchName", required = false) String searchName) {
        return eventsService.getEvents2(communityId, searchName);
    }


}
