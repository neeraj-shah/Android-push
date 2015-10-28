package com.mobile.push;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class GCMConnection {

	@Test
	public void test() throws IOException {
		
		Sender gcmSender = new Sender("AIzaSyD6qL6s6N2p9yAj0SofCZ-sPOCn-YqYhPA");		
		Message msg=new Message.Builder().addData(Constants.PARAM_REGISTRATION_ID, "954310322655").build();
		Result result = gcmSender.send(msg, "954310322655", 1);		
		Assert.assertEquals("neeraj", "neeraj");
		
		
	}

}
