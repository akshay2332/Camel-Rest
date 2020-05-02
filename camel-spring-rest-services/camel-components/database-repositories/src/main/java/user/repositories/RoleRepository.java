package user.repositories;

import edu.stevens.constants.ApplicationConstants;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import user.entities.Authorities;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@Repository
public class RoleRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(RoleRepository.class);

    @PersistenceContext(unitName = "postgresPU")
    private EntityManager entityManager;

    @Transactional
    public void fetchUserRole(Exchange exchange) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Authorities> authoritiesCriteriaQuery = criteriaBuilder.createQuery(Authorities.class);
        Root<Authorities> authoritiesRoot = authoritiesCriteriaQuery.from(Authorities.class);
        ParameterExpression<String> stringParameterExpression = criteriaBuilder.parameter(String.class);

        authoritiesCriteriaQuery.select(authoritiesRoot).where(criteriaBuilder.equal(authoritiesRoot.get("role"),
                stringParameterExpression));

        Authorities authorities = entityManager.createQuery(authoritiesCriteriaQuery).setParameter(stringParameterExpression,
                ApplicationConstants.ROLE_USER).getSingleResult();

        exchange.setProperty(ApplicationConstants.AUTHORITIES_OBJECT, authorities);

    }

}
