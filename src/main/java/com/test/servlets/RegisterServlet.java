package com.test.servlets;

import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.test.model.CardConnectRequestEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String url = "http://localhost:8080/painter/email";
        URLConnection connection = new URL(url + "?email=" + email).openConnection();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String decodedString;
            while ((decodedString = bufferedReader.readLine()) != null) {
                System.out.println(decodedString);
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = req.getReader().readLine()) != null) {
            sb.append(s);
        }
        Gson gson = new Gson();
        CardConnectRequestEntity client = gson.fromJson(sb.toString(), CardConnectRequestEntity.class);
        String jsonObject = new Gson().toJson(client);
        String url = "https://fts-uat.cardconnect.com/cardconnect/rest/auth";
        String username = "testing";
        String password = "testing123";
        String auth = username + ":" + password;
        String encodedAuth = "Basic" + Base64.encode(auth.getBytes());
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Authorization", encodedAuth);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        try {
            outputStream = connection.getOutputStream();
            outputStream.write(jsonObject.getBytes());
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder decoded = new StringBuilder();
            String decodedString;
            while ((decodedString = bufferedReader.readLine()) != null) {
                decoded.append(decodedString);
            }
            /*req.setAttribute("decoded", decoded);
            RequestDispatcher dispatcher = req.getRequestDispatcher("");
            dispatcher.forward(req, resp);*/
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

}
