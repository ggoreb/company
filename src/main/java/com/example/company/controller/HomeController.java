package com.example.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.company.model.NightPharmacy;
import com.example.company.repository.NightPharmacyRepository;

@Controller
public class HomeController {
  @Autowired
  NightPharmacyRepository nightPharmacyRepository;

  @GetMapping("/night2")
  @ResponseBody
  public List<NightPharmacy> night2(
    @RequestParam String search
  ) {
    Pageable pageable = PageRequest.of(0, 3);
    List<NightPharmacy> list = nightPharmacyRepository
    .findByRoadAddressContainingOrJibunAddressContaining(search, search, pageable);
    return list;
  }

  @GetMapping("/night")
  @ResponseBody
  public List<NightPharmacy> night(
    @RequestParam(defaultValue = "1") int page
  ) {
    // tel 내림차순으로 정렬하기
    Direction direction = Direction.DESC;
    Sort sort = Sort.by(direction, "tel");
    Pageable pageable = PageRequest.of(page - 1, 10, sort);
    Page<NightPharmacy> p = nightPharmacyRepository.findAll(pageable);
    List<NightPharmacy> list = p.getContent();


    // List<NightPharmacy> list = nightPharmacyRepository.findAll(sort);

    return list;
  } 
}
