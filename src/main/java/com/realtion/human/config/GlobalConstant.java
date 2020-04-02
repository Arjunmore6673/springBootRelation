package com.realtion.human.config;

public class GlobalConstant {
	public static final String LOGIN = "/auth/login";
	public static final String AUTHONTICATE_USER_BY_TOKEN = "/authonticate_token";
	public static final String REGISTER = "/auth/register";
	public static final String COMMUNITY = "/api/secured/community";
	public static final String LIST = "/list";
	public static final String USERS = "/api/secured/users";
	public static final String REMOVE = "/api/secured/remove";
	public static final String FORGET_PASSWORD = "/api/secured/forgot-password";
	public static final String CHECK_OTP = "/api/secured/check-otp";
	public static final String CHANGE_PASSWORD = "/api/secured/change-password";
	public static final String PASSWORD_RESET = "/password-reset";
	public static final String CODEVERIFICATION = "/codeverification";
	public static final String FAMILY = "/api/secured/family";
	public static final String FAMILY_REGISTER = "/family_register";
	public static final String MEMBER_LIST_COMMUNITY_EVENTS_AND_ALL = "/memberList";
	public static final String MEMBER_LIST_COMMUNITY = "/memberListcommunity";
	public static final String MEMBERS_COMMUNITY_WITH_REQUESTED_STATUS = "/members_with_req_status";
	public static final String BLOCK_MEMBER_FROM_COMMUNITY = "/block_member";
	public static final String GET_CURRENT_USER = "/getCurrentUserDetails";
	public static final String GETTING_USER_ROLE = "/getUserRoles";
	public static final String GETTING_LIST_ROLE_WISE = "/getting_list_role_wise";
	public static final String GET_FAMILY_DETAILS = "/get_family_details";
	public static final String UPDATE_MEMBER_ROLE = "/update_role";
	public static final String FAMILY_UPDATE = "/update_family_details";
	public static final String USER_EXIST_IN_COMMUNITY = "/check_userExist_in_Community";
	public static final String UPDATE_USER_DETAILS = "/update";
	public static final String UPDATE_MEMBER_DETAILS = "/update_member";
	public static final String GETCOMMUNITYDETAILS = "/getcommunitydetails";
	public static final String GETUSERDETAILS = "/get_user_details";
	public static final String GETCUSTOMDETAILS = "/getcustomdetails";
	public static final String UPDATECUSTOMDETAILS = "/updatecustomdetails";
	public static final String ADD_USER_ROLE = "/add_user_role";
	public static final String DELETE_USER_ROLE = "/delete_user_role";
	public static final String ADD_USER_DETAILS = "/add_user_details";
	public static final String COMMUNITY_LIST_OF_PARTICULAR_USER = "community_list_of_user";
	public static final String CREATE_NEW_COMMUNITY = "/create_new_community";
	public static final String SAVE_CUSTOM_REGISTRATION_DETAILS = "/custom_registration_details";
	public static final String VIEW_CUSTOM_DETAILS = "/viewcustomdetails";
	public static final String GET_COMMUNITY_ANNOUNCE_LIST = "/getcommunity_announce_list";
	public static final String ADD_COMMUNITY_ANNOUNCE = "/add_community_announce";
	public static final String UPDATE_COMMUNITY_ANNOUNCE = "/update_community_announce";
	public static final String GET_COMMUNITY_ANNOUNCE = "/getcommunity_announce";
	public static final String DELETE_COMMUNITY_ANNOUNCE = "/delete_community_announce";
	public static final String GET_COUNTRY_LIST = "/auth/get_country_list";
	public static final String GET_COUNTRY_STATE = "/auth/get_country_state";
	public static final String GET_STATE_CITIES = "/auth/get_state_cities";
	public static final String UPDATE_COMMUNITY_IMG_URL = "/update_community_img_url";
	public static final String PENDING_USERS_REQUESTS_ADMIN = "/get_pending_community_requests";
	public static final String ACCEPT_OR_DECLINE_COMMUNITY_ADD_REQUEST = "/admin_accept_or_deny_community_add_request";
	public static final String ADD_USER_CONFIGURATION = "/add_notification_config";
	public static final String GET_USER_CONFIGURATION = "/get_notification_config";
	public static final String GET_NOTIFICATION_DETAILS = "/notification";
	public static final String GET_COMMUNITY_PAYMENT_CONFIG = "/get_payment_config";
	public static final String GET_PAYMENT_MASTER = "/get_payment_master";
	public static final String ADD_PAYMENT_MASTER_CONFIG = "add_master_payment_config";
	public static final String ADD_COMMUNITY_PAYMENT_CONFIG = "add_community_payment_config";
	public static final String GET_COMMUNITY_PAYMENT_CONFIG_SINGLE = "/get_payment_single";
	public static final String GET_USER_COMMUNITIES = "/get_user_communities";
	public static final String GET_COMMUNITY_PAYMENT_CONFIGS = "/get_payment_configs";
	public static final String ADD_USER_REGISTRATION_DETAILS = "/add_user_dynamic_values";
	public static final String REGISTER_NEW_USER = "/register_user";
	public static final String SEND_OTP = "send_otp";
	public static final String SEND_OTP_EMAIL = "send_otp_email";
	public static final String SENDFORGOT_OTP = "sendForgot_otp";
	public static final String VERIFY_OTP = "verify_otp";
	public static final String UPDATE_USER_IMG_URL = "/update_user_img_url";

