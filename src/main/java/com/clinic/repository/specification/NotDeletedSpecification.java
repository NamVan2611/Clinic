package com.clinic.repository.specification;

import com.clinic.entity.AuditableEntity;
import org.springframework.data.jpa.domain.Specification;

public class NotDeletedSpecification {

    public static <T extends AuditableEntity> Specification<T> notDeleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
    }

    public static <T extends AuditableEntity> Specification<T> deleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), true);
    }
}
