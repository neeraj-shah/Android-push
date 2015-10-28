package com.mobile.push.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.gwtphonegap.client.event.BackButtonPressedEvent;
import com.googlecode.gwtphonegap.client.event.BackButtonPressedHandler;
import com.googlecode.gwtphonegap.client.event.MenuButtonPressedEvent;
import com.googlecode.gwtphonegap.client.event.MenuButtonPressedHandler;
import com.googlecode.gwtphonegap.client.event.VolumeUpButtonPressedEvent;
import com.googlecode.gwtphonegap.client.event.VolumeUpButtonPressedHandler;
import com.mobile.push.shared.FieldVerifier;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PushTest implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private final PhoneGap phoneGap = GWT.create(PhoneGap.class);

	// private final PhoneGap push=GWT.create(.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button notifyButton = new Button("Notify");
		final TextBox nameField = new TextBox();
		final TextBox pushNotificationField = new TextBox();
		final Button sendPushNotificationButton = new Button(
				"Send Push Notification");

		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		phoneGap.addHandler(new PhoneGapAvailableHandler() {

			@Override
			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
				errorLabel.setText("Phonegap available");

			}
		});

		phoneGap.addHandler(new PhoneGapTimeoutHandler() {

			@Override
			public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
				errorLabel.setText("Phonegap not available");

			}
		});

		errorLabel.setText("initing Phonegap");
		phoneGap.initializePhoneGap();
		RootPanel.get("sendButtonContainer").add(notifyButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		RootPanel.get("nameFieldContainer").add(pushNotificationField);
		RootPanel.get("sendButtonContainer").add(sendPushNotificationButton);
		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		sendPushNotificationButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("uuid :" + phoneGap.getDevice().getUuid());
			}
		});

		phoneGap.getEvent()
				.getVolumeUpButtonPressedHandler()
				.addVolumeUpButtonPressedHandler(
						new VolumeUpButtonPressedHandler() {

							@Override
							public void onVolumeUpButtonPressed(
									VolumeUpButtonPressedEvent event) {
								Window.alert("volume up");
							}
						});

		phoneGap.getEvent().getBackButton()
				.addBackButtonPressedHandler(new BackButtonPressedHandler() {

					@Override
					public void onBackButtonPressed(BackButtonPressedEvent event) {
						Window.alert("Back Button");
					}
				});

		phoneGap.getEvent().getMenuButton()
				.addMenuButtonPressedHandler(new MenuButtonPressedHandler() {

					@Override
					public void onMenuButtonPressed(MenuButtonPressedEvent event) {
						Window.alert("menu Button");
					}
				});

		notifyButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (phoneGap.isPhoneGapDevice()) {
					// phoneGap.getNotification().alert("phonegap device");
					AndroidPush.init();
				} else
					phoneGap.getNotification().alert("Not a phonegap device");
			}
		});
	}

}
