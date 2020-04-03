package com.realtion.human.service;

import com.realtion.human.entity.UserRelation;
import com.realtion.human.entity.Users;
import com.realtion.human.model.Response;
import com.realtion.human.model.UserListModel;
import com.realtion.human.model.UsersModel;
import com.realtion.human.repository.UserRelationRepository;
import com.realtion.human.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
public class EventsService {
    public static final Logger logger = LoggerFactory.getLogger("ScheduledTasks.class");

    @Autowired
    private Response response;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRelationRepository userRelationRepository;

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


    public Response getNestedRelations(Long id, Long relativeId) {

        return response;
    }
}
