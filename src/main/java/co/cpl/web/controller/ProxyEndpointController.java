/******************************************************************
 *  
 * This code is for the Complaints service project.
 *
 * 
 * © 2018, Complaints Management All rights reserved.
 * 
 * 
 ******************************************************************/

package co.cpl.web.controller;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import co.cpl.domain.Incidence;
import co.cpl.dto.IncidenceDto;
import co.cpl.dto.IncidenceImageDto;
import co.cpl.dto.UsersDto;
import co.cpl.service.BusinessManager;
import co.cpl.utilities.GoogleCloudAuth;
import co.cpl.utilities.GoogleCloudStorage;
import co.cpl.validators.UsersValidator;
import com.google.api.client.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import co.cpl.enums.ResponseKeyName;
import org.springframework.web.client.HttpClientErrorException;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Only service exposition point of services to FE layer
 * 
 * @author jmunoz
 * @version 1.0.0
 * @since 08/09/2018
 *
 */

@RestController
@RequestMapping("/v1")
public class ProxyEndpointController extends BaseRestController {

	private static final Logger LOGGER = LogManager.getLogger(ProxyEndpointController.class);

	/** The error properties. */
	@Autowired
	@Qualifier("errorProperties")
	private Properties errorProperties;

	@Autowired
	BusinessManager businessManager;

	@RequestMapping(value = "/incidence", method = RequestMethod.GET)
	public ResponseEntity<Object> getIncidence(@RequestParam("limit") int limit, @RequestParam("offset") int offset,
											HttpServletRequest request) {
		ResponseEntity<Object> responseEntity;
		try {
			List<IncidenceDto> users = businessManager.getIncidences(limit, offset);
			responseEntity = ResponseEntity.ok(users);
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		}
		return responseEntity;
	}

	/**
	* saveUser method: allows to create a new user from an object
	*
	* @param load the whole information necessary to create user
	* @author omarquez
	* @since 12/08/2018
	* @return load confirmation registry
	*/
	@RequestMapping(value = "/incidence", method = RequestMethod.POST /*,
					headers = "content-type=multipart/form-data,application/json,multipart/mixed"*/)
	@ResponseBody
	public ResponseEntity<Object> createIncidence(@Validated @RequestBody IncidenceDto load,
											  BindingResult result, HttpServletRequest request) {

		ResponseEntity<Object> responseEntity = apiValidator(result);
		if (responseEntity != null) {
			return responseEntity;
		}
		try {
			// Creamos la incidencia
			Boolean registry = businessManager.createIncidence(load);
			// Se evalua si se creo la incidencia con exito y se evalua si exiten imagenes para esta
			if (registry && load.getImages() != null && load.getImages().size() > 0) {
				// Obtenemos el id de la incidencia creada recientemente
				String idIncidence = businessManager.getLastIdIncidence();

				// Obtenemos el token de acceso
				GoogleCloudAuth auth = new GoogleCloudAuth();
				auth.authExplicit();

				// Instanciamos la conexion con Google Cloud Storage
				GoogleCloudStorage storage = new GoogleCloudStorage(auth.getStorage());
				// Recorremos el array de imagenes enviado
				for (int i = 0; i < load.getImages().size(); i++) {
					// Creamos la variable donde se guardara la cadena de caracteres que se descompondra del base64
					String img64 = null;
					if (load.getImages().get(i).getImg().contains("data:image/jpeg;base64,")) {
						img64 = load.getImages().get(i).getImg().replaceAll("data:image/jpeg;base64,", "");
					}
					if (load.getImages().get(i).getImg().contains("data:image/png;base64,")) {
						img64 = load.getImages().get(i).getImg().replaceAll("data:image/png;base64,", "");
					}

					// Decodificamos el base64
				  byte[] data = Base64.decodeBase64(img64);

					// Generamos el nombre del archivo
					String nameFile = storage.generateNameFile(load.getDescription() + "_" + i);

					// Seteamos los datos de la guardar la imagen de la incidencia
					load.getImages().get(i).setIdIncidence(idIncidence);
					load.getImages().get(i).setUrl(
									// Enviamos la imagen al bucket
									storage.uploadFile(
													data,
													"incidence-storage",
													"image/jpeg",
													nameFile)
					);
					load.getImages().get(i).setUrlDisplay("https://storage.googleapis.com/incidence-storage/" + nameFile);

					// Guardamos en BD la info
          businessManager.createIncidenceImage(load.getImages().get(i));
        }
			}
			responseEntity =  ResponseEntity.ok(registry);
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		} catch (IOException e) {
			e.printStackTrace();
			responseEntity = setErrorResponse(new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE), request);
		}

