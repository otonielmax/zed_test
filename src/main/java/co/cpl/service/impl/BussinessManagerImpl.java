/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

package co.cpl.service.impl;

import co.cpl.domain.Incidence;
import co.cpl.domain.Users;
import co.cpl.dto.IncidenceDto;
import co.cpl.dto.UsersDto;
import co.cpl.service.BusinessManager;
import co.cpl.data.IncidenceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


/***
 * Implementation for business manager module
 *
 * @author jmunoz
 *
 */
@Component
public class BussinessManagerImpl implements BusinessManager{

    //private IncidenceRepository IncidenceRepository;
    private static IncidenceRepository incidenceRepository;

    public BussinessManagerImpl() {
        incidenceRepository = new IncidenceRepository();
    }

    @Override
    public IncidenceDto findIncidenceById(String Id) {
        Optional<Incidence> users = incidenceRepository.findIncidenceById(Id);
        if (!users.isPresent()) {throw new HttpClientErrorException(HttpStatus.NOT_FOUND); }
        //TODO: Replace this code for mapper approach
        IncidenceDto response = new IncidenceDto();
        response.setId(users.get().getId());
        response.setTitle(users.get().getTitle());
        response.setDescription(users.get().getDescription());
        response.setDateDevice(users.get().getDate_device());
        response.setDateUser(users.get().getDate_user());
        response.setPlaca(users.get().getPlaca());
        response.setStatus(users.get().getStatus());
        response.setCreateDate(users.get().getCreatedAt());
        response.setUpdateDate(users.get().getUpdatedAt());
        return response;
    }

    @Override
    public List<IncidenceDto> getIncidences(int limit, int offset) {
        List<Incidence> users = incidenceRepository.getIncidences(limit, offset);
        List<IncidenceDto> response = new LinkedList<>();
        if(users.isEmpty()) {
            return response;
        }

        for (Incidence user: users) {
            IncidenceDto userDto = new IncidenceDto();
            userDto.setId(user.getId());
            userDto.setTitle(user.getTitle());
            userDto.setDescription(user.getDescription());
            userDto.setPlaca(user.getPlaca());
            userDto.setDateDevice(user.getDate_device());
            userDto.setDateUser(user.getDate_user());
            userDto.setDirectionGps(user.getDirection_gps());
            userDto.setStatus(user.getStatus());
            userDto.setDirectionUser(user.getDirection_user());
            userDto.setCreateDate(user.getCreatedAt());
            userDto.setUpdateDate(user.getUpdatedAt());
            response.add(userDto);
        }
        return response;
    }

    @Override
    public Boolean createIncidence(IncidenceDto usersDto) {
        return incidenceRepository.createUser(usersDto);
    }

    @Override
    public Boolean updateIncidence(IncidenceDto usersDto) {
        return incidenceRepository.updateIncicence(usersDto);
    }

    @Override
    public void deleteIncidence(String userId) {
        Optional<Incidence> currentUser = incidenceRepository.findIncidenceById(userId);
        if (!currentUser.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        incidenceRepository.deleteIncidence(userId);
    }

}
