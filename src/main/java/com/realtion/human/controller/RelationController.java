package com.realtion.human.controller;

import com.realtion.human.config.GlobalConstant;
import com.realtion.human.entity.Feedback;
import com.realtion.human.model.*;
import com.realtion.human.model.jwt.JwtUserDetails;
import com.realtion.human.security.CurrentUser;
import com.realtion.human.service.RelationService;

import javax.management.relation.RelationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GlobalConstant.RELATION)
public class RelationController {

    @Autowired
    private RelationService relationService;


    @PostMapping(GlobalConstant.ADD_FEEDBACK)
    public Response addFeedback(@CurrentUser JwtUserDetails jwtUserDetails, @RequestBody Feedback feedback) {
        return relationService.addFeedback(jwtUserDetails.getId(), feedback);
    }

    @GetMapping(GlobalConstant.GET_USER_WITH_RELATION)
    public Response getUserRelations(@CurrentUser JwtUserDetails jwtUserDetails) {
        Response response = new Response();
        try{
            return relationService.getUserRelations(jwtUserDetails.getId());
        } catch (Exception e){
            response.errorResponse("Something went wrong " + e);
            return response;
        }
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
    public Response addUserAndRelation(@CurrentUser JwtUserDetails jwtUserDetails, @RequestBody RelationUserModel model) throws RelationException {
        return relationService.addUserAndRelation(jwtUserDetails.getId(), model);
    }
    @PostMapping(GlobalConstant.ADD_OTHER_USER_RELATION)
    public Response addOtherRelation(@RequestParam("userId") Long userId, @RequestBody RelationUserModel model) throws RelationException {
        return relationService.addUserAndRelation(userId, model);
    }

    @PutMapping(GlobalConstant.UPDATE_USER_IMAGE)
    public Response updateUserWithImage(@RequestBody ImageModel model) {
        return relationService.updateUserWithImage(model);
    }

    @PutMapping(GlobalConstant.UPDATE_USER)
    public Response updateUser(@RequestBody UsersModel model) {
        return relationService.updateUser(model);
    }


}
