package br.com.lrvasconcelos.eventex.domain.repositoy.impl;

import br.com.lrvasconcelos.eventex.domain.entity.Event;
import br.com.lrvasconcelos.eventex.domain.repositoy.EventsRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventsCustomRepositoryImpl implements EventsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Event> findByFilters(LocalDate startDate, LocalDate endDate) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> query = criteriaBuilder.createQuery(Event.class);
        Root<Event> eventRoot = query.from(Event.class);
        List<Object> filterPredicates = new ArrayList<>();

        if(startDate != null && endDate != null) {
            filterPredicates.add(criteriaBuilder.greaterThan(eventRoot.get("startDate"), startDate));
            filterPredicates.add(criteriaBuilder.lessThan(eventRoot.get("startDate"), endDate));
        } else if(startDate != null) {
            filterPredicates.add(criteriaBuilder.equal(eventRoot.get("startDate"), startDate));
        } else if(endDate != null) {
            filterPredicates.add(criteriaBuilder.equal(eventRoot.get("endDate"), endDate));
        }

        query.select(criteriaBuilder.construct(Event.class,
                eventRoot.get("*")))
                .where(criteriaBuilder.and(filterPredicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }

}