	// ***event Url list
	public static final String EVENTS = "/api/secured/events";
	public static final String EVENTS_LIST_WITH_USER_COUNT = "/events_with_count";
	public static final String GET_EVENT_DETAILS = "/get_event_details";
	public static final String EVENT_DELETE = "/event_delete";
	public static final String COMMUNITYWISE_EVENT_PAYMENT = "/community_event_payment";
	public static final String COMMUNITYWISE_EVENT_PAYMENT_UPDATE = "/communitywise_event_payment_update";
	public static final String EVENTSUSERSTATUS = "/userstatus";
	public static final String ADD_EVENT_N_PRICE = "/add_event_n_price";
	public static final String GET_GUEST_DETAILS = "/getGuestDetails";
	public static final String UPDATE = "/update";
	public static final String EVENT_LISTS = "/lists";
	public static final String UPDATE_EVENT_IMG_URL = "/update_event_img_url";
	public static final String EVENTQ_QR = "/event";
	public static final String USER_BOOKED_EVENT = "/check_event_booked";
	public static final String GET_QR_DATA = "/get_qr_data";
	public static final String GENERATE_QR = "/generate_qr";
	public static final String GET_USER_BY_TOKEN = "user_by_token";
	public static final String ADD_EVENT = "/add_event";
	public static final String ADD_EVENT_PRICES = "/add_event_prices";
	public static final String ADD_EVENT_PRICE_CATEGORY = "add_event_price_category";
	public static final String GET_EVENT_PRICE_CATEGORY = "get_event_price_category";
	public static final String GET_EVENT_PRICE_CATEGORY_BOOKING = "get_e_price_category_booking";

	//edit event booking API.
	public static final String EDIT_EVENT_BOOKING = "updateEventBooking";

	//edit event API
	//author:: Sandesh Kasliwal
	public static final String EDIT_EVENT = "updateEvent";

	// ***event

	// ***event

	// ***activity url list
	public static final String ACTIVITIES = "/api/secured/activities";
	public static final String ADD_ACTIVITY = "/add_activity";
	public static final String GET_ACTIVITY_DETAILS = "/get_activity";
	public static final String GET_ALL_ACTIVITY_LIST_WITH_STATUS = "/get_activity_list";
	public static final String UPDATE_ACTIVITY = "/update_activity";
	public static final String UPDATE_ACTIVITY_STATUS = "/change_activity_status";
	public static final String REQUESTED_MEMBER_LIST_ACTIVITY = "/requested_member_list_activity";
	public static final String GET_REQUESTED_MEMBER_LIST_ACTIVITY = "/get_requested_member_list_activity";
	public static final String ACCEPT_INVITED_REQUEST_ACTIVITY = "/accept_invitation_activity";
	public static final String REJECT_INVITED_REQUEST_ACTIVITY = "/reject_invitation_activity";
	public static final String ACTIVITY_INVITATIONS_OF_USER = "/get_invited_activities";
	public static final String REQUESTED_DELETE_MEMBER_LIST_ACTIVITY = "/requested_deleted_member_list_activity";
	public static final String UPDATE_ACTIVITY_WITH_IMG_URL = "/update_with_url";
	public static final String ACTIVITIES_OF_USERS = "/user_activities";
	public static final String EVENT_USER_ADD = "/add_event_user";
	public static final String DELETE_ACTIVITY = "/delete_activity";
	public static final String USER_BOOKED_ACTIVITY = "/check_activity_booked";
	// ***activity

