//package com.realtion.human.controller;
//
//import com.wetech.common.model.*;
//import com.wetech.common.model.mapped_tables.CommunityWise;
//import com.wetech.community.config.GlobalConstant;
//import com.wetech.community.dto.MemberListDto;
//import com.wetech.community.dto.MembersWithRequested;
//import com.wetech.community.dto.PaymentSetupDto;
//import com.wetech.community.dto.UserDRegistrationDto;
//import com.wetech.community.forms.ResponseMessage;
//import com.wetech.community.helper.JsonConverter;
//import com.wetech.community.helper.Response;
//import com.wetech.community.security.CurrentUser;
//import com.wetech.community.service.user.UserService;
//import com.wordnik.swagger.annotations.Api;
//import com.wordnik.swagger.annotations.ApiResponse;
//import com.wordnik.swagger.annotations.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by isuraksha3 on 1/25/2019.
// */
//
//@Api(value = "User Controller", description = "allow to list user details ")
//@RestController
//@RequestMapping(GlobalConstant.USERS)
//public class UserController {
//
//	@Autowired
//    MessageSource messageSource;
//	@Autowired
//	UserService userService;
//
//	@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorised request"),
//			@ApiResponse(code = 200, message = "The request is complete, and the currently logged user is displayed  "),
//			@ApiResponse(code = 404, message = "User not found") })
//
//	@GetMapping(GlobalConstant.GET_CURRENT_USER)
//	public  Map<String,Object> displayuser(@CurrentUser JwtUserDetails currentUser) {
//		JwtUserDetails jwtUserDetails = currentUser;
//		Long userid = currentUser.getId();
//		return userService.getCurrentUser(userid);
//	}
//
//	@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorised request"),
//			@ApiResponse(code = 201, message = "The request is complete, and the password is successfully reset  "),
//			@ApiResponse(code = 404, message = "User not found") })
//	@GetMapping(GlobalConstant.PASSWORD_RESET)
//	public ResponseMessage resetPassword(@CurrentUser JwtUserDetails currentUser,
//			@RequestParam("newPassword") String newPassword) {
//		JwtUserDetails jwtUserDetails = currentUser;
//		Long userid = currentUser.getId();
//		userService.resetPass(userid, newPassword);
//		return new ResponseMessage(messageSource.getMessage("reset.password", null, LocaleContextHolder.getLocale()));
//	}
//
//	@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorised request"),
//			@ApiResponse(code = 201, message = "The request is complete"),
//			@ApiResponse(code = 404, message = "User not found") })
//	@GetMapping(GlobalConstant.MEMBER_LIST_COMMUNITY_EVENTS_AND_ALL)
//	public List<MemberListDto> getMemberList(@CurrentUser JwtUserDetails currentUser,
//                                             @RequestParam("communityId") Long communityId, @RequestParam("eventsId") Long eventsId) {
//		JwtUserDetails jwtUserDetails = currentUser;
//		Long userId = currentUser.getId();
//		return userService.getMemberList(userId, communityId, eventsId);
//	}
//
//	@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorised request"),
//			@ApiResponse(code = 201, message = "The request is complete"),
//			@ApiResponse(code = 404, message = "User not found") })
//	@GetMapping(GlobalConstant.MEMBER_LIST_COMMUNITY)
//	public List<MemberListDto> getMemberList(@CurrentUser JwtUserDetails currentUser,
//			@RequestParam("communityId") Long communityId) {
//		return userService.getMember(currentUser.getId(), communityId);
//	}
//
//	@GetMapping(GlobalConstant.MEMBERS_COMMUNITY_WITH_REQUESTED_STATUS)
//	public List<MembersWithRequested> getMemberListCommunityWithRequestedMembers(
//			@CurrentUser JwtUserDetails currentUser, @RequestParam("communityId") Long communityId) {
//		return userService.getMemberListCommunityWithRequestedMembers(currentUser.getId(), communityId);
//	}
//
//	@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorised request"),
//			@ApiResponse(code = 201, message = "The request is complete"),
//			@ApiResponse(code = 404, message = "User not found") })
//	@GetMapping(GlobalConstant.GETTING_USER_ROLE)
//	public List<Roles> getUserRole(@CurrentUser JwtUserDetails currentUser, @RequestParam("userId") Long userId,
//			@RequestParam("communityId") Long communityId) {
//		Long loginUserId = currentUser.getId();
//		return userService.getUserRole(userId, communityId);
//	}
//
//	@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorised request"),
//			@ApiResponse(code = 201, message = "The request is complete, and the password is successfully reset  "),
//			@ApiResponse(code = 404, message = "User not found") })
//	@PutMapping(GlobalConstant.UPDATE_USER_DETAILS)
//	public Response updateUserDetails(@CurrentUser JwtUserDetails currentUser, @RequestBody Users user) {
//		Long userid = currentUser.getId();
//		return userService.updateUserDetails(user, userid);
//	}
//
//	@GetMapping(GlobalConstant.COMMUNITYWISE_EVENT_PAYMENT)
//	public List<PaymentMaster> getPaymentConfig(@CurrentUser JwtUserDetails currentUser,
//			@RequestParam("communityId") Long communityId) {
//		Long userId = currentUser.getId();
//		return userService.getCommunityWisePayment(userId, communityId);
//	}
//
//	@PostMapping(GlobalConstant.ADD_USER_DETAILS)
//	public ResponseMessage addUserDetailsAdmin(@Valid @RequestBody UserDetail userDetail,
//			@RequestParam("communityid") Long communityId) {
//		userService.addUserDetailsInfo(userDetail, communityId);
//		return new ResponseMessage(messageSource.getMessage("event.added", null, LocaleContextHolder.getLocale()));
//	}
//
//	@PostMapping(GlobalConstant.COMMUNITYWISE_EVENT_PAYMENT_UPDATE)
//	public CommunityWise updatePaymentConfig(@CurrentUser JwtUserDetails currentUser,
//			@RequestBody PaymentSetupDto paymentSetupDto) {
//		Long userId = currentUser.getId();
//		return userService.updateCommunityWisePayment(userId, paymentSetupDto);
//	}
//
//	@PostMapping(GlobalConstant.ADD_USER_CONFIGURATION)
//	public Response addNotificationPreferences(@Valid @RequestBody UserConfiguration userConfiguration,
//			@CurrentUser JwtUserDetails currentUser) {
//		return userService.addNotificationPreferences(userConfiguration, currentUser);
//	}
//
//	@GetMapping(GlobalConstant.GET_USER_CONFIGURATION)
//	public Response getNotificationPreferences(@CurrentUser JwtUserDetails currentUser) {
//		return userService.getNotificationPreferences(currentUser);
//	}
//
//	@GetMapping(GlobalConstant.GET_COMMUNITY_PAYMENT_CONFIG)
//	public List<JsonConverter> getCommunityWisePaymentOption(@CurrentUser JwtUserDetails currentUser,
//			@RequestParam("communityId") Long communityId) {
//		return userService.getCommunityWisePaymentOption(currentUser.getId(), communityId);
//	}
//
//	@GetMapping(GlobalConstant.GET_COMMUNITY_PAYMENT_CONFIG_SINGLE)
//	public Response getCommunityWisePaymentSingle(@RequestParam("paymentId") Long paymentId,
//			@RequestParam("communityId") Long communityId) {
//		return userService.getCommunityWisePaymentSingle(paymentId, communityId);
//	}
//
//	@GetMapping(GlobalConstant.GET_PAYMENT_MASTER)
//	public List<Map<String, Object>> gePaymentMaster(@CurrentUser JwtUserDetails currentUser) {
//		return userService.gePaymentMaster();
//	}
//
//	@PostMapping(GlobalConstant.ADD_PAYMENT_MASTER_CONFIG)
//	public Response addPaymentMasterConfig(@Valid @RequestBody PaymentMaster paymentMaster,
//			@CurrentUser JwtUserDetails currentUser) {
//		return userService.addPaymentMasterConfig(paymentMaster, currentUser);
//	}
//
//	@PostMapping(GlobalConstant.ADD_COMMUNITY_PAYMENT_CONFIG)
//	public Response addCommunityPaymentConfig(@Valid @RequestBody CommunityWise communityWise,
//			@CurrentUser JwtUserDetails currentUser) {
//		return userService.addCommunityPaymentConfig(communityWise, currentUser);
//	}
//
//	@GetMapping(GlobalConstant.GET_COMMUNITY_PAYMENT_CONFIGS)
//	public Response get_payment_configs(@RequestParam("communityId") Long communityId) {
//		return userService.get_payment_configs(communityId);
//	}
//
//	@PostMapping(GlobalConstant.ADD_USER_REGISTRATION_DETAILS)
//	public Response updateUserWithCustomDetails(@CurrentUser JwtUserDetails currentUser,
//			@Valid @RequestBody UserDRegistrationDto userDRegistrationDto) {
//		return userService.updateUserWithCustomDetails(currentUser.getId(), userDRegistrationDto);
//	}
//
//	@PostMapping(GlobalConstant.UPDATE_USER_IMG_URL)
//	public ResponseMessage updateUserImg(@Valid @RequestBody CommunityImageModel communityImageModel) {
//		return userService.updateUserUrl(communityImageModel.getId(), communityImageModel.getUrl());
//	}
//
//	@GetMapping(GlobalConstant.GET_USER_BY_TOKEN)
//	public Response updateUserImg(@CurrentUser JwtUserDetails currentUser) {
//		return userService.getUserByToken(currentUser.getId());
//	}
//
//}