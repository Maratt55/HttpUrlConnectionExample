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

public class CardConnectRequestServlet extends HttpServlet {

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
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(jsonObject.getBytes());
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder decoded = new StringBuilder();
            String decodedString;
            while ((decodedString = bufferedReader.readLine()) != null) {
                decoded.append(decodedString);
            }
             /*req.setAttribute("decoded", decoded);
            RequestDispatcher dispatcher = req.getRequestDispatcher("");
            dispatcher.forward(req, resp);*/
        }
    }
}
