package com.realtion.human.service;

import com.realtion.human.entity.Feedback;
import com.realtion.human.entity.UserRelation;
import com.realtion.human.entity.Users;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    }


    /**
     * @param userId        userId
     * @param relationModel relationModel
     * @return success or failure response
     */
    public Response addRelation(Long userId, RelationModel relationModel) {
        if (relationModel.getUserId().equals(userId)) {
            response.errorResponse("user cannot add relation to himself");
            return response;
        }
        Optional<Users> users = userRepository.findById(userId);
        if (users.isPresent()) {
            Optional<Users> users2 = userRepository.findById(relationModel.getUserId());
            if (users2.isPresent()) {
                UserRelation userRelation = new UserRelation();
                userRelation.setDoc(new Date());
                userRelation.setRelation(relationModel.getRelation());
                userRelation.setUsers(users.get());
                userRelation.setUsers2(users2.get());
                userRelationRepository.save(userRelation);
                response.successResponse("user relation added successfully");
            } else {
                response.errorResponse("received user not found");
            }
        } else {
            response.errorResponse("user not found");
        }
        return response;
    }

    public Response getOthersRelations(Long userId, Long otherId) {
        Optional<Users> users = userRepository.findById(otherId == null ? userId : otherId);
        if (users.isPresent()) {
            List<UserRelation> userRelations = userRelationRepository.findAllByUsersId(users.get().getId());
            List<UserRelation> yourRelationAddedByOther = userRelationRepository.findAllByUsers2Id(users.get().getId());
            List<UsersModel> userRelatives = getRelationListHelper(userRelations, 1);
            List<UsersModel> yourRelativesOther = getRelationListHelper(yourRelationAddedByOther, 2);
            List<UsersModel> convertedList = yourRelativesOther.stream().peek((obj) -> {
                String relation = getRelationHelper(obj.getRelation(), obj.getGender(), obj.getName());
                obj.setRelation(relation);
            }).collect(Collectors.toList());
            userRelatives.addAll(convertedList);

            Set<UsersModel> s = new HashSet<UsersModel>(userRelatives);
            List<UsersModel> list = new ArrayList<UsersModel>(s);

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
        }
        // MULGA MULGI
        else if (RelationList.MULGA.getValue().equals(relation) || RelationList.MULGI.getValue().equals(relation)) {
            opposite = gender.equals("MALE") ? RelationList.PAPAA.getValue() : RelationList.AAI.getValue();
        }
        //bhavu, bahin
        else if (RelationList.BHAVU.getValue().equals(relation) || RelationList.BAHIN.getValue().equals(relation)) {
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
        } else {
            opposite = name + "'s " + relation;
        }
        return opposite;
    }

    /**
     * @param userId userId
     * @param model  model simple user with relation
     * @return success or 12/4/2020
     */
    public Response addUserAndRelation(Long userId, RelationUserModel model) {
        Response response = new Response();
        Optional<Users> users = userRepository.findById(userId);
        if (users.isPresent()) {
            try {
                Users user = mapper.map(model.getUser(), Users.class);
                Users usersByEmail;
                if (user.getEmail().length() > 0)
                    usersByEmail = userRepository.findByEmailOrMobile(user.getEmail(), user.getMobile());
                else {
                    usersByEmail = userRepository.findByMobile(user.getMobile());
                    user.setEmail(null);
                }

                Users userSaved;
                if (usersByEmail != null) {
                    userSaved = usersByEmail;
                } else {
                    user.setStatus("ADDED");
                    userSaved = userRepository.save(user);
                }

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
            } catch (Exception e) {
                e.printStackTrace();
                response.errorResponse("something went wrong " + e);
            }
        } else {
            response.errorResponse("user not found");
        }

        return response;
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
        Optional<Users> users = userRepository.findById(model.getId());
        try {
            if (users.isPresent()) {
                Users userToBeSaved = mapper.map(model, Users.class);
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
