/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

package co.cpl.service;

import co.cpl.dto.IncidenceDto;
import co.cpl.dto.IncidenceImageDto;
import co.cpl.dto.UsersDto;

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
