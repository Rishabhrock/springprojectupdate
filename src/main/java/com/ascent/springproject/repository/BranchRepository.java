package com.ascent.springproject.repository;

import com.ascent.springproject.model.BranchDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<BranchDto, String> {
}
