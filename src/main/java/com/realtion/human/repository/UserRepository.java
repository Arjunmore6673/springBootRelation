package com.realtion.human.repository;

import com.realtion.human.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by isuraksha3 on 1/22/2019.
 */

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

	Users findByEmailOrMobile(String email, String mobile);

	Users findFirstByEmailOrMobile(String email, String mobile);

	Users findByEmail(String email);

	Users findByMobile(String mobile);
//
//	@Query(value = "SELECT new com.wetech.community.dto.MemberListDto(id,image,email,name,city) FROM  Users ")
//	List<MemberListDto> getMemberList();
//
//	@Query(value = "SELECT new com.wetech.community.dto.MemberListDto(u.id,u.image,u.email,u.name,u.city) FROM  Users u LEFT JOIN UserCommunity uc ON u.id=uc.users where uc.community.id=:idParameter")
//	List<MemberListDto> getMemberListCommunity(@Param("idParameter") long communityId);
//
//	@Query(value = "SELECT new com.wetech.community.dto.MemberListDto(u.id,u.image,u.email,u.name,u.city) FROM  Users u LEFT JOIN EventsUser eu ON u.id=eu.users where eu.events.id=:eventsParam")
//	List<MemberListDto> getMemberListEvents(@Param("eventsParam") long eventsId);
//
//	@Query(value = "SELECT r.role from roles r,user_roles ur WHERE  ur.roles_id=r.id AND ur.user_id=:userId AND ur.community_id=:communityId", nativeQuery = true)
//	List<Roles> geUserRole(@Param("userId") Long userId, @Param("communityId") Long communityId);
//
//	@Query(value = "select f from FamilyDetails f where id=:userId")
//	List<FamilyDetails> getFamilyDetails(@Param("userId") Long loginUserId);
//
//    @Query(value = "select u.id,u.name,u.email,u.city,u.country,aur.status as invitation,user_image from users u join user_community uc on u.id=uc.users_id  left join activity_user_requested aur on u.id=aur.users_id where uc.community_id=:idParameter",nativeQuery = true)
//    List<MembersWithRequested> getMemberListCommunityWithRequestedMembers(@Param("idParameter") long communityId);
//
//	@Query(value = "select token from user_configuration uc,users u where u.id = uc.user_id and u.id=:userId", nativeQuery = true)
//	String findToken(@Param("userId") Long id);
//
//	List<Users> findByIdIn(List<Long> id);
//



}
