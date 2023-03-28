package com.civalue.eugene.data.repos;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.function.Predicate;

public interface Specification<T> {

    Predicate toPredicate(Root<T> root, CriteriaQuery query, CriteriaBuilder cb);
}
