package com.terminusgroup.ae.user;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terminusgroup.ae.model.Device;
import com.terminusgroup.ae.model.User;
import com.terminusgroup.ae.repository.DeviceRepository;
import com.terminusgroup.ae.repository.UserRepository;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceControllerTest {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    UserRepository userRepository;

    @MockBean
    DeviceRepository deviceRepository;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void register() throws Exception {

        Device device = new Device();
        device.setImei("12345678");
        device.setSim("0565697710");
        Mockito.when(deviceRepository.findOneByimei("12345678")).thenReturn(null);
        setUp();
        String uri = "/api/v2.0/devices/register";

        String inputJson = mapToJson(device);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }


    @Test
    public void imeiIsNotNull() throws Exception {
        Device device = new Device();

        device.setSim("0565697710");

        setUp();
        String uri = "/api/v2.0/devices/register";

        String inputJson = mapToJson(device);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);



    }

    @Test
    public void simIsNotNull() throws Exception {
        Device device = new Device();
        device.setImei("123456789");
        setUp();
        String uri = "/api/v2.0/devices/register";

        String inputJson = mapToJson(device);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);



    }



    @Test
    public void deviceAlreadyExistTest() throws Exception {
        Device device = new Device();
        device.setImei("12345678");
        device.setSim("0565697710");



        Mockito.when(deviceRepository.findOneByimei("12345678")).thenReturn(device);
        setUp();
        String uri = "/api/v2.0/devices/register";

        String inputJson = mapToJson(device);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);


    }






}
