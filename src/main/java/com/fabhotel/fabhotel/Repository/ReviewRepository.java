package com.fabhotel.fabhotel.Repository;

import com.fabhotel.fabhotel.Entity.Property;
import com.fabhotel.fabhotel.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review ,Long > {
     List<Review>findReviewByproperty(Property property);
}