	// classified ********
	public static final String CLASSIFIED = "/api/secured/classified";
	public static final String GET_CURRENCY_LIST = "/auth/get_currency_list";
	public static final String ADD_CLASSIFIED = "/add_classified";
	public static final String UPDATE_CLASSIFIED_WITH_IMG_URL = "/update_classified_with_url";
	public static final String GET_CLASSIFIED = "/get_classified";
	public static final String CLASSIFIED_DETAIL = "/classified";
	public static final String CLASSIFIED_TYPE = "/classified/type";

	public static final String SUPER_ADMIN = "/api/secured/super_admin";
	public static final String SEARCH_COMMUNITY_TICKET = "/ticket";
	public static final String COMMUNITY_DETAILS = "/community";
	public static final String DETAILS = "/details";
	public static final String TICKET = "/api/secured/ticket";
	public static final String TICKETS = "/tickets";
	public static final String TICKET_SAVE = "/ticket";
	public static final String COMMENT = "/comment";
	public static final String COUNT = "/count";

	public static final String COMMUNITY_UPDATE = "/community_update";

	/// Polling
	public static final String POLL = "/api/secured";
	public static final String POLL_PERSIST = "/polls";
	public static final String POLLS = "/poll";
	public static final String POLLS_GET = "/comm/poll";
	public static final String SUBMIT = "poll/submit";
	public static final String UPDATE_ANSWER = "/poll";
	public static final String POLL_COUNT = "/count/poll";
	public static final String POLL_DELETE = "/poll_delete";
	public static final String POLL_UPDATE = "/poll_update";

	// Discussion Forum

	public static final String GLOBAL_URL = "/api/secured";
	public static final String DISCUSSIONS = "/discussions";
	public static final String DISCUSSION = "/discussion";
	public static final String GET_DISCUSSION = "/discussion";
	public static final String COMMENTS = "/comments";
	public static final String GET_COMMENTS = "/post/comment";
	public static final String GET_USERS = "/discussion/user";
	public static final String UPDATE_DISCUSSION = "update/discussion";
	public static final String DELETE_DISCUSSION = "remove/discussion";
	public static final String GET_SINGLE_DISCUSSION_DETAIL = "/detail/discussion";
	public static final String UPDATE_FORUM_IMG_URL = "/update_forum_img_url";

	public static final String GET_COMMUNITY_DETAILS = "/users";

	//
	public static final String BLOCK_ADMIN = "/block/admin";
	public static final String SEARCH_EVENT = "/find/event";
	public static final String SEARCH_DISCUSSION = "/find/discussion";
	public static final String SEARCH_POLL = "/find/poll";
	public static final String SEARCH_ACTIVITY = "/find/activity";
	public static final String SEARCH_CLASSIFY = "/find/classify";
	public static final String SERACH_EVENT_ADMIN = "/admin/event";
	public static final String SEARCH_DISCUSSION_ADMIN = "/admin/discussion";
	public static final String SEARCH_POLL_ADMIN = "/admin/poll";
	public static final String SEARCH_ACTIVITY_ADMIN = "/admin/activity";
	public static final String SEARCH_CLASSIFY_ADMIN = "/admin/classify";
	public static final String UPDATE_EVENT_ADMIN = "/update_event";
	public static final String UPDATE_POLL_ADMIN = "/update_poll";
	public static final String UPDATE_ACTIVITY_ADMIN = "/update_activity";
	public static final String UPDATE_CLASSIFIED_ADMIN = "/update_classified";

	public static final String GET_PENDING_ACTIVITY = "/pending_activity";
	public static final String ADMIN_APPROVED = "/pending/approve";

	public static final String GET_PENDING_CLASSIFIED = "/pending_classified";
	public static final String GET_ALL_COMMUNITY = "/find/community";
	public static final String GET_ALL_COMMUNITY_MOBILE = "/find/community_mobile";

//	 public static final String CLASSIFIED_APPROVED="/pending"

	public static final String GET_CLASSIFIED_COMM_ADMIN = "/user/classified";
	public static final String UPDATE_CLASSIFIED = "/update_classified";

