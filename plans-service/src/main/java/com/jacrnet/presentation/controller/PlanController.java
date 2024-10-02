package com.jacrnet.presentation.controller;

import com.jacrnet.presentation.dto.PlanDTO;
import com.jacrnet.service.IPlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private IPlanService planService;

    @PostMapping("/add")
    public ResponseEntity<PlanDTO> addPlan(@RequestBody @Valid PlanDTO planDTO){
        return new ResponseEntity<>(planService.addPlan(planDTO), HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PlanDTO>> findAll(){
        return new ResponseEntity<>(planService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePlan(@PathVariable Long id, @Valid @RequestBody PlanDTO planDTO){
        return new ResponseEntity<>(planService.updatePlan(id, planDTO), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Long id){
        return new ResponseEntity<>(planService.deletePlan(id), HttpStatus.OK);
    }

}