    return responseEntity;
	}

	/**
	 * updateUser method: allows to modify the data of the user that is sent (for now only imei and status)
	 *
	 * @param load the whole information necessary to perform update user
	 * @author omarquez
	 * @since 12/08/2018
	 * @return load confirmation registry
	 */
	@RequestMapping(value = "/incidence/update", method = RequestMethod.POST)
	public ResponseEntity<Object> updateIncidence(@Validated @RequestBody IncidenceDto load,
										   BindingResult result, HttpServletRequest request) {

		ResponseEntity<Object> responseEntity = apiValidator(result);
		if (responseEntity != null) {
			return responseEntity;
		}
		try {
			Boolean registry = businessManager.updateIncidence(load);
			responseEntity =  ResponseEntity.ok(registry);
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		}

		return responseEntity;
	}

	@RequestMapping(value = "/incidence/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findIncidenceById(@PathVariable("id") String id,
												HttpServletRequest request) {
		//TODO: build custom validator for face_plate, if it apply
		ResponseEntity<Object> responseEntity;
		try {
			IncidenceDto users = businessManager.findIncidenceById(id);
			responseEntity = ResponseEntity.ok(users);
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		}
		return responseEntity;
	}

	@RequestMapping(value = "/incidence/mys_incidences/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findIncidenceByIdUser(@PathVariable("id") String id,
																									HttpServletRequest request) {
		//TODO: build custom validator for face_plate, if it apply
		ResponseEntity<Object> responseEntity;
		try {
			List<IncidenceDto> users = businessManager.findIncidenceByIdUser(id);

			for (int i = 0; i < users.size(); i++) {
				users.get(i).setImages(businessManager.getIncidencesImageByIdIncidence(users.get(i).getId()));
			}

			responseEntity = ResponseEntity.ok(users);
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		}
		return responseEntity;
	}

	@RequestMapping(value = "/incidence/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteIncidence(@PathVariable("id") String id,
											 HttpServletRequest request) {
		ResponseEntity<Object> responseEntity;
		try {
			businessManager.deleteIncidence(id);
			HashMap<String, String> response = new HashMap<>();
			response.put("success", "true");
			response.put("message", "Resource successfully deleted");
			responseEntity = ResponseEntity.ok(response);
		} catch (HttpClientErrorException ex) {
			responseEntity = setErrorResponse(ex, request);
		}
		return responseEntity;
	}

	private ResponseEntity<Object> setErrorResponse(HttpClientErrorException ex, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		HttpStatus status;
		switch (ex.getStatusCode().value()) {
			case 404:
				map.put("message", "Not Found");
				status = HttpStatus.NOT_FOUND;
				break;
			case 401:
				map.put("message", "Access denied");
				status = HttpStatus.UNAUTHORIZED;
				break;
			case 400:
				map.put("message", "bad request");
				status = HttpStatus.BAD_REQUEST;
				break;
			case 406:
				map.put("message", "invalid parameter");
				map.put("detail", ex.getMessage());
				status = HttpStatus.NOT_ACCEPTABLE;
				break;
            case 412:
                map.put("message", "invalid parameter");
                map.put("detail", ex.getMessage());
                status = HttpStatus.PRECONDITION_FAILED;
                break;
			case 500:
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			case 503:
				status = HttpStatus.SERVICE_UNAVAILABLE;
				break;
			default:
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				map.put("message", "There was a problem trying to resolve the request");
		}
		return  ResponseEntity.status(status)
				.body(buildExceptionResponse(ResponseKeyName.INCIDENCE_RESPONSE, map, ex));

	}
}
