package utn.frsf.ofa.cursojava.rrhh.web.it.vista.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestClientRestIt {
    public TestClientRestIt() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Ignore
    public void testGetCliente() throws IOException {
        String MY_URL = "http://172.16.65.183:7003/Vista/api/cliente";
        HttpGet httpget = new HttpGet(MY_URL);
        CloseableHttpClient cliente = HttpClients.createDefault();
        CloseableHttpResponse response1 = cliente.execute(httpget);
        HttpEntity entity1 = response1.getEntity();
        String resultado = entidadToString(entity1.getContent());
        assertEquals("GET", resultado.toUpperCase());
        EntityUtils.consume(entity1);
        response1.close();
    }

    @SuppressWarnings("oracle.jdeveloper.java.nested-assignment")
    private String entidadToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Test
    public void testPostCliente() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode clienteJson = mapper.createObjectNode();
        clienteJson.put("id", 1);
        clienteJson.put("nombre", "Nati");
        clienteJson.put("correo", "nati@mail.com");
        clienteJson.put("cuit", "203040");

        StringEntity postingString = new StringEntity(clienteJson.toString());
        HttpPost httpPost = new HttpPost("http://172.16.65.183:7003/Vista/api/cliente");
        httpPost.setEntity(postingString);
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpClient cliente = HttpClients.createDefault();
        CloseableHttpResponse response1 = cliente.execute(httpPost);
        HttpEntity entity1 = response1.getEntity();
        String resultado = entidadToString(entity1.getContent());
        assertEquals("POSTNATI", resultado.toUpperCase());
        EntityUtils.consume(entity1);
        response1.close();

    }
}
