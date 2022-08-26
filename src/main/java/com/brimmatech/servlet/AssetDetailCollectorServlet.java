package com.brimmatech.servlet;

import com.brimmatech.dao.AssetDetailsClass;
import com.brimmatech.dao.AssetDetailsCollector;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/assetdetail")
public class AssetDetailCollectorServlet extends HttpServlet {
    private Gson gson = new Gson();

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = request.getReader();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            JSONObject jsonObject = new JSONObject(builder.toString());
            String status = jsonObject.getString("status");

            AssetDetailsCollector assetDetailsCollector = new AssetDetailsCollector();
            List<AssetDetailsClass> list = assetDetailsCollector.assetCollector(status);
            request.setAttribute("list", list);
            String userJsonString = this.gson.toJson(list);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(userJsonString);
            out.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}