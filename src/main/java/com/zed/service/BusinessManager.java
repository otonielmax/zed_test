/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

package com.zed.service;

import com.zed.dto.IncidenceDto;
import com.zed.dto.IncidenceImageDto;

import java.util.List;

/***
 * Interface for business manager module
 *
 * @author jmunoz
 *
 */
public interface BusinessManager {
    // All implemented business methods should be declared here
    // example:

    IncidenceDto findIncidenceById(String id);

    List<IncidenceDto> findIncidenceByIdUser(String id);

    List<IncidenceImageDto> getIncidencesImageByIdIncidence(String id);

    List<IncidenceDto> getIncidences(int limit, int offset);

    String getLastIdIncidence();

    Boolean createIncidence(IncidenceDto usersDto);

    Boolean createIncidenceImage(IncidenceImageDto usersDto);

    Boolean updateIncidence(IncidenceDto usersDto);

    void deleteIncidence(String userId);
}
