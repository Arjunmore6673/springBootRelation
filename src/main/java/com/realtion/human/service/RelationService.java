package com.realtion.human.service;

import com.realtion.human.entity.Feedback;
import com.realtion.human.entity.UserRelation;
import com.realtion.human.entity.Users;
import com.realtion.human.exception.UserNotFoundException;
import com.realtion.human.helper.RelationList;
import com.realtion.human.imple.RelationDaoImpl;
import com.realtion.human.model.*;
import com.realtion.human.repository.FeedbackRepository;
import com.realtion.human.repository.UserRelationRepository;
import com.realtion.human.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import javax.management.relation.RelationException;

@Service
@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
public class RelationService {
    public static final Logger logger = LoggerFactory.getLogger("ScheduledTasks.class");

    @Autowired
    private Response response;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRelationRepository userRelationRepository;

    @Autowired
    private RelationDaoImpl relationDao;
    @Autowired
    private FeedbackRepository feedbackRepository;

    /**
     * @param userId userId
     * @return get user with relatives;
     */
    @Transactional
    public Response getUserRelations(Long userId) {
        Response response = new Response();
        try {
            Optional<Users> users = userRepository.findById(userId);
            if (users.isPresent()) {
                UserListModel userListModel = new UserListModel();
                userListModel.setUser(mapper.map(users.get(), UsersModel.class));
                List<UserRelation> userRelations = userRelationRepository.findAllByUsersId(users.get().getId());
                List<UsersModel> models = userRelations.stream().map(obj -> {
                    UsersModel usersModel = mapper.map(obj.getUsers2(), UsersModel.class);
                    usersModel.setRelation(obj.getRelation());
                    return usersModel;
                }).collect(Collectors.toList());
                userListModel.setRelatives(models);
                response.successResponse(userListModel);
            } else {
                response.errorResponse("user not found");
            }
            return response;
        } catch (Exception e) {
            response.errorResponse("Something went wrong " + e);
            return response;
        }
    }


    /**
     * @param userId        userId
     * @param relationModel relationModel
     * @return success or failure response
     */
    public Response addRelation(Long userId, RelationModel relationModel) {
        Response response = new Response();
        try {
            if (relationModel.getUserId().equals(userId)) {
                response.errorResponse("user cannot add relation to himself");
                return response;
            }

            Optional<Users> users = userRepository.findById(userId);
            Optional<Users> users2 = userRepository.findById(relationModel.getUserId());
            if (!users.isPresent()) {
                response.errorResponse("user not found");
                return response;
            }
            if (!users2.isPresent()) {
                response.errorResponse("user not found given in the body..!");
                return response;
            }

            UserRelation userRelation1 = userRelationRepository.findAllByUsersAndUsers2(users.get(), users2.get());
            if (userRelation1 != null) {
                response.errorResponse("relation already present..!");
                return response;
            }

            UserRelation userRelation = new UserRelation();
            userRelation.setDoc(new Date());
            userRelation.setRelation(relationModel.getRelation());
            userRelation.setUsers(users.get());
            userRelation.setUsers2(users2.get());
            userRelationRepository.save(userRelation);
            response.successResponse("user relation added successfully");
            return response;
        } catch (Exception e) {
            response.errorResponse("Something went wrong..!");
            return response;
        }
    }

    public Response getOthersRelations(Long userId, Long otherId) {
        Response response = new Response();
        Optional<Users> users = userRepository.findById(otherId == null ? userId : otherId);
        if (users.isPresent()) {
            List<UserRelation> userRelations = new ArrayList<>();
            userRelations = userRelationRepository.findAllByUsersId(users.get().getId());
            List<UserRelation> yourRelationAddedByOther = new ArrayList<>();
            yourRelationAddedByOther = userRelationRepository.findAllByUsers2Id(users.get().getId());
            List<UsersModel> userRelatives = getRelationListHelper(userRelations, 1);
            List<UsersModel> yourRelativesOther = getRelationListHelper(yourRelationAddedByOther, 2);
            List<UsersModel> convertedList = yourRelativesOther.stream().peek((obj) -> {
                String relation = getRelationHelper(obj.getRelation(), obj.getGender(), obj.getName());
                obj.setRelation(relation);
            }).collect(Collectors.toList());
            userRelatives.addAll(convertedList);

            Set<UsersModel> s = new HashSet<>(userRelatives);
            List<UsersModel> list = new ArrayList<>(s);

            response.successResponse(list);
        } else {
            response.errorResponse("user not found");
        }
        return response;
    }

