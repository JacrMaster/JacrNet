package com.jacrnet.service;

import com.jacrnet.presentation.dto.PlanDTO;

import java.util.List;

public interface IPlanService {
    PlanDTO addPlan(PlanDTO planDTO);
    List<PlanDTO> findAll();
    PlanDTO updatePlan(Long id, PlanDTO planDTO);
    String deletePlan(Long id);

}
