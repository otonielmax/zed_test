/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

package co.cpl.utilities;

import java.util.Optional;

public class Constants {

    // This is an example of how to declare constants
    // please build your own constants based on this
	public static final String PAYMENT_ACCOUNT_ID = Optional.ofNullable(System.getenv("PAYMENTS_ACCOUNT_ID"))
			.orElse("512321");

	private Constants() {
	}

	/* validations */
	
}