    private List<UsersModel> getRelationListHelper(List<UserRelation> userRelations, int i) {
        List<UsersModel> models = userRelations.stream().map(obj -> {
            UsersModel usersModel = mapper.map(i == 1 ? obj.getUsers2() : obj.getUsers(), UsersModel.class);
            usersModel.setRelation(obj.getRelation());
            return usersModel;
        }).collect(Collectors.toList());

        List<Long> relationIdList = new ArrayList<>();
        if (userRelations.size() != 0) {
            relationIdList = userRelations.stream().map((x) -> i == 1 ? x.getUsers2().getId() : x.getUsers().getId()).collect(Collectors.toList());
        }
        List<Object[]> list;
        Map<Long, Set<Object>> countMap = new LinkedHashMap<>();
        if (!relationIdList.isEmpty()) {
            list = relationDao.getRelations(relationIdList);
            Set<Object> user = new HashSet<>();
            if (!list.isEmpty()) {
                for (Object[] ob : list) {
                    user.add(ob[1]);
                    countMap.put((Long) ob[0], user);
                }
            }
        }

        return models.stream().peek(obj -> obj.setHisRelations(countMap.get(obj.getId()))).collect(Collectors.toList());

    }


    private String getRelationHelper(String relation, String gender, String name) {
        String opposite = "";
        if (RelationList.AAI.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.MULGA.getValue() : RelationList.MULGI.getValue();
        } else if (RelationList.PAPAA.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.MULGA.getValue() : RelationList.MULGI.getValue();
        } else if (RelationList.MULGA.getValue().equals(relation) || RelationList.MULGI.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.PAPAA.getValue() : RelationList.AAI.getValue();
        } else if (RelationList.BHAVU.getValue().equals(relation) || RelationList.BAHIN.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.BHAVU.getValue() : RelationList.BAHIN.getValue();
        } else if (RelationList.AAJI.getValue().equals(relation) || RelationList.AJOBA.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.NATU.getValue() : RelationList.NAT.getValue();
        } else if (RelationList.PANJI.getValue().equals(relation) || RelationList.PANJOBA.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.NATU.getValue() : RelationList.NAT.getValue();
        } else if (RelationList.NAT.getValue().equals(relation) || RelationList.NATU.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.AJOBA.getValue() : RelationList.AAJI.getValue();
        } else if (RelationList.MAMA.getValue().equals(relation) || RelationList.MAMI.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.BHACHA.getValue() : RelationList.BHACHI.getValue();
        } else if (RelationList.BHACHA.getValue().equals(relation) || RelationList.BHACHI.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.MAMA.getValue() : RelationList.MAMI.getValue();
        } else if (RelationList.DAJI.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.MEHVNA.getValue() : RelationList.MEHVNI.getValue();
        } else if (RelationList.MAVSHI.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.MULGA.getValue() : RelationList.MULGI.getValue();
        } else if (RelationList.VAHINI.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.BHAUJI.getValue() : RelationList.NANAND.getValue();
        } else {
            opposite = name + " added above person's relation as " + "'" + relation + "'";
        }
        return opposite;
    }

