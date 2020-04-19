package com.realtion.human.service;

import com.realtion.human.entity.UserRelation;
import com.realtion.human.entity.Users;
import com.realtion.human.imple.RelationDaoImpl;
import com.realtion.human.model.*;
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
        Optional<Users> users;
        if (otherId == null)
            users = userRepository.findById(userId);
        else
            users = userRepository.findById(otherId);
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

            List<Long> relationIdList = new ArrayList<>();
            if (userRelations.size() != 0) {
                relationIdList = userRelations.stream().map(x -> x.getUsers2().getId()).collect(Collectors.toList());
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

            List<UsersModel> mm = models.stream().peek(obj -> obj.setHisRelations(countMap.get(obj.getId()))).collect(Collectors.toList());
            response.successResponse(mm);
        } else {
            response.errorResponse("user not found");
        }
        return response;
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
                else
                    usersByEmail = userRepository.findByMobile(user.getMobile());

                if (usersByEmail != null) {
                    user.setId(usersByEmail.getId());
                    if (user.getAddress().length() < usersByEmail.getAddress().length())
                        user.setAddress(usersByEmail.getAddress());
                }
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
            } catch (Exception e) {
                e.printStackTrace();
                response.errorResponse("something went wrong " + e);
            }
        } else {
            response.errorResponse("user not found");
        }

        return response;
    }
}