	public static final String VALID_COMMUNITY_NAME = "/community_name";
	public static final String VALID_EMAIL = "/user/email";
	public static final String IS_VALID_USER = "/verify/user";
	public static final String COMM_DETAILS = "/comm/user";
	public static final String DELETE_USER = "/remove/user";
	public static final String ROLLS = "/rolls";
	public static final String UPDATE_ROLLS = "/update/rolls";

	public static final String GET_SINGLE_COMM = "/user/community";
	public static final String GET_ACTIVITY = "/community/activity";
	public static final String GET_CLASSIFIED_COMM = "/community/classified";
	public static final String GET_MEMBER = "/community_member";

	// PayPal transaction.
	public static final String PAYMENT = "/api/secured";
	public static final String MAKE_TRANSACTION_ENTRY = "start_transaction";
	public static final String UPDATE_TRANSACTION_ENTRY = "update_transaction";

	public static final String GET_DISCUSSION_USERS = "/discussion/users";
	public static final String UPDATE_DISCUSSION_USER = "/discussion_update_user";
	public static final String REMOVE_USER = "/discussion_remove_user";
	public static final String ADD_CUSTOM_EVENT = "/event_custom";

	public static final String GET_NOTIFICATION_COUNT = "/get_notification_count";

	public static final String FIND_PENDING_MEMBER_ALL_COMMUNITY_BY_ADMIN = "/member_pending";

	public static final String FIND_ALL_POLL_BY_ADMIN_COMMUNITY = "/poll/comm/admin";
	public static final String ADD_EVENT_ADMIN = "/api/secured/admin/events";
	public static final String PERSIST_ADMIN_EVENT = "/add_event";

	public static final String FIND_ALL_POLL_BY_END_DATE = "/poll/comm";
	public static final String UPDATE_EVENT_CATEGORIES = "/update_categories";
	public static final String GET_ALL_COMMUNITY_COUNT = "/community_details";
	public static final String GET_ALL_USER_FOR_ACTIVITY_INVITATION = "/invite_member";
	public static final String GENERATE_EVENT_BOOKING_ID = "generate_event_bookId";
	public static final String GUEST_DETAILS = "/add_guest_details";
	public static final String ADD_GUEST = "/add_guest";
	public static final String GET_MANGE_EVENT_DETAILS = "/get_event_manage";

	public static final String USER_ADMIN = "api/secured/admin/user";
	public static final String USER_ADMIN_UPDATE = "/user_update";
	public static final String USER_PERSONAL_DETAILS_UPDATE = "/personal_details";
	public static final String USER_FAMILY_UPDATE = "/update_family";

	public static final String GET_GUEST = "/get_guest";
	public static final String GET_EVENT_BOOKING = "/get_event_booking";
	public static final String GET_EVENT_PRICES = "get_event_prices";

	public static final String GET_POLL_BY_ADMIN_COMM = "/admin_poll";
	public static final String GET_EXPIRED_POLL_BY_ANOTHER_USER = "/expire_poll";
	public static final String GET_USER_INTERESTED_ACTIVITY = "/user_interested";
	public static final String GET_USER_PAST_INTERESTED_ACTIVITY = "/past_activity";

	public static final String GET_SINGLE_COMMUNITY_DETAILS = "/comm_details";
	public static final String UPDATE_SINGLE_COMMUNITY_WITH_CONFIG = "/comm_update";
    public static final String EVENT_TAKE_CASH_PAYMENT = "event_cash_payment";
    public static final String EVENTS_OF_USER = "events_by_user" ;
    public static final String EVENT_ORGANIZER_WITH_CASH_COUNT = "organizer_with_count" ;
    public static final String EVENT_HAS_CHECKER_ROLE = "event_has_checker_role" ;
    public static final String EVENT_CHECKER_GUEST_ENTRY = "event_guest_entry" ;


    // Bussiness Listing
	public static final String BUSINESS_LISTING = "/api/secured/businessListing";
	public static final String PERSIST_BUSINESS_LISTING = "/businessLists";
	public static final String GET_BUSINESS_LISTING = "/getBusinessLists";
	public static final String PERSIST_BUSINESS_SERVICE_REVIEWS = "/serviceReviews";
	public static final String GET_BUSINESS_SERVICE_REVIEWS = "/getServiceReviews";

	public static final String SAVE_NOTIFICATION_SEEN_COUNT = "set_seen_count";
	public static final String MY_BOOKINGS = "/events_my_booking";
	public static final String DELETE_COMMUNITY = "/delete_community";
}