    /**
     * @param userId userId
     * @param model  model simple user with relation
     * @return success or 12/4/2020
     * @throws RelationException
     */
    public Response addUserAndRelation(Long userId, RelationUserModel model) throws RelationException {
        Response response = new Response();
        Optional<Users> users = userRepository.findById(userId);
        try {
            if (users.isPresent()) {
                //TODO : check given user is already present in db check mail and mobile..!.
                Users emailUserChk = userRepository.findByEmail(model.getUser().getEmail());
                Users mobileUerChk = userRepository.findByMobile(model.getUser().getMobile());
                Users relativeNew = null;
                //TODO : if present check relation is already entry exist with this user
                //TODO Doing this secretly because it findByEmailMobile giving wrong response.!
                if (emailUserChk != null)
                    relativeNew = emailUserChk;
                if (mobileUerChk != null)
                    relativeNew = mobileUerChk;

                if (relativeNew != null) {
                    UserRelation userRelation1 = userRelationRepository.findAllByUsersAndUsers2(users.get(), relativeNew);
                    //TODO : if yes give msg
                    if (userRelation1 != null) {
                        response.successResponse("relation already present..!", userRelation1);
                        return response;
                    } else {
                        //TODO : if not add relation..!
                        UserRelation userRelation = new UserRelation();
                        userRelation.setDoc(new Date());
                        userRelation.setRelation(model.getRelation());
                        userRelation.setUsers(users.get());
                        userRelation.setUsers2(relativeNew);
                        userRelationRepository.save(userRelation);
                        response.successResponse("user relation added successfully");
                        return response;
                    }
                }
                // TODO else create new entry in db
                //TODO FOR user and for user relation
                Users user = mapper.map(model.getUser(), Users.class);
                user.setStatus("ADDED");
                Users userSaved = userRepository.save(user);

                UserRelation userRelationCheck = userRelationRepository.findAllByUsersAndUsers2(users.get(), userSaved);
                UserRelation userRelation = new UserRelation();
                if (userRelationCheck != null) {
                    userRelation.setId(userRelationCheck.getId());
                }
                userRelation.setUsers(users.get());
                userRelation.setUsers2(userSaved);
                userRelation.setRelation(model.getRelation());
                userRelation.setDoc(new Date());
                userRelationRepository.save(userRelation);
                response.successResponse("successfully saved relation", "");
            } else {
                response.errorResponse("user not found");
                throw new UserNotFoundException("user not found");
            }
            return response;
        } catch (Exception e) {
            throw new RelationException("something went wrong " + e.toString());
        }
    }

    public Response addFeedback(Long userId, Feedback feedback) {
        Response response = new Response();
        Optional<Users> users = userRepository.findById(userId);
        if (users.isPresent()) {
            feedback.setDate(new Date());
            feedback.setUserId(userId);
            feedbackRepository.save(feedback);
            response.successResponse("feedback taken successfully");
        } else {
            response.errorResponse("user not registered");
        }
        return response;
    }

    public Response updateUserWithImage(ImageModel model) {
        Response response = new Response();
        Optional<Users> users = userRepository.findById(model.getUserId());
        if (users.isPresent()) {
            Users user = users.get();
            user.setImage(model.getUrl());
        } else {
            response.errorResponse("user not registered");
        }
        return response;
    }

    public Response updateUser(UsersModel model) {
        Response response = new Response();
        try {

            if (model.getId() == null) {
                response.errorResponse("id is required");
                return response;
            }

            Optional<Users> users = userRepository.findById(model.getId());
            if (users.isPresent()) {
                Users userToBeSaved = mapper.map(model, Users.class);
                //TODO check if already mail and mobile is present.. if diff email or mobile number found.
                Users userMailChk = userRepository.findByEmail(userToBeSaved.getEmail());
                Users userMobileChk = userRepository.findByMobile(userToBeSaved.getMobile());
                if (userMailChk != null && !users.get().getEmail().equals(model.getEmail())) {
                    response.errorResponse("Email is already registered with diff account..!");
                    return response;
                }
                if (userMobileChk != null && !users.get().getMobile().equals(model.getMobile())) {
                    response.errorResponse("Mobile is already registered with diff account..!");
                    return response;
                }

                userToBeSaved.setId(users.get().getId());
                userRepository.save(userToBeSaved);
                response.successResponse("successfully saved", userToBeSaved);
            } else
                response.errorResponse("user not registered");
        } catch (Exception e) {
            response.errorResponse("something went wrong " + e.getMessage());
        }
        return response;
    }
}
