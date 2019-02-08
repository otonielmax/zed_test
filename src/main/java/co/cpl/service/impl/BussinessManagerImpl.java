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

import co.cpl.data.IncidenceImageRepository;
import co.cpl.domain.Incidence;
import co.cpl.domain.IncidenceImage;
import co.cpl.domain.Users;
import co.cpl.dto.IncidenceDto;
import co.cpl.dto.IncidenceImageDto;
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
    private static IncidenceImageRepository incidenceImageRepository;

    public BussinessManagerImpl() {
        incidenceRepository = new IncidenceRepository();
        incidenceImageRepository = new IncidenceImageRepository();
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
        response.setType(users.get().getType());
        response.setCreateDate(users.get().getCreatedAt());
        response.setUpdateDate(users.get().getUpdatedAt());
        response.setDirectionGpsLat(users.get().getDirectionGpsLat());
        response.setDirectionGpsLng(users.get().getDirectionGpsLng());
        response.setDirectionUserLat(users.get().getDirectionUserLat());
        response.setDirectionUserLng(users.get().getDirectionUserLng());
        return response;
    }

    @Override
    public List<IncidenceDto> findIncidenceByIdUser(String id) {
        List<Incidence> users = incidenceRepository.findIncidenceByIdUser(id);
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
            userDto.setType(user.getType());
            userDto.setDirectionUser(user.getDirection_user());
            userDto.setCreateDate(user.getCreatedAt());
            userDto.setUpdateDate(user.getUpdatedAt());
            userDto.setDirectionGpsLat(user.getDirectionGpsLat());
            userDto.setDirectionGpsLng(user.getDirectionGpsLng());
            userDto.setDirectionUserLat(user.getDirectionUserLat());
            userDto.setDirectionUserLng(user.getDirectionUserLng());
            response.add(userDto);
        }
        return response;
    }

    @Override
    public String getLastIdIncidence() {
        List<Incidence> users = incidenceRepository.getLastIdIncidence();
        if(users.isEmpty()) {
            return null;
        }

        return users.get(0).getId();
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
            userDto.setType(user.getType());
            userDto.setDirectionUser(user.getDirection_user());
            userDto.setCreateDate(user.getCreatedAt());
            userDto.setUpdateDate(user.getUpdatedAt());
            userDto.setDirectionGpsLat(user.getDirectionGpsLat());
            userDto.setDirectionGpsLng(user.getDirectionGpsLng());
            userDto.setDirectionUserLat(user.getDirectionUserLat());
            userDto.setDirectionUserLng(user.getDirectionUserLng());
            response.add(userDto);
        }
        return response;
    }

    @Override
    public Boolean createIncidence(IncidenceDto usersDto) {
        return incidenceRepository.createIncidence(usersDto);
    }

    @Override
    public Boolean createIncidenceImage(IncidenceImageDto dto) {
        return incidenceImageRepository.createIncidenceImage(dto);
    }

    @Override
    public List<IncidenceImageDto> getIncidencesImageByIdIncidence(String id) {
        List<IncidenceImage> users = incidenceImageRepository.getIncidenceImageByIdIncidence(id);
        List<IncidenceImageDto> response = new LinkedList<>();
        if(users.isEmpty()) {
            return response;
        }

        for (IncidenceImage user: users) {
            IncidenceImageDto userDto = new IncidenceImageDto();
            userDto.setId(user.getId());
            userDto.setUrl(user.getUrl());
            userDto.setUrlDisplay(user.getUrlDisplay());
            userDto.setIdIncidence(user.getId_incidence());
            userDto.setCreateDate(user.getCreatedAt());
            userDto.setUpdateDate(user.getUpdatedAt());
            response.add(userDto);
        }
        return response;
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
