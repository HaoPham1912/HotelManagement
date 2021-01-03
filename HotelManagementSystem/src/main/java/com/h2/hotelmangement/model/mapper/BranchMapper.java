package com.h2.hotelmangement.model.mapper;

import com.google.api.client.util.Lists;
import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.model.dto.BranchDTO;

import javax.swing.text.html.CSS;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BranchMapper {

    public BranchDTO convertEntityToDto(Branch branch){
        BranchDTO branchDTO = new BranchDTO();

        Set<String> empCodes = new HashSet<>();

        branchDTO.setBranchId(branch.getBranchId().toString());
        branchDTO.setAddress(branch.getAddress());
        branchDTO.setLocation(branch.getLocation());
        branchDTO.setBranchCode(branch.getBranchCode());
        branchDTO.setDescription(branch.getDescription());
        branchDTO.setBranchName(branch.getName());
        branchDTO.setRating(String.valueOf(branch.getRating()));
        branchDTO.setStatus(branch.getStatus());
        for ( Employee employee: branch.getEmployeeSet()) {
            empCodes.add(employee.getEmpCode());
        }
        branchDTO.setEmpCodeSet(empCodes);
        Set<String> setImages = branch.getThumbnailsHotelList();
        branchDTO.setThumbnailsBranchSet(Lists.newArrayList(setImages));
        branchDTO.setMainImage(branch.getMainImage());
        return branchDTO;
    }

    public List<BranchDTO> convertListBranchEntityToDto(List<Branch> branchList){
        List<BranchDTO> branchDTOList = new ArrayList<>();
        for (Branch branch:
                branchList) {
            BranchDTO branchDTO = convertEntityToDto(branch);
            branchDTOList.add(branchDTO);
        }
        return branchDTOList;
    }

    public Branch convertDtoToEntity(BranchDTO branchDTO){
        Branch branch = new Branch();
        //branch.setBranchId(Long.valueOf(branchDTO.getBranchId()));
        branch.setLocation(branchDTO.getLocation());
        branch.setBranchCode(branchDTO.getBranchCode());
        branch.setAddress(branchDTO.getAddress());
        branch.setDescription(branchDTO.getDescription());
        branch.setName(branchDTO.getBranchName());
        branch.setStatus(branchDTO.getStatus());
        Set<String> branchImage =  new HashSet<>(branchDTO.getThumbnailsBranchSet());
        branch.setThumbnailsHotelList(branchImage);
        branch.setMainImage(branchDTO.getMainImage());
        return branch;
    }
}
