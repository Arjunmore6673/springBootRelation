/**
 *
 */
package com.realtion.human.imple;

import com.realtion.human.repository.UserRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WeTech_Digital
 *
 */
@Repository
@Scope("prototype")
public class RelationDaoImpl {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRelationRepository userRelationRepository;


    public List<Object[]> getRelations(List<Long> categoriesId) {
        List<Object[]> list = new ArrayList<>();
        try {
//            String sql = "select e.priceCategory.id,count(*) from EventPrices e where  e.priceCategory.id in (:ids) group by  e.priceCategory.id";
            String sql = "SELECT ur.users.id, ur.users2 from UserRelation ur where ur.users.id in (:ids) ";
            Query query = entityManager.createQuery(sql);
            query.setParameter("ids", categoriesId);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
