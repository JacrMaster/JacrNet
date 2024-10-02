package com.jacrnet.service;

import com.jacrnet.entity.PlanEntity;
import com.jacrnet.exception.ResourceNotFoundException;
import com.jacrnet.presentation.dto.PlanDTO;
import com.jacrnet.repository.IPlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements IPlanService{

    @Autowired
    private IPlanRepository planRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PlanDTO addPlan(PlanDTO planDTO) {
        PlanEntity plan = modelMapper.map(planDTO, PlanEntity.class);
        PlanEntity planSaved = planRepository.save(plan);
        return modelMapper.map(planSaved, PlanDTO.class);

    }

    @Override
    public List<PlanDTO> findAll() {
        List<PlanEntity> planEntities = (List<PlanEntity>) planRepository.findAll();
        return planEntities.stream().map(planEntity -> modelMapper.map(planEntity, PlanDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlanDTO updatePlan(Long id, PlanDTO planDTO) {
        PlanEntity plan = planRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plan no existe")) ;
        plan.setName(planDTO.getName());
        plan.setPrice(planDTO.getPrice());
        plan.setInstallationPrice(planDTO.getInstallationPrice());
        plan.setDescription(planDTO.getDescription());

        PlanEntity planUpdate = planRepository.save(plan);
        return modelMapper.map(planUpdate, PlanDTO.class);
    }

    @Override
    public String deletePlan(Long id) {
        planRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plan no existe"));
        planRepository.deleteById(id);
        return "Eliminado con exito";

    }
}
