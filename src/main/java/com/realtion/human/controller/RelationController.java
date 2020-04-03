package com.realtion.human.controller;

import com.realtion.human.config.GlobalConstant;
import com.realtion.human.model.Response;
import com.realtion.human.model.jwt.JwtUserDetails;
import com.realtion.human.security.CurrentUser;
import com.realtion.human.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GlobalConstant.RELATION)
public class RelationController {

    @Autowired
    private EventsService eventsService;


    @GetMapping(GlobalConstant.GET_USER_WITH_RELATION)
    public Response getUserRelations(@CurrentUser JwtUserDetails jwtUserDetails) {
        return eventsService.getUserRelations(jwtUserDetails.getId());
    }


}
