package com.realtion.human.controller;

import com.realtion.human.config.GlobalConstant;
import com.realtion.human.model.RelationModel;
import com.realtion.human.model.RelationUserModel;
import com.realtion.human.model.Response;
import com.realtion.human.model.jwt.JwtUserDetails;
import com.realtion.human.security.CurrentUser;
import com.realtion.human.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GlobalConstant.RELATION)
public class RelationController {

    @Autowired
    private RelationService relationService;

    @GetMapping(GlobalConstant.GET_USER_WITH_RELATION)
    public Response getUserRelations(@CurrentUser JwtUserDetails jwtUserDetails) {
        return relationService.getUserRelations(jwtUserDetails.getId());
    }

    @PostMapping(GlobalConstant.ADD_USER_RELATION)
    public Response addRelation(@CurrentUser JwtUserDetails jwtUserDetails, @RequestBody RelationModel relationModel) {
        return relationService.addRelation(jwtUserDetails.getId(), relationModel);
    }


    @GetMapping(GlobalConstant.GET_NESTED)
    public Response getOthersRelations(@CurrentUser JwtUserDetails jwtUserDetails, @RequestParam(value = "userId", required = false) Long user2) {
        return relationService.getOthersRelations(jwtUserDetails.getId(), user2);
    }

    @PostMapping(GlobalConstant.ADD_USER_RELATION_WITH_DATA)
    public Response addUserAndRelation(@CurrentUser JwtUserDetails jwtUserDetails, @RequestBody RelationUserModel model) {
        return relationService.addUserAndRelation(jwtUserDetails.getId(), model);
    }


}
