package com.lazy.database_interation;

import org.springframework.data.jpa.repository.JpaRepository;

interface PostItRepository extends JpaRepository<PostIt, Long>{}
