package com.holonplatform.vaadin.flow.demo;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.SessionScope;

import com.holonplatform.auth.Account;
import com.holonplatform.auth.AuthContext;
import com.holonplatform.auth.Credentials;
import com.holonplatform.auth.Realm;
import com.holonplatform.auth.Account.AccountProvider;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.i18n.Localization;
import com.holonplatform.core.i18n.LocalizationContext;
import com.holonplatform.vaadin.flow.demo.models.User;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.SessionInitListener;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Realm realm(AccountProvider accountProvider) {
		return Realm.builder()
				// add an Authenticator using default Account interface and given AccountProvider
				.withAuthenticator(Account.authenticator(accountProvider))
				// use the default Authorizer
				.withDefaultAuthorizer().build();
	}

	// Account provider
	@Bean
	public AccountProvider accountProvider(Datastore datastore) {
		return userId -> datastore.query().target(User.TARGET).filter(User.EMAIL.eq(userId)).findOne(User.USER)
				// map the user PropertyBox to an Account
				.map(user -> Account.builder(userId)
						// details
						.withDetail(User.USER_DETAIL_NAME, user.getValue(User.NAME))
						.withDetail(User.USER_DETAIL_ID, user.getValue(User.ID))
						// build user stored credentials declaring SHA-512 as hash algorithm
						.credentials(Credentials.builder().secret(user.getValue(User.PASSWORD)) // password
								.hashAlgorithm(Credentials.Encoder.HASH_SHA_512) // hash algorithm
								.base64Encoded().build() // secret is Base64 encoded
						)
						// load permissions
						.permissionStrings(user.getValue(User.ROLE)).build());
	}

	@Bean
	@SessionScope
	public AuthContext authContext(Realm realm) {
		return AuthContext.create(realm);
	}

	@Bean
	@SessionScope
	public LocalizationContext localizationContext() {
		LocalizationContext lc = LocalizationContext.builder().withInitialLocale(Locale.US).build();
		lc.localize(Localization.builder(Locale.US).defaultDecimalPositions(2).build());
		return lc;
	}

	@Bean
	public SessionInitListener sessionErrorHandler() {
		return e -> e.getSession().setErrorHandler(error -> {
			error.getThrowable().printStackTrace();
			Notification.show(error.getThrowable().getMessage());
		});
	}
}
