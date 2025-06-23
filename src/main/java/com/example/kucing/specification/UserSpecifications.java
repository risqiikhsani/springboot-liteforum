package com.example.kucing.specification;

import com.example.kucing.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> containsKeyword(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isEmpty()) return null;
            String likePattern = "%" + keyword.toLowerCase() + "%";
            return cb.or(
                cb.like(cb.lower(root.get("fullname")), likePattern),
                cb.like(cb.lower(root.get("email")), likePattern),
                cb.like(cb.lower(root.get("username")), likePattern)
            );
        };
    }

    public static Specification<User> hasUsername(String username) {
        return (root, query, cb) ->
            username == null ? null : cb.equal(root.get("username"), username);
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, cb) ->
            email == null ? null : cb.equal(root.get("email"), email);
    }
}
